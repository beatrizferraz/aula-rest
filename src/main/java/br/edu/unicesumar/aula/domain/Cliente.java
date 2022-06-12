package br.edu.unicesumar.aula.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@NotBlank
	@Size(min=2, message = "Mínimo de 2 caracteres.")
	private String nome;

	@NotBlank
	@Size(min=2, message = "Mínimo de 2 caracteres.")
	private String sobrenome;
	
	@NotBlank
	@CPF(message = "CPF inválido.") 
	private String cpf;
	
	@NotNull
	private Long telefone;

	@NotBlank
	@Email(message = "Email inválido.")
	private String email;

	@Size(min=6, max=12, message = "Senha deve ter entre 6 e 12 caracteres.") 
	@JsonProperty(access = Access.WRITE_ONLY)
	private String senha;

	@OneToMany
	@JoinColumn(name="cliente_id")
	private List<Endereco> enderecos = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="cliente_id")
	private List<Pet> pets = new ArrayList<>();

	@OneToMany
	@JoinColumn(name="loja_id")
	private List<Loja> lojasFavoritas = new ArrayList<>(); 

	@OneToMany
	@JoinColumn(name="cartao_id")
	private List<Cartao> cartoes = new ArrayList<>();

}
