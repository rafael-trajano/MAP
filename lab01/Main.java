import java.util.ArrayList;
import java.util.List;

class Pessoa {
    protected String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

class Professor extends Pessoa {
    private List<Disciplina> disciplinas;

    public Professor(String nome) {
        super(nome);
        this.disciplinas = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina d) {
        this.disciplinas.add(d);
    }

    public void listarDisciplinas() {
        System.out.println("Disciplinas do Prof. " + nome + ":");
        for (Disciplina d : disciplinas) {
            System.out.println("- " + d.getNome());
        }
    }

    public void exibirHorario() {
        System.out.println("Horário do Prof. " + nome + ":");
        for (Disciplina d : disciplinas) {
            System.out.println("- " + d.getNome() + ": " + d.getHorario());
        }
    }
}

class Aluno extends Pessoa {
    private List<Disciplina> disciplinas;

    public Aluno(String nome) {
        super(nome);
        this.disciplinas = new ArrayList<>();
    }

    public void matricular(Disciplina d) {
        this.disciplinas.add(d);
        d.adicionarAluno(this);
    }

    public void listarDisciplinas() {
        System.out.println("Disciplinas do Aluno " + nome + ":");
        for (Disciplina d : disciplinas) {
            System.out.println("- " + d.getNome());
        }
    }

    public void exibirHorario() {
        System.out.println("Horário do Aluno " + nome + ":");
        for (Disciplina d : disciplinas) {
            System.out.println("- " + d.getHorario() + " (" + d.getNome() + ")");
        }
    }
}

class Disciplina {
    private String nome;
    private String horario;
    private Professor professor;
    private List<Aluno> alunos;

    public Disciplina(String nome, String horario, Professor professor) {
        this.nome = nome;
        this.horario = horario;
        this.professor = professor;
        this.alunos = new ArrayList<>();
        professor.adicionarDisciplina(this);
    }

    public void adicionarAluno(Aluno a) {
        this.alunos.add(a);
    }

    public String getNome() { return nome; }
    public String getHorario() { return horario; }

    public void listarAlunos() {
        System.out.println("Alunos matriculados em " + nome + ":");
        for (Aluno a : alunos) {
            System.out.println("- " + a.getNome());
        }
    }

    public int getQuantidadeAlunos() {
        return alunos.size();
    }
}

public class Main {
    public static void main(String[] args) {
        Professor profAlan = new Professor("Alan Turing");
        Professor profGrace = new Professor("Grace Hopper");

        Disciplina d1 = new Disciplina("Algoritmos", "Segunda 08:00", profAlan);
        Disciplina d2 = new Disciplina("Sistemas Operacionais", "Quarta 10:00", profAlan);
        Disciplina d3 = new Disciplina("Compiladores", "Terça 14:00", profGrace);

        Aluno aluno1 = new Aluno("Joao");
        Aluno aluno2 = new Aluno("Maria");
        
        aluno1.matricular(d1);
        aluno1.matricular(d2);
        aluno2.matricular(d1);
        aluno2.matricular(d3);

        System.out.println("=== RELATÓRIO ACADÊMICO ===\n");

        // a. Quais disciplinas um professor está ministrando
        profAlan.listarDisciplinas();
        System.out.println();

        // b. Qual o horário de um professor
        profAlan.exibirHorario();
        System.out.println();

        // c. Quais os alunos de uma dada disciplina
        d1.listarAlunos();
        System.out.println();

        // d. Quais as disciplinas de um aluno
        aluno2.listarDisciplinas();
        System.out.println();

        // e. Qual o horário de um aluno
        aluno2.exibirHorario();
        System.out.println();

        // f. Qual o número de alunos de uma disciplina
        System.out.println("Total de alunos em " + d1.getNome() + ": " + d1.getQuantidadeAlunos());
        System.out.println("Total de alunos em " + d3.getNome() + ": " + d3.getQuantidadeAlunos());
    }
}
