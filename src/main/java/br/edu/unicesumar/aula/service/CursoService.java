package br.edu.unicesumar.aula.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.aula.domain.Curso;
import br.edu.unicesumar.aula.repository.CursoRepository;

@Service
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepository;

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Page<Curso> findAll(Pageable pageable) {
        return cursoRepository.findAll(pageable);
    }

    public Curso findById(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso update(Curso curso, Long id) {
        Optional<Curso> cursoDoBancoDeDadosOpt = cursoRepository.findById(id);
        
        if(cursoDoBancoDeDadosOpt.isPresent()) {

            Curso cursoDoBancoDeDados = cursoDoBancoDeDadosOpt.get();
            cursoDoBancoDeDados.setNome(curso.getNome());
            cursoDoBancoDeDados.setDescricao(curso.getDescricao());
            cursoDoBancoDeDados.setCargaHoraria(curso.getCargaHoraria());
            cursoDoBancoDeDados.setDataInicio(curso.getDataInicio());
            
            return this.save(cursoDoBancoDeDados);
        }
        return null;
    }

    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }

    public Page<Curso> findByNome(String nome, Pageable pageable) {
        return cursoRepository.findByNome(nome, pageable);
    }
}
