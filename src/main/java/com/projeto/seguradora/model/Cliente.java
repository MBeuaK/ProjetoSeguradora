package com.projeto.seguradora.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="T_CLIENTE")
public class Cliente {
	
	@Id
	@Column(name = "ID", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOME_COMPLETO")
	private String nomeCompleto;
	
	@Column(name="CPF", unique=true)
	private String cpf;

	@Column(name="UF")
	private String uf;
	
	@Column(name="CIDADE")
	private String cidade;

}