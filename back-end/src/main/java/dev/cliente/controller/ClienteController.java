package dev.cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import dev.cliente.model.Cliente;
import dev.cliente.service.ClienteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> listar(){
        return service.listarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar( @RequestBody @Valid Cliente cliente ){
        return service.salvar(cliente);
    }

    @GetMapping("{id}")
    public Cliente listarPorId( @PathVariable Integer id ){
        return service.listarPorId(id);
    }
    
    @GetMapping("ident/{nome}")
    public String pesquisarPorNome(@PathVariable String nome ){
        return service.capturarContatosPorNome(nome);
    }
    
    @GetMapping("{id}/telefone")
    public String listarPorIdTelefone( @PathVariable Integer id ){
        return service.listarPorTelefone(id);
    }
    
    @GetMapping("nome")
    public List<Cliente> listarDezClientesPorNome(){
        return service.listarDezContatosPorNome();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir( @PathVariable Integer id ){
    	service.excluir(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editar( @PathVariable Integer id, @RequestBody @Valid Cliente contatoAtualizado ) {
    	service.editar(id, contatoAtualizado);
    }
}
