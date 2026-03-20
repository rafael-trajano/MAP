import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

interface Figura {
    String getDescricao();
}

// CIRCULO - Singleton 

final class Circulo implements Figura {

    private Circulo() {}

    private static class Holder {
        private static final Circulo INSTANCIA = new Circulo();
    }

    public static Circulo getInstancia() {
        return Holder.INSTANCIA;
    }

    @Override
    public String getDescricao() {
        return "Círculo único";
    }
}

// TRIANGULO - Controlado pela Fábrica

final class Triangulo implements Figura {

    public enum Tipo { ISOSCELES, EQUILATERO, RETANGULO }

    private final Tipo tipo;

    Triangulo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getDescricao() {
        return "Triângulo " + tipo;
    }
}

// QUADRADO - Instâncias ilimitadas

final class Quadrado implements Figura {

    private final double lado;

    Quadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public String getDescricao() {
        return "Quadrado de lado " + lado;
    }
}

// FABRICA - Singleton 

final class FabricaFiguras {

    private final Map<Triangulo.Tipo, Triangulo> cacheTriangulos;

    private FabricaFiguras() {
        this.cacheTriangulos = new ConcurrentHashMap<>();
    }

    private static class Holder {
        private static final FabricaFiguras INSTANCIA = new FabricaFiguras();
    }

    public static FabricaFiguras getInstancia() {
        return Holder.INSTANCIA;
    }

    // 1 único círculo
    public Circulo criarCirculo() {
        return Circulo.getInstancia();
    }

    // 1 triângulo por tipo
    public Triangulo criarTriangulo(Triangulo.Tipo tipo) {
        return cacheTriangulos.computeIfAbsent(tipo, Triangulo::new);
    }

    // Quadrados ilimitados
    public Quadrado criarQuadrado(double lado) {
        return new Quadrado(lado);
    }
}

public class Main {

    public static void main(String[] args) {

        System.out.println("===== TESTE DA FABRICA =====");
        FabricaFiguras fabrica1 = FabricaFiguras.getInstancia();
        FabricaFiguras fabrica2 = FabricaFiguras.getInstancia();
        System.out.println("Fábrica única? " + (fabrica1 == fabrica2));

        System.out.println("\n===== TESTE DO CIRCULO =====");
        Circulo c1 = fabrica1.criarCirculo();
        Circulo c2 = fabrica2.criarCirculo();
        System.out.println("Círculo único? " + (c1 == c2));

        System.out.println("\n===== TESTE DOS 3 TRIANGULOS =====");

        Triangulo iso1 = fabrica1.criarTriangulo(Triangulo.Tipo.ISOSCELES);
        Triangulo iso2 = fabrica1.criarTriangulo(Triangulo.Tipo.ISOSCELES);

        Triangulo equi1 = fabrica1.criarTriangulo(Triangulo.Tipo.EQUILATERO);
        Triangulo equi2 = fabrica1.criarTriangulo(Triangulo.Tipo.EQUILATERO);

        Triangulo ret1 = fabrica1.criarTriangulo(Triangulo.Tipo.RETANGULO);
        Triangulo ret2 = fabrica1.criarTriangulo(Triangulo.Tipo.RETANGULO);

        System.out.println("Isósceles único? " + (iso1 == iso2));
        System.out.println("Equilátero único? " + (equi1 == equi2));
        System.out.println("Retângulo único? " + (ret1 == ret2));

        System.out.println("\nSão três objetos diferentes?");
        System.out.println("Isósceles diferente de Equilátero? " + (iso1 != equi1));
        System.out.println("Equilátero diferente de Retângulo? " + (equi1 != ret1));
        System.out.println("Isósceles diferente de Retângulo? " + (iso1 != ret1));

        System.out.println("\n===== TESTE DOS QUADRADOS =====");
        Quadrado q1 = fabrica1.criarQuadrado(10);
        Quadrado q2 = fabrica1.criarQuadrado(10);
        System.out.println("Quadrados são o mesmo objeto? " + (q1 == q2));
    }
}
