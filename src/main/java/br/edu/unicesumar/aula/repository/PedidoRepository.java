package br.edu.unicesumar.aula.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.aula.domain.Pedido;

public interface PedidoRepository extends JpaRepository <Pedido, UUID> {
    
}
