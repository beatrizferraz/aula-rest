package br.edu.unicesumar.aula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.aula.domain.CatalogoFilme;
import br.edu.unicesumar.aula.service.CatalogoFilmeService;

@RestController
@RequestMapping("/catalogoFilme")
public class CatalogoFilmeController {

    @Autowired
    private CatalogoFilmeService service;

    @GetMapping
    public List<CatalogoFilme> buscarTodosOsFilmes() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CatalogoFilme buscarFilmesPorId(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CatalogoFilme criarNovoFilme(@RequestBody CatalogoFilme filme) {
        return this.service.save(filme);
    }

    @PutMapping("/{id}")
    public CatalogoFilme alterarFilme(@PathVariable(value = "id") Long id, @RequestBody CatalogoFilme filme) {
        return service.update(filme, id);
    }

    @DeleteMapping("/{id}")
    public void deletarFilme(@PathVariable(value = "id") Long id) {
        this.service.deleteById(id);
    }
    
}
