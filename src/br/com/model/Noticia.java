package br.com.model;

import javax.persistence.*;

@Entity
public class Noticia {

	/*
	 * 1) Atributos da Classe
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codNot;

	private String tituloNoticia;
	private String materia;
	
	private int codCategoria;

	/*
	 * 2) Métodos Construtores
	 */
	
	public Noticia() {}

	public Noticia(String tituloNoticia, String materia, int codCategoria) { 
		this.tituloNoticia = tituloNoticia;
		this.materia = materia;
		this.setCodCategoria(codCategoria);
	}

	/*
	 * 3) Métodos Getters e Setters
	 */

	public int getCodNot() {
		return codNot;
	}

	public void setCodNot(int codNot) {
		this.codNot = codNot;
	}

	public String getTituloNoticia() {
		return tituloNoticia;
	}

	public void setTituloNoticia(String tituloNoticia) {
		this.tituloNoticia = tituloNoticia;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}
	
}