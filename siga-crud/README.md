# SIGA — Atividade de CRUD completo e Etapa 1

**Técnicas de Programação II (TP2) · Aula 8** — CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Este projeto implementa as quatro operações do CRUD para alunos, separando a apresentação, as regras de negócio e o acesso aos dados em memória.

## Estrutura

```
siga-crud/
├── src/siga/
│   ├── Aluno.java
│   ├── AlunoDAO.java
│   ├── AlunoDAOMemoria.java
│   ├── ServicoAluno.java
│   └── Main.java
└── README.md
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior.

Na raiz do repositório, execute:

```bash
javac -encoding UTF-8 -d bin siga-crud/src/siga/*.java
java -cp bin siga.Main
```

No VS Code, abra o repositório e execute o método `main` do arquivo `siga-crud/src/siga/Main.java` com o suporte a Java instalado.

## Etapa 1 — CRUD completo

`AlunoDAO` define as operações de inserir, buscar por matrícula, listar, atualizar e remover. `AlunoDAOMemoria` guarda os alunos em um `Map`, cuja chave é a matrícula.

| Operação | Comportamento |
|---|---|
| Criar | Impede o cadastro de matrícula duplicada. |
| Consultar | Busca um aluno pela matrícula e informa quando não existe. |
| Listar | Devolve uma cópia da lista de alunos. |
| Alterar | Atualiza somente um aluno já cadastrado. |
| Excluir | Remove somente uma matrícula existente. |

## Etapa 2 — Camada de serviço

`ServicoAluno` concentra as operações usadas pela apresentação: `cadastrar`, `consultar`, `listar`, `alterar` e `excluir`. A validação fica no método privado `validar(Aluno)`, reutilizado no cadastro e na alteração.

As regras aplicadas são:

- nome obrigatório;
- matrícula obrigatória;
- média entre 0 e 10.

Com isso, a apresentação não replica as regras de domínio.

## Etapa 3 — Correções realizadas

### Coleção protegida

`listarTodos` cria uma nova lista a cada chamada. Alterar a lista recebida pela apresentação não altera o armazenamento interno do DAO.

### Exclusão verificada

`remover` lança uma exceção quando a matrícula não está cadastrada. A apresentação informa o erro e não confirma uma exclusão que não ocorreu.

### Validação centralizada

A regra da média existe apenas no serviço. O `Main` fornece os dados ao serviço e apresenta a mensagem recebida quando uma operação falha.

## Etapa 4 — Tratamento de exceções

O `Main` captura as exceções de cadastro, consulta, alteração e exclusão. Cada caso mostra uma mensagem específica ao usuário. Não há blocos `catch` vazios.

## Demonstração

A execução demonstra:

1. cadastro de Maria Silva e João Souza;
2. bloqueio de matrícula duplicada;
3. consulta existente e inexistente;
4. alteração existente e inexistente;
5. exclusão existente e inexistente;
6. rejeição de média fora do intervalo;
7. cópia defensiva na listagem.

A saída está disponível em [docs/execucao.txt](../docs/execucao.txt).

## Decisões de design

A entidade `Aluno` representa os dados acadêmicos. `ServicoAluno` aplica as regras do domínio e usa a abstração `AlunoDAO`, evitando dependência direta da implementação de memória. `AlunoDAOMemoria` fica responsável apenas pelo armazenamento e pelas garantias de integridade da coleção.

Essa organização mantém as responsabilidades separadas: a apresentação interage com quem executa o programa, o serviço coordena as regras e o DAO persiste os dados.
