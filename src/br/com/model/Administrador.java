package br.com.model;

import javax.persistence.*;

@Entity
public class Administrador {

	/*
	 * 1) Atributos da Classe
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codAdm;

	private String nomeCompleto;
	private String email;
	private String senha;

	/*
	 * 2) Métodos Construtores
	 */
	
	public Administrador() {}

	public Administrador(String nomeCompleto, String email, String senha) { 
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.senha = senha;
	}

	/*
	 * 3) Métodos Getters e Setters
	 */

	public int getCodAdm() {
		return codAdm;
	}

	public void setCodAdm(int codAdm) {
		this.codAdm = codAdm;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}