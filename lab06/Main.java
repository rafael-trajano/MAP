interface Visitor {
    void visit(Circulo c);
    void visit(Triangulo t);
    void visit(Retangulo r);
    void visit(Trapezio t);
}

interface Figura {
    void accept(Visitor v);
}

class Circulo implements Figura {
    double raio;
    Circulo(double raio) { this.raio = raio; }
    public void accept(Visitor v) { v.visit(this); }
}

class Triangulo implements Figura {
    double l1, l2, l3, base, altura;
    Triangulo(double l1, double l2, double l3, double base, double altura) {
        this.l1 = l1; this.l2 = l2; this.l3 = l3;
        this.base = base; this.altura = altura;
    }
    public void accept(Visitor v) { v.visit(this); }
}

class Retangulo implements Figura {
    double largura, altura;
    Retangulo(double largura, double altura) {
        this.largura = largura; this.altura = altura;
    }
    public void accept(Visitor v) { v.visit(this); }
}

class Trapezio implements Figura {
    double baseMaior, baseMenor, altura, lado1, lado2;
    Trapezio(double bM, double bm, double h, double l1, double l2) {
        this.baseMaior = bM; this.baseMenor = bm;
        this.altura = h; this.lado1 = l1; this.lado2 = l2;
    }
    public void accept(Visitor v) { v.visit(this); }
}

class DesenharVisitor implements Visitor {
    public void visit(Circulo c) { System.out.println("Desenhando Círculo de raio " + c.raio); }
    public void visit(Triangulo t) { System.out.println("Desenhando Triângulo"); }
    public void visit(Retangulo r) { System.out.println("Desenhando Retângulo " + r.largura + "x" + r.altura); }
    public void visit(Trapezio t) { System.out.println("Desenhando Trapézio"); }
}

class CalcularAreaVisitor implements Visitor {
    public void visit(Circulo c) { System.out.println("Área Círculo: " + (Math.PI * Math.pow(c.raio, 2))); }
    public void visit(Triangulo t) { System.out.println("Área Triângulo: " + (t.base * t.altura / 2)); }
    public void visit(Retangulo r) { System.out.println("Área Retângulo: " + (r.largura * r.altura)); }
    public void visit(Trapezio t) { System.out.println("Área Trapézio: " + ((t.baseMaior + t.baseMenor) * t.altura / 2)); }
}

class CalcularPerimetroVisitor implements Visitor {
    public void visit(Circulo c) { System.out.println("Perímetro Círculo: " + (2 * Math.PI * c.raio)); }
    public void visit(Triangulo t) { System.out.println("Perímetro Triângulo: " + (t.l1 + t.l2 + t.l3)); }
    public void visit(Retangulo r) { System.out.println("Perímetro Retângulo: " + (2 * (r.largura + r.altura))); }
    public void visit(Trapezio t) { System.out.println("Perímetro Trapézio: " + (t.baseMaior + t.baseMenor + t.lado1 + t.lado2)); }
}

class MaximizarVisitor implements Visitor {
    public void visit(Circulo c) { c.raio *= 2; System.out.println("Círculo Maximizado (Raio: " + c.raio + ")"); }
    public void visit(Triangulo t) { t.base *= 2; t.altura *= 2; t.l1 *= 2; t.l2 *= 2; t.l3 *= 2; System.out.println("Triângulo Maximizado"); }
    public void visit(Retangulo r) { r.largura *= 2; r.altura *= 2; System.out.println("Retângulo Maximizado (" + r.largura + "x" + r.altura + ")"); }
    public void visit(Trapezio t) { t.baseMaior *= 2; t.baseMenor *= 2; t.altura *= 2; t.lado1 *= 2; t.lado2 *= 2; System.out.println("Trapézio Maximizado"); }
}

public class Main {
    public static void main(String[] args) {
        // Elementos concretos
        Figura[] figuras = {
            new Circulo(5),
            new Retangulo(10, 20),
            new Triangulo(3, 4, 5, 4, 3),
            new Trapezio(10, 6, 4, 5, 5)
        };

        // Visitantes
        Visitor desenhar = new DesenharVisitor();
        Visitor area = new CalcularAreaVisitor();
        Visitor perimetro = new CalcularPerimetroVisitor();
        Visitor maximizar = new MaximizarVisitor();

        // Testes
        System.out.println("--- OPERAÇÃO: DESENHAR ---");
        for (Figura f : figuras) f.accept(desenhar);

        System.out.println("\n--- OPERAÇÃO: CALCULAR ÁREA ---");
        for (Figura f : figuras) f.accept(area);

        System.out.println("\n--- OPERAÇÃO: CALCULAR PERÍMETRO ---");
        for (Figura f : figuras) f.accept(perimetro);

        System.out.println("\n--- OPERAÇÃO: MAXIMIZAR (DUPLICAR) ---");
        for (Figura f : figuras) f.accept(maximizar);

        System.out.println("\n--- VALIDAÇÃO PÓS-MAXIMIZAÇÃO (ÁREA) ---");
        for (Figura f : figuras) f.accept(area);
    }
}
