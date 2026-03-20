import java.util.ArrayList;
import java.util.List;

class Professor {
    private String nome;
    public Professor(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
}

class Disciplina {
    private String nome;
    public Disciplina(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
}

class RDM {
    private String semestre;
    private List<Disciplina> disciplinas;

    public RDM(String semestre) {
        this.semestre = semestre;
        this.disciplinas = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina d) {
        this.disciplinas.add(d);
    }

    public void imprimirRDM() {
        System.out.println("RDM Semestre: " + semestre);
        for (Disciplina d : disciplinas) {
            System.out.println("- " + d.getNome());
        }
    }
}

class Aluno {
    private String nome;
    private RDM rdm;

    public Aluno(String nome) {
        this.nome = nome;
    }

    public void criarRDM(String semestre) {
        this.rdm = new RDM(semestre);
    }

    public void matricularEmDisciplina(Disciplina d) {
        if (rdm != null) rdm.adicionarDisciplina(d);
    }

    public void exibirInfo() {
        System.out.println("\nAluno: " + nome);
        if (rdm != null) rdm.imprimirRDM();
    }
}

class ControleAcademico {
    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<Disciplina> disciplinas = new ArrayList<>();

    public Aluno criarAluno(String nome) {
        Aluno novo = new Aluno(nome);
        alunos.add(novo);
        return novo;
    }

    public Professor criarProfessor(String nome) {
        Professor novo = new Professor(nome);
        professores.add(novo);
        return novo;
    }

    public Disciplina criarDisciplina(String nome) {
        Disciplina nova = new Disciplina(nome);
        disciplinas.add(nova);
        return nova;
    }
}

public class Main {
    public static void main(String[] args) {
        ControleAcademico ca = new ControleAcademico();

        Professor prof1 = ca.criarProfessor("Dr. Alan Turing");
        Disciplina disc1 = ca.criarDisciplina("Metodos Avancados de Programacao");
        Disciplina disc2 = ca.criarDisciplina("Estrutura de Dados");
        
        Aluno aluno1 = ca.criarAluno("Rafael Trajano");

        aluno1.criarRDM("2025.2");
        aluno1.matricularEmDisciplina(disc1);
        aluno1.matricularEmDisciplina(disc2);

        // Testes 
        System.out.println("SISTEMA DE CONTROLE ACADÊMICO");
        System.out.println("=============================");
        System.out.println("Professor cadastrado: " + prof1.getNome());
        aluno1.exibirInfo();
    }
}
