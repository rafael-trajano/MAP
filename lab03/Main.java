import java.util.ArrayList;
import java.util.List;

interface Figura {
    double calcularArea();
    double calcularPerimetro();
    String obterInfo();
}

class Retangulo implements Figura {
    private int altura;
    private int largura;

    public Retangulo(int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
    }

    @Override
    public double calcularArea() {
        return altura * largura;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (altura + largura);
    }

    @Override
    public String obterInfo() {
        return "Retângulo [Altura: " + altura + ", Largura: " + largura + "]";
    }
}

class Quadrado implements Figura {
    private int lado;

    public Quadrado(int lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }

    @Override
    public double calcularPerimetro() {
        return 4 * lado;
    }

    @Override
    public String obterInfo() {
        return "Quadrado [Lado: " + lado + "]";
    }
}

class Circulo implements Figura {
    private int raio;
    private final double PI = 3.14159;

    public Circulo(int raio) {
        this.raio = raio;
    }

    @Override
    public double calcularArea() {
        return PI * raio * raio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * PI * raio;
    }

    @Override
    public String obterInfo() {
        return "Círculo [Raio: " + raio + "]";
    }
}


public class Main {
    public static void main(String[] args) {
        List<Figura> figuras = new ArrayList<>();
        
        try {
            figuras.add(new Retangulo(10, 5));
            figuras.add(new Quadrado(7));
            figuras.add(new Circulo(25));
            
            System.out.println("--- Executando Testes de Figuras ---");

            for (Figura f : figuras) {
                System.out.println(f.obterInfo());
                System.out.printf("Área: %.2f | Perímetro: %.2f\n", f.calcularArea(), f.calcularPerimetro());
                System.out.println("------------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Erro ao processar figuras: " + e.getMessage());
        }
    }
}
