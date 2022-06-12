package br.edu.unicesumar.aula.controller;

import java.util.Optional;
import java.util.UUID;

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

import br.edu.unicesumar.aula.domain.Pedido;
import br.edu.unicesumar.aula.service.PedidoService;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<Page<Pedido>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(this.service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable(name="id") UUID id) {
        Optional<Pedido> pedidoOpt = this.service.findById(id);

        if(pedidoOpt.isPresent()) {
            return ResponseEntity.ok(pedidoOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@Valid @RequestBody Pedido novoPedido) {
        return ResponseEntity.ok(this.service.save(novoPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId (@PathVariable (name="id") UUID id) {
        if(this.service.existeEsseId(id)){
            this.service.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();  
        }    
    }

    @DeleteMapping
    public ResponseEntity<Void> excluirTudo() {
        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }

}

