package siga;

import java.util.List;
import java.util.Objects;

public class ServicoAluno {

    private final AlunoDAO dao;

    public ServicoAluno(AlunoDAO dao) {
        this.dao = Objects.requireNonNull(dao);
    }

    public void cadastrar(Aluno aluno) {
        validar(aluno);
        dao.inserir(aluno);
    }

    public List<Aluno> listar() {
        return dao.listarTodos();
    }

    public Aluno consultar(String matricula) {
        Aluno aluno = dao.buscarPorMatricula(matricula);
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não encontrado: " + matricula + ".");
        }
        return aluno;
    }

    public void alterar(Aluno aluno) {
        validar(aluno);
        consultar(aluno.getMatricula());
        dao.atualizar(aluno);
    }

    public void excluir(String matricula) {
        consultar(matricula);
        dao.remover(matricula);
    }

    private void validar(Aluno aluno) {
        Objects.requireNonNull(aluno, "Aluno é obrigatório.");
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (aluno.getMatricula() == null || aluno.getMatricula().isBlank()) {
            throw new IllegalArgumentException("Matrícula é obrigatória.");
        }
        if (aluno.getMedia() < 0 || aluno.getMedia() > 10) {
            throw new IllegalArgumentException("Média deve estar entre 0 e 10.");
        }
    }
}
