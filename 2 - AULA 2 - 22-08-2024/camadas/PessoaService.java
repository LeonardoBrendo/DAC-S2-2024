package camadas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaService {
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

    public Optional<Pessoa> buscarPessoaPorId(Long id) {
        return pessoas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Pessoa atualizarPessoa(Long id, String nome, int idade) {
        Optional<Pessoa> pessoaOptional = buscarPessoaPorId(id);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            pessoa.setNome(nome);
            pessoa.setIdade(idade);
            return pessoa;
        }
        return null;
    }

    public boolean deletarPessoa(Long id) {
        return pessoas.removeIf(p -> p.getId().equals(id));
    }
}
