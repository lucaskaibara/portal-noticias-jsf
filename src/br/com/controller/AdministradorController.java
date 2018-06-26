package br.com.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.model.Administrador;

public class AdministradorController {

	private EntityManagerFactory manager = Persistence.createEntityManagerFactory("TrabalhoAvaliacao");

	public void Salvar(Administrador obj) {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(obj);
		entitymanager.getTransaction().commit();
	}

	public List<Administrador> Listar() {
		EntityManager entitymanager = manager.createEntityManager();
		TypedQuery<Administrador> query = null;
		query = entitymanager.createQuery("select a from Administrador a", Administrador.class);
		return query.getResultList();
	}

	public void Excluir(int codAdm) {
		EntityManager entitymanager = manager.createEntityManager();

		entitymanager.getTransaction().begin();

		Administrador adm = entitymanager.find(Administrador.class, codAdm);

		entitymanager.remove(adm);
		entitymanager.getTransaction().commit();
	}

	public Administrador CarregarAdm(int codAdm) {
		EntityManager entitymanager = manager.createEntityManager();
		TypedQuery<Administrador> query = null;
		query = entitymanager.createQuery("select a from Administrador a where a.codAdm = :codAdm", Administrador.class)
				.setParameter("codAdm", codAdm);
		return query.getSingleResult();
	}

}