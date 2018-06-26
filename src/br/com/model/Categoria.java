package br.com.model;

import javax.persistence.*;

@Entity
public class Categoria {

	/*
	 * 1) Atributos da Classe
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codCategoria;

	private String nomeCategoria;

	/*
	 * 2) Métodos Construtores
	 */
	
	public Categoria() {}

	public Categoria(String nomeCategoria) { 
		this.nomeCategoria = nomeCategoria;
	}

	/*
	 * 3) Métodos Getters e Setters
	 */
	
	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
}