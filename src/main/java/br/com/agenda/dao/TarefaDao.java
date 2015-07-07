package br.com.agenda.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.agenda.enums.Finalizado;
import br.com.agenda.modelos.Tarefas;
import br.com.agenda.modelos.Usuario;
import br.com.agenda.seguranca.UsuarioLogado;

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
	
	public Tarefas listaUmaTarefa(Tarefas tarefa, UsuarioLogado usuarioLogado){
		TypedQuery<Tarefas> query;
		
		if(usuarioLogado.isAdmin()){
			query = manager.createQuery("select t from Tarefas t where t.id=:id", Tarefas.class);
			query.setParameter("id", tarefa.getId());
		}else{			
			query = manager.createQuery("select t from Tarefas t where t.id=:id and t.usuario=:usuario",
					Tarefas.class);
			query.setParameter("id", tarefa.getId());
			query.setParameter("usuario", usuarioLogado.getUsuario());
		}
		
		try{
			return query.getSingleResult();			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void remove(Tarefas tarefa){
		manager.getTransaction().begin();
		manager.remove(tarefa);
		manager.getTransaction().commit();
	}

	public void edita(Tarefas tarefa) {
		manager.getTransaction().begin();
		manager.merge(tarefa);
		manager.getTransaction().commit();
	}
	
	public List<Tarefas> listarTarefasParaReagendar(){
		TypedQuery<Tarefas> query = manager.createQuery("select t from Tarefas t where t.finalizado=:finalizado", 
				Tarefas.class);
		query.setParameter("finalizado", Finalizado.NAO);
		
		return query.getResultList();
	}
	
	public List<Tarefas> buscarTarefasNaoFinalizadasPorUsuario(Usuario usuario) {
		TypedQuery<Tarefas> query = manager.createQuery("select t from Tarefas t where t.finalizado=:finalizado and t.usuario.id=:usuario", 
				Tarefas.class);
		query.setParameter("finalizado", Finalizado.NAO);
		query.setParameter("usuario", usuario.getId());
		
		return query.getResultList();
	}

}
