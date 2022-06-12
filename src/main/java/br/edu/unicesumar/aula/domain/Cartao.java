package br.edu.unicesumar.aula.domain;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cartao {
    @Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    private String nome;

    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private Long numero;

    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private int cvv;
    
    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private LocalDate vencimento;
}