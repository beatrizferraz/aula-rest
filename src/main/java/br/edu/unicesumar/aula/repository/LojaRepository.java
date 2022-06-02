package br.edu.unicesumar.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.aula.domain.Loja;

public interface LojaRepository extends JpaRepository <Loja, Long>{
    
}
