package clienteServidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        String servidorIp = "localhost";  
        int porta = 12345;

        try (Socket socket = new Socket(servidorIp, porta);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

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
                        out.writeObject("criar");
                        out.writeObject(nome);
                        out.writeInt(idade);
                        out.flush();
                        System.out.println(in.readObject());
                        break;

                    case 2:
                        out.writeObject("listar");
                        out.flush();
                        List<?> pessoas = (List<?>) in.readObject();
                        System.out.println("Lista de Pessoas:");
                        pessoas.forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("ID da Pessoa: ");
                        Long idBusca = scanner.nextLong();
                        out.writeObject("buscar");
                        out.writeLong(idBusca);
                        out.flush();
                        System.out.println(in.readObject());
                        break;

                    case 4:
                        System.out.print("ID da Pessoa: ");
                        Long idAtualiza = scanner.nextLong();
                        System.out.print("Novo Nome: ");
                        String novoNome = scanner.next();
                        System.out.print("Nova Idade: ");
                        int novaIdade = scanner.nextInt();
                        out.writeObject("atualizar");
                        out.writeLong(idAtualiza);
                        out.writeObject(novoNome);
                        out.writeInt(novaIdade);
                        out.flush();
                        System.out.println(in.readObject());
                        break;

                    case 5:
                        System.out.print("ID da Pessoa: ");
                        Long idDeleta = scanner.nextLong();
                        out.writeObject("deletar");
                        out.writeLong(idDeleta);
                        out.flush();
                        System.out.println(in.readObject());
                        break;

                    case 6:
                        out.writeObject("sair");
                        out.flush();
                        System.out.println(in.readObject());
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } while (opcao != 6);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
