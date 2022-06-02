package br.edu.unicesumar.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.aula.domain.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
    
}
