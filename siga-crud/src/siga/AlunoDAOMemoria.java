package siga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlunoDAOMemoria implements AlunoDAO {

    private final Map<String, Aluno> armazem = new HashMap<>();

    @Override
    public void inserir(Aluno aluno) {
        if (armazem.containsKey(aluno.getMatricula())) {
            throw new IllegalStateException("Já existe aluno com a matrícula "
                    + aluno.getMatricula() + ".");
        }
        armazem.put(aluno.getMatricula(), aluno);
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        return armazem.get(matricula);
    }

    @Override
    public List<Aluno> listarTodos() {
        return new ArrayList<>(armazem.values());
    }

    @Override
    public void atualizar(Aluno aluno) {
        if (!armazem.containsKey(aluno.getMatricula())) {
            throw new IllegalStateException("Aluno não encontrado: "
                    + aluno.getMatricula() + ".");
        }
        armazem.put(aluno.getMatricula(), aluno);
    }

    @Override
    public void remover(String matricula) {
        if (armazem.remove(matricula) == null) {
            throw new IllegalStateException("Aluno não encontrado: " + matricula + ".");
        }
    }
}
