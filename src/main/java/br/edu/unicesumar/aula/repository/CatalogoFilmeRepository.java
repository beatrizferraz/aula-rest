package br.edu.unicesumar.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.aula.domain.CatalogoFilme;

public interface CatalogoFilmeRepository extends JpaRepository <CatalogoFilme, Long> {
    
}
