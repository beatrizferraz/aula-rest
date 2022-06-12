package br.edu.unicesumar.aula.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Loja {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;    

    @NotBlank
    private String nome;

    private Double avaliacao;

    private String imgUrl;

    @OneToMany
    @JoinColumn(name = "loja_id") 
    private List<Produto> produtos = new ArrayList<>();

}
