import java.util.*;

interface Papel {
    String getDescricao();
}

class RolePassageiro implements Papel {
    private String formaPagamento;
    public RolePassageiro(String formaPagamento) { this.formaPagamento = formaPagamento; }
    public String getDescricao() { return "Passageiro (Pagamento: " + formaPagamento + ")"; }
}

class RoleMotorista implements Papel {
    private String veiculo;
    public RoleMotorista(String veiculo) { this.veiculo = veiculo; }
    public String getDescricao() { return "Motorista (Veículo: " + veiculo + ")"; }
}

class RoleAdmin implements Papel {
    public String getDescricao() { return "Administrador"; }
}

class Viagem {
    String origem, destino, horario;
    Usuario motorista, passageiro;
    String veiculo;
    double valor;
    int avaliacao;

    public Viagem(String o, String d, String h, Usuario m, Usuario p, String v, double val, int av) {
        this.origem = o; this.destino = d; this.horario = h;
        this.motorista = m; this.passageiro = p;
        this.veiculo = v; this.valor = val; this.avaliacao = av;
    }

    public void exibirDetalhes() {
        System.out.println("Viagem: " + origem + " -> " + destino + " | Horário: " + horario);
        System.out.println("Motorista: " + motorista.getNome() + " | Passageiro: " + passageiro.getNome());
        System.out.println("Veículo: " + veiculo + " | Valor: R$" + valor + " | Avaliação: " + avaliacao + "/5");
    }
}

class Usuario {
    private String nome;
    private List<Papel> papeis = new ArrayList<>();
    private List<Viagem> historico = new ArrayList<>();

    public Usuario(String nome) { this.nome = nome; }

    public void adicionarPapel(Papel p) { papeis.add(p); }
    public void adicionarViagem(Viagem v) { historico.add(v); }
    public String getNome() { return nome; }

    public void consultarHistorico() {
        System.out.println("\n--- Histórico de: " + nome + " ---");
        System.out.print("Papéis ativos: ");
        for (Papel p : papeis) System.out.print("[" + p.getDescricao() + "] ");
        System.out.println("\nCorridas realizadas:");
        for (Viagem v : historico) {
            String papelNaViagem = (v.motorista == this) ? "MOTORISTA" : "PASSAGEIRO";
            System.out.println("- " + papelNaViagem + " | Valor: R$" + v.valor + " | Avaliação: " + v.avaliacao);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Cadastro de Usuários 
        Usuario u1 = new Usuario("Carlos");
        u1.adicionarPapel(new RoleMotorista("Toyota Corolla - ABC1234"));
        u1.adicionarPapel(new RolePassageiro("Cartão de Crédito"));

        Usuario u2 = new Usuario("Ana");
        u2.adicionarPapel(new RolePassageiro("Dinheiro"));

        Usuario u3 = new Usuario("Admin_Julia");
        u3.adicionarPapel(new RoleAdmin());
        u3.adicionarPapel(new RolePassageiro("Voucher Empresa"));

        // Viagem onde Carlos é Motorista e Ana é Passageira
        Viagem v1 = new Viagem("Centro", "Aeroporto", "10:00", u1, u2, "Toyota Corolla", 50.0, 5);
        u1.adicionarViagem(v1);
        u2.adicionarViagem(v1);

        // Viagem onde Julia (Admin) é Passageira e Carlos é Motorista
        Viagem v2 = new Viagem("Shopping", "Casa", "14:30", u1, u3, "Toyota Corolla", 25.0, 4);
        u1.adicionarViagem(v2);
        u3.adicionarViagem(v2);

        // Consultas 
        System.out.println("### SISTEMA DE MOBILIDADE ###");
        
        System.out.println("\n--- DETALHES DE UMA VIAGEM ESPECÍFICA ---");
        v1.exibirDetalhes();

        u1.consultarHistorico();
        u3.consultarHistorico();
    }
}
