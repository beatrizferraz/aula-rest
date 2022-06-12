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

import br.edu.unicesumar.aula.domain.Produto;
import br.edu.unicesumar.aula.service.ProdutoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@RequestMapping("/produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<Produto>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(this.service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable(name="id") Long id) {
        Optional<Produto> produtoOpt = this.service.findById(id);

        if(produtoOpt.isPresent()) {
            return ResponseEntity.ok(produtoOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto novoProduto) {
        return ResponseEntity.ok(this.service.save(novoProduto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable(name="id") Long id, @RequestBody Produto produtoExistente) {
        
        if (!id.equals(produtoExistente.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Produto> produtoAtualizadoOpt = this.service.update(produtoExistente);

        if (produtoAtualizadoOpt.isPresent()) {
            return ResponseEntity.ok(produtoAtualizadoOpt.get());
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
