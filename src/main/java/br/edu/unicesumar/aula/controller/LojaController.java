package br.edu.unicesumar.aula.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.aula.domain.Loja;
import br.edu.unicesumar.aula.service.LojaService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@RequestMapping("/loja")
public class LojaController {
    
    @Autowired
    private LojaService service;

    @GetMapping
    public ResponseEntity<Page<Loja>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(this.service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loja> buscarPorId(@PathVariable(name="id") Long id) {
        Optional<Loja> lojaOpt = this.service.findById(id);

        if(lojaOpt.isPresent()) {
            return ResponseEntity.ok(lojaOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Loja> criarLoja(@Valid @RequestBody Loja novaLoja) {
        return ResponseEntity.ok(this.service.save(novaLoja));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loja> atualizarLoja(@PathVariable(name="id") Long id, @RequestBody Loja lojaExistente) {
        
        if (!id.equals(lojaExistente.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Loja> lojaAtualizadoOpt = this.service.update(lojaExistente);

        if (lojaAtualizadoOpt.isPresent()) {
            return ResponseEntity.ok(lojaAtualizadoOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId (@PathVariable (name="id") Long id) {
        if(this.service.existeEsseId(id)){
            this.service.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();  
        }    
    }

}
