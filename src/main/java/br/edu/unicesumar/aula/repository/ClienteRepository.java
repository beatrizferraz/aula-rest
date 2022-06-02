package br.edu.unicesumar.aula.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import br.edu.unicesumar.aula.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	boolean existsByCpf(String cpf); 
}
