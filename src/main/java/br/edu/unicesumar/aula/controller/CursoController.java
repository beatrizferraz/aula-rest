package br.edu.unicesumar.aula.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.aula.domain.Curso;
import br.edu.unicesumar.aula.service.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping("/search")
    public Page<Curso> findByNome(@PathParam(value = "nome") String nome, Pageable pageable) {
        return service.findByNome(nome,pageable);
    }
    
    @GetMapping("/{id}")
    public Curso buscarCursosPorId(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Page<Curso> buscarTodosOsCursos(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping
    public Curso criarNovoCurso(@RequestBody Curso curso) {
        return this.service.save(curso);
    }

    @PutMapping("/{id}")
    public Curso alterarDadosDoCurso(@PathVariable(value="id") Long id, @RequestBody Curso curso) {
        return this.service.update(curso, id);
    }

    @DeleteMapping("/{id}")
    public void deletarUmCursoExistente(@PathVariable(value="id") Long id) {
        this.service.deleteById(id);
    }

}
