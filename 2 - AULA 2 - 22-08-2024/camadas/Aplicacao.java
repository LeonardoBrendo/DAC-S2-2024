package camadas;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Aplicacao {
    private static PessoaService servico = new PessoaService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;

        do {
            exibirMenu();

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        criarPessoa();
                        break;
                    case 2:
                        listarPessoas();
                        break;
                    case 3:
                        buscarPessoaPorId();
                        break;
                    case 4:
                        atualizarPessoa();
                        break;
                    case 5:
                        deletarPessoa();
                        break;
                    case 6:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Por favor, insira um número válido.");
                scanner.next();
            }

        } while (opcao != 6);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("1. Criar Pessoa");
        System.out.println("2. Listar Pessoas");
        System.out.println("3. Buscar Pessoa por ID");
        System.out.println("4. Atualizar Pessoa");
        System.out.println("5. Deletar Pessoa");
        System.out.println("6. Saiuuur");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarPessoa() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        Pessoa novaPessoa = servico.criarPessoa(nome, idade);
        System.out.println("Pessoa criada: " + novaPessoa);
    }

    private static void listarPessoas() {
        List<Pessoa> pessoas = servico.listarPessoas();
        System.out.println("Lista de Pessoas:");
        pessoas.forEach(System.out::println);
    }

    private static void buscarPessoaPorId() {
        System.out.print("ID da Pessoa: ");
        Long idBusca = scanner.nextLong();
        scanner.nextLine();
        Optional<Pessoa> pessoaEncontrada = servico.buscarPessoaPorId(idBusca);
        if (pessoaEncontrada.isPresent()) {
            System.out.println("Pessoa encontrada: " + pessoaEncontrada.get());
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    private static void atualizarPessoa() {
        System.out.print("ID da Pessoa: ");
        Long idAtualiza = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Novo Nome: ");
        String novoNome = scanner.nextLine();
        System.out.print("Nova Idade: ");
        int novaIdade = scanner.nextInt();
        scanner.nextLine();
        Pessoa pessoaAtualizada = servico.atualizarPessoa(idAtualiza, novoNome, novaIdade);
        if (pessoaAtualizada != null) {
            System.out.println("Pessoa atualizada: " + pessoaAtualizada);
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    private static void deletarPessoa() {
        System.out.print("ID da Pessoa: ");
        Long idDeleta = scanner.nextLong();
        scanner.nextLine();
        boolean deletada = servico.deletarPessoa(idDeleta);
        if (deletada) {
            System.out.println("Pessoa deletada com sucesso.");
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }
}
