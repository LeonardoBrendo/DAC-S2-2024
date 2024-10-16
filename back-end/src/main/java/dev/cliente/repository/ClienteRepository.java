package dev.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query(value="SELECT * FROM contato ORDER BY nome ASC LIMIT 10", nativeQuery = true)
	public List<Cliente> buscarPrimeirosDezContatosPorNome();
	
	@Query(value="SELECT * FROM contato c where c.nome = :nome ", nativeQuery = true)
	public String retornarDezPrimeirosContatosPorNome(@Param("nome") String nome);
}

