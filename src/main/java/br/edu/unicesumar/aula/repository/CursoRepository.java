package br.edu.unicesumar.aula.repository;

import br.edu.unicesumar.aula.domain.Curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CursoRepository extends JpaRepository <Curso, Long> {

    Page<Curso> findByNome(@Param("nome") String nome, Pageable pageable);
}
