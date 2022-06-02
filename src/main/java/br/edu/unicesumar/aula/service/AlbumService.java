package br.edu.unicesumar.aula.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unicesumar.aula.domain.Album;
import br.edu.unicesumar.aula.repository.AlbumRepository;

@Service
public class AlbumService {
    
    @Autowired
    private AlbumRepository repository;

    public Page<Album> buscarTodos(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Optional<Album> buscarPorId(UUID id) {
        return this.repository.findById(id);
    }

    public Album criar(Album novoAlbum) {
        if(this.repository.existsByNome(novoAlbum.getNome())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse album já existe");
        }
        return this.repository.save(novoAlbum);
    }

    public Optional<Album> atualizar(Album albumExistente) {
        if(this.repository.existsById(albumExistente.getId())) {
            Album albumAtualizado = this.repository.save(albumExistente);
            return Optional.of(albumAtualizado);
        }
        return Optional.empty();
    }

    public void excluirPorId(UUID id) {
        this.repository.deleteById(id);
    }

    public void excluirTudo() {
        this.repository.deleteAll();
    }

    public boolean existeEsseId(UUID id) {
        return this.repository.existsById(id);
    }
}
