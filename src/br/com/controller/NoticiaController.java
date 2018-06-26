package br.com.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.model.Noticia;

public class NoticiaController {

	private EntityManagerFactory manager = Persistence.createEntityManagerFactory("TrabalhoAvaliacao");

	public void Salvar(Noticia obj) {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(obj);
		entitymanager.getTransaction().commit();
	}

	public List<Noticia> Listar() {
		EntityManager entitymanager = manager.createEntityManager();
		TypedQuery<Noticia> query = null;
		query = entitymanager.createQuery("select a from Noticia a", Noticia.class);
		return query.getResultList();
	}

	public void Excluir(int codNot) {
		EntityManager entitymanager = manager.createEntityManager();
		
		entitymanager.getTransaction().begin();
		
		Noticia not = entitymanager.find(Noticia.class, codNot);
		
		entitymanager.remove(not);
		entitymanager.getTransaction().commit();
	}
	
	public Noticia CarregarNoticia(int codNot) {
		EntityManager entitymanager = manager.createEntityManager();
		TypedQuery<Noticia> query = null;
		query = entitymanager.createQuery("select n from Noticia n where n.codNot = :codNot", Noticia.class)
				.setParameter("codNot", codNot);
		return query.getSingleResult();
	}

}