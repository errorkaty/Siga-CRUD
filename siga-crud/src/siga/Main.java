package siga;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - Atividade CRUD e Etapa 1 ===\n");

        AlunoDAO dao = new AlunoDAOMemoria();
        ServicoAluno servico = new ServicoAluno(dao);

        cadastrar(servico, new Aluno("Maria Silva", "2026001", 8.5));
        cadastrar(servico, new Aluno("João Souza", "2026002", 6.0));
        cadastrar(servico, new Aluno("Maria Silva", "2026001", 7.0));

        consultar(servico, "2026001");
        consultar(servico, "0000000");

        List<Aluno> lista = servico.listar();
        lista.add(new Aluno("Intruso Silva", "9999999", 10));
        System.out.println("\nListagem após alterar a cópia retornada:");
        listar(servico);

        alterar(servico, new Aluno("Maria Silva", "2026001", 9.0));
        alterar(servico, new Aluno("Aluno Ausente", "0000000", 5.0));

        excluir(servico, "2026002");
        excluir(servico, "0000000");
        cadastrar(servico, new Aluno("Média Inválida", "2026003", 50));

        System.out.println("\nAlunos ao final:");
        listar(servico);
    }

    private static void cadastrar(ServicoAluno servico, Aluno aluno) {
        try {
            servico.cadastrar(aluno);
            System.out.println("Cadastrado: " + aluno);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
        }
    }

    private static void consultar(ServicoAluno servico, String matricula) {
        try {
            System.out.println("Consulta: " + servico.consultar(matricula));
        } catch (IllegalArgumentException e) {
            System.out.println("Não foi possível consultar: " + e.getMessage());
        }
    }

    private static void alterar(ServicoAluno servico, Aluno aluno) {
        try {
            servico.alterar(aluno);
            System.out.println("Alterado: " + aluno);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível alterar: " + e.getMessage());
        }
    }

    private static void excluir(ServicoAluno servico, String matricula) {
        try {
            servico.excluir(matricula);
            System.out.println("Matrícula removida: " + matricula);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível remover: " + e.getMessage());
        }
    }

    private static void listar(ServicoAluno servico) {
        for (Aluno aluno : servico.listar()) {
            System.out.println("  " + aluno);
        }
    }
}
