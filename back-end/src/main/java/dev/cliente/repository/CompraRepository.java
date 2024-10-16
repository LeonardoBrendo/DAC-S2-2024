package dev.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.cliente.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
}
