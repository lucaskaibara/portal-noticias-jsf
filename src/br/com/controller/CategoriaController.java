package br.com.controller;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.model.Categoria;

public class CategoriaController {

	private EntityManagerFactory manager = 
			Persistence.createEntityManagerFactory("TrabalhoAvaliacao");
			
	public void Salvar(Categoria obj) {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(obj);
		entitymanager.getTransaction().commit();
	}
	
	public List<Categoria> Listar() {
		EntityManager entitymanager = manager.createEntityManager();	
		TypedQuery<Categoria> query = null;
		query = entitymanager.createQuery("select c from Categoria c", Categoria.class);
		return query.getResultList();
	}
	
    public HashMap<String, Integer> ListarCategoriasNot(List<Categoria> listaCategoria) { 

    	HashMap<String, Integer> opcoesCategorias = new HashMap<String, Integer>();

        for (Categoria c : listaCategoria) {
        	opcoesCategorias.put(c.getNomeCategoria(), c.getCodCategoria());
        }
        
        return opcoesCategorias;
        
    }

	public void Excluir(int codCategoria) {
		EntityManager entitymanager = manager.createEntityManager();

		entitymanager.getTransaction().begin();

		Categoria c = entitymanager.find(Categoria.class, codCategoria);

		entitymanager.remove(c);
		entitymanager.getTransaction().commit();
	}

}