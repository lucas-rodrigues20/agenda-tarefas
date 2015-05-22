package br.com.agenda.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.agenda.modelos.Tarefas;
import br.com.agenda.modelos.Usuario;

public class TarefaDao {
	
	private EntityManager manager;

	@Inject
	public TarefaDao(EntityManager manager){
		this.manager = manager;
	}
	
	public TarefaDao(){
		
	}

	public void adiciona(Tarefas tarefa) {
		manager.getTransaction().begin();
		manager.persist(tarefa);
		manager.getTransaction().commit();
	}

	public List<Tarefas> lista(Usuario usuario) {
		TypedQuery<Tarefas> query = manager.createQuery("select t from Tarefas t where t.usuario=:usuario",
						Tarefas.class);
		query.setParameter("usuario", usuario);
		
		try {
			return query.getResultList();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
