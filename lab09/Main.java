import java.util.ArrayList;
import java.util.List;

// PRODUTO ABSTRATO 
abstract class Sanduiche {
    protected String nome;
    protected List<String> ingredientes = new ArrayList<>();

    public void exibirEstrutura() {
        System.out.println("--- " + nome + " ---");
        for (String ingrediente : ingredientes) {
            System.out.println("- " + ingrediente);
        }
        System.out.println();
    }
}

// PRODUTOS CONCRETOS 
class SanduicheIntegral extends Sanduiche {
    public SanduicheIntegral() {
        nome = "Sanduíche Fitness (Integral)";
        ingredientes.add("2 fatias de pão integral");
        ingredientes.add("1 fatia de queijo mussarela");
        ingredientes.add("1 fatia de presunto de peru");
        ingredientes.add("1 ovo de capoeira");
        ingredientes.add("Tomate");
    }
}

class SanduicheFrances extends Sanduiche {
    public SanduicheFrances() {
        nome = "Sanduíche Tradicional (Francês)";
        ingredientes.add("2 fatias de pão francês");
        ingredientes.add("1 fatia de queijo prato");
        ingredientes.add("1 fatia de presunto de frango");
        ingredientes.add("1 ovo de granja");
        ingredientes.add("Tomate");
    }
}

class SanduicheBola extends Sanduiche {
    public SanduicheBola() {
        nome = "Sanduíche Especial (Bola)";
        ingredientes.add("2 fatias de pão bola");
        ingredientes.add("1 fatia de queijo cheddar");
        ingredientes.add("1 fatia de presunto de peru");
        ingredientes.add("1 ovo de capoeira");
        ingredientes.add("Tomate");
    }
}

// CRIADOR ABSTRATO  
abstract class SanduicheFactory {
    // Factory Method
    public abstract Sanduiche criarSanduiche();
}

// CRIADORES CONCRETOS 
class IntegralFactory extends SanduicheFactory {
    public Sanduiche criarSanduiche() {
        return new SanduicheIntegral();
    }
}

class FrancesFactory extends SanduicheFactory {
    public Sanduiche criarSanduiche() {
        return new SanduicheFrances();
    }
}

class BolaFactory extends SanduicheFactory {
    public Sanduiche criarSanduiche() {
        return new SanduicheBola();
    }
}

public class Main {
    public static void main(String[] args) {

        SanduicheFactory factoryIntegral = new IntegralFactory();
        SanduicheFactory factoryFrances = new FrancesFactory();
        SanduicheFactory factoryBola = new BolaFactory();

        
        Sanduiche s1 = factoryIntegral.criarSanduiche();
        Sanduiche s2 = factoryFrances.criarSanduiche();
        Sanduiche s3 = factoryBola.criarSanduiche();

        System.out.println("Iniciando produção de sanduíches...\n");
        s1.exibirEstrutura();
        s2.exibirEstrutura();
        s3.exibirEstrutura();
    }
}
