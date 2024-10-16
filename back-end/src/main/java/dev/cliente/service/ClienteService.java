package dev.cliente.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.cliente.model.Cliente;
import dev.cliente.repository.ClienteRepository;

@Service
public class ClienteService{

    private final ClienteRepository repository;
    
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }
        
	public Cliente salvar(Cliente cliente) {
		return repository.save(cliente);
	}
	
	public List<Cliente> listarTodos(){
        return repository.findAll();
    }
	
	public Cliente listarPorId(Integer id) {
		return repository.findById(id).orElseThrow( () -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
	}
	
	public String listarPorTelefone(Integer id) {
		return repository.findById(id).get().getEmail();            
	}
	
	public List<Cliente> listarDezContatosPorNome() {
		return repository.buscarPrimeirosDezContatosPorNome();
	}
	
	public String capturarContatosPorNome(String nome) {
		return repository.retornarDezPrimeirosContatosPorNome(nome);
	}
	
	public void excluir(Integer id) {
		repository.findById(id).map( contato -> {
            repository.delete(contato);
            return Void.TYPE;
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado") );
	}
	
	public void editar(Integer id, Cliente contatoAtualizado) {
		repository.findById(id)
        .map( contato -> {
        	contato.setNome(contatoAtualizado.getNome());
        	contato.setSobreNome(contatoAtualizado.getSobreNome());
        	contato.setCpf(contatoAtualizado.getCpf());
        	contato.setEmail(contatoAtualizado.getEmail());
        	contato.setTelefone(contatoAtualizado.getTelefone());
        	contato.setEndereco(contatoAtualizado.getEndereco());
            return repository.save(contato);
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado") );
	}

	
}
