package br.edu.unicesumar.aula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.aula.domain.CatalogoFilme;
import br.edu.unicesumar.aula.repository.CatalogoFilmeRepository;
import br.edu.unicesumar.aula.domain.CatalogoFilme;

@Service
public class CatalogoFilmeService {
    
    @Autowired
    private CatalogoFilmeRepository catalogoFilmeRepository;

    public CatalogoFilme save (CatalogoFilme catalogoFilme) {
        return catalogoFilmeRepository.save(catalogoFilme);
    }

    public List<CatalogoFilme> findAll() {
        return catalogoFilmeRepository.findAll();
    }

    public CatalogoFilme findById(Long id) {
        return catalogoFilmeRepository.findById(id).orElse(null);
    }

    public CatalogoFilme update(CatalogoFilme catalogoFilme, Long id) {

        Optional<CatalogoFilme> filmeBancoDeDadosOpt = catalogoFilmeRepository.findById(id);

        if(filmeBancoDeDadosOpt.isPresent()) {
           
            CatalogoFilme filmeBancoDeDados = filmeBancoDeDadosOpt.get();
            filmeBancoDeDados.setNome(catalogoFilme.getNome());
            filmeBancoDeDados.setSinopse(catalogoFilme.getSinopse());
            filmeBancoDeDados.setLancamento(catalogoFilme.getLancamento());
            filmeBancoDeDados.setBannerUrl(catalogoFilme.getBannerUrl());

            return this.save(filmeBancoDeDados);
        }
        return null;
    }

    public void deleteById(Long id) {
		catalogoFilmeRepository.deleteById(id);
	}
}
