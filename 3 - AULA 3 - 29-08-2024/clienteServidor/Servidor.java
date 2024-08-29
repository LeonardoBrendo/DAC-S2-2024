package clienteServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	public static class Pessoa {
		private Long id;
		private String nome;
		private int idade;

		public Pessoa() {
		}

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

		try (ServerSocket serverSocket = new ServerSocket(12345)) {
			System.out.println("Servidor iniciado e aguardando conexões...");

			while (true) {
				Socket socket = serverSocket.accept();
				new Thread(new ClienteHandler(socket, servico)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class ClienteHandler implements Runnable {
		private Socket socket;
		private PessoaServico servico;

		public ClienteHandler(Socket socket, PessoaServico servico) {
			this.socket = socket;
			this.servico = servico;
		}

		@Override
		public void run() {
			try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

				String comando;
				while ((comando = (String) in.readObject()) != null) {
					switch (comando) {
					case "criar":
						String nome = (String) in.readObject();
						int idade = in.readInt();
						Pessoa novaPessoa = servico.criarPessoa(nome, idade);
						out.writeObject("Pessoa criada: " + novaPessoa);
						break;

					case "listar":
						out.writeObject(servico.listarPessoas());
						break;

					case "buscar":
						Long idBusca = in.readLong();
						Pessoa pessoaEncontrada = servico.buscarPessoaPorId(idBusca);
						out.writeObject(pessoaEncontrada != null ? "Pessoa encontrada: " + pessoaEncontrada
								: "Pessoa não encontrada.");
						break;

					case "atualizar":
						Long idAtualiza = in.readLong();
						String novoNome = (String) in.readObject();
						int novaIdade = in.readInt();
						Pessoa pessoaAtualizada = servico.atualizarPessoa(idAtualiza, novoNome, novaIdade);
						out.writeObject(pessoaAtualizada != null ? "Pessoa atualizada: " + pessoaAtualizada
								: "Pessoa não encontrada.");
						break;

					case "deletar":
						Long idDeleta = in.readLong();
						boolean deletada = servico.deletarPessoa(idDeleta);
						out.writeObject(deletada ? "Pessoa deletada com sucesso." : "Pessoa não encontrada.");
						break;

					case "sair":
						out.writeObject("Conexão encerrada.");
						return;

					default:
						out.writeObject("Comando inválido.");
					}
					out.flush();
				}

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
