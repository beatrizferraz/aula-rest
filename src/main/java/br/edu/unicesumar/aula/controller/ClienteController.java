package br.edu.unicesumar.aula.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

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

import br.edu.unicesumar.aula.domain.Cliente;
import br.edu.unicesumar.aula.service.ClienteService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Page<Cliente>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(this.service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable(name="id") UUID id) {
        Optional<Cliente> clienteOpt = this.service.findById(id);

        if(clienteOpt.isPresent()) {
            return ResponseEntity.ok(clienteOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente novoCliente) {
        return ResponseEntity.ok(this.service.save(novoCliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable(name="id") UUID id, @RequestBody Cliente clienteExistente) {
        
        if (!id.equals(clienteExistente.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Cliente> clienteAtualizadoOpt = this.service.update(clienteExistente);

        if (clienteAtualizadoOpt.isPresent()) {
            return ResponseEntity.ok(clienteAtualizadoOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }    
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

}
