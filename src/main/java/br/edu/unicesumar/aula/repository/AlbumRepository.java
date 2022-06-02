package br.edu.unicesumar.aula.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.aula.domain.Album;

public interface AlbumRepository extends JpaRepository <Album, UUID> {

    boolean existsByNome(String nome);
}
