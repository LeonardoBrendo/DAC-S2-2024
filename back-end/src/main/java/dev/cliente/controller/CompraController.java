package dev.cliente.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cliente.model.Cliente;
import dev.cliente.model.Compra;
import dev.cliente.repository.ClienteRepository;
import dev.cliente.repository.CompraRepository;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    @PostMapping("/cliente/{clienteId}")
    public Compra criarCompra(@PathVariable Integer clienteId, @RequestBody Compra compra) {
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        compra.setCliente(cliente); // Associar a compra ao cliente
        return compraRepository.save(compra);
    }

    @GetMapping("/{id}")
    public Compra buscarCompra(@PathVariable Integer id) {
        return compraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }

    @PutMapping("/{id}")
    public Compra atualizarCompra(@PathVariable Integer id, @RequestBody Compra compraAtualizada) {
        Compra compra = compraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        
        compra.setProduto(compraAtualizada.getProduto());
        compra.setValor(compraAtualizada.getValor());
        
        return compraRepository.save(compra);
    }

    @DeleteMapping("/{id}")
    public void deletarCompra(@PathVariable Integer id) {
        Compra compra = compraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        
        compraRepository.delete(compra);
    }
}
