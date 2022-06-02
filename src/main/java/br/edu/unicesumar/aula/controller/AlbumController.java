package br.edu.unicesumar.aula.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.aula.domain.Album;
import br.edu.unicesumar.aula.service.AlbumService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/albuns")
public class AlbumController {
    
    @Autowired
    private AlbumService service;

    @GetMapping
    public ResponseEntity<Page<Album>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(this.service.buscarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> buscarPorId(@PathVariable(name="id") UUID id) {
        Optional<Album> albumOpt = this.service.buscarPorId(id);

        if(albumOpt.isPresent()) {
            return ResponseEntity.ok(albumOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Album> criarAlbum(@RequestBody Album novoAlbum) {
        return ResponseEntity.ok(this.service.criar(novoAlbum));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> atualizarAlbum(@PathVariable(name="id") UUID id, @RequestBody Album albumExistente) {
        
        if (!id.equals(albumExistente.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Album> albumAtualizadoOpt = this.service.atualizar(albumExistente);

        if (albumAtualizadoOpt.isPresent()) {
            return ResponseEntity.ok(albumAtualizadoOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId (@PathVariable (name="id") UUID id) {
        if(this.service.existeEsseId(id)){
            this.service.excluirPorId(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();  
        }    
    }

    @DeleteMapping
    public ResponseEntity<Void> excluirTodos() {
        this.service.excluirTudo();
        return ResponseEntity.ok().build();
    }

}
