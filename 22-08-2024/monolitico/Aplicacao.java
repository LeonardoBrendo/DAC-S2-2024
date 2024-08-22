package monolitico;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {

    public static class Pessoa {
        private Long id;
        private String nome;
        private int idade;

        public Pessoa() {}

        public Pessoa(Long id, String nome, int idade) {
            this.id = id;
            this.nome = nome;
            this.idade = idade;
        }

        // Getters e Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getIdade() {
            return idade;
        }

        public void setIdade(int idade) {
            this.idade = idade;
        }

        @Override
        public String toString() {
            return "Pessoa [id=" + id + ", nome=" + nome + ", idade=" + idade + "]";
        }
    }

    public static class PessoaServico {
        private List<Pessoa> pessoas = new ArrayList<>();
        private Long idAtual = 1L;

        public Pessoa criarPessoa(String nome, int idade) {
            Pessoa pessoa = new Pessoa(idAtual++, nome, idade);
            pessoas.add(pessoa);
            return pessoa;
        }

        public List<Pessoa> listarPessoas() {
            return pessoas;
        }

        public Pessoa buscarPessoaPorId(Long id) {
            return pessoas.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        }

        public Pessoa atualizarPessoa(Long id, String nome, int idade) {
            Pessoa pessoa = buscarPessoaPorId(id);
            if (pessoa != null) {
                pessoa.setNome(nome);
                pessoa.setIdade(idade);
            }
            return pessoa;
        }

        public boolean deletarPessoa(Long id) {
            return pessoas.removeIf(p -> p.getId().equals(id));
        }
    }

    public static void main(String[] args) {
        PessoaServico servico = new PessoaServico();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("1. Criar Pessoa");
            System.out.println("2. Listar Pessoas");
            System.out.println("3. Buscar Pessoa por ID");
            System.out.println("4. Atualizar Pessoa");
            System.out.println("5. Deletar Pessoa");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.next();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    Pessoa novaPessoa = servico.criarPessoa(nome, idade);
                    System.out.println("Pessoa criada: " + novaPessoa);
                    break;

                case 2:
                    List<Pessoa> pessoas = servico.listarPessoas();
                    System.out.println("Lista de Pessoas:");
                    pessoas.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("ID da Pessoa: ");
                    Long idBusca = scanner.nextLong();
                    Pessoa pessoaEncontrada = servico.buscarPessoaPorId(idBusca);
                    if (pessoaEncontrada != null) {
                        System.out.println("Pessoa encontrada: " + pessoaEncontrada);
                    } else {
                        System.out.println("Pessoa não encontrada.");
                    }
                    break;

                case 4:
                    System.out.print("ID da Pessoa: ");
                    Long idAtualiza = scanner.nextLong();
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.next();
                    System.out.print("Nova Idade: ");
                    int novaIdade = scanner.nextInt();
                    Pessoa pessoaAtualizada = servico.atualizarPessoa(idAtualiza, novoNome, novaIdade);
                    if (pessoaAtualizada != null) {
                        System.out.println("Pessoa atualizada: " + pessoaAtualizada);
                    } else {
                        System.out.println("Pessoa não encontrada.");
                    }
                    break;

                case 5:
                    System.out.print("ID da Pessoa: ");
                    Long idDeleta = scanner.nextLong();
                    boolean deletada = servico.deletarPessoa(idDeleta);
                    if (deletada) {
                        System.out.println("Pessoa deletada com sucesso.");
                    } else {
                        System.out.println("Pessoa não encontrada.");
                    }
                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 6);

        scanner.close();
    }
}
