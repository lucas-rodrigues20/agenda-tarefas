package br.com.agenda.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.agenda.modelos.Usuario;

@RequestScoped
public class UsuarioDao {
	
	private EntityManager manager;

	@Inject
	public UsuarioDao(EntityManager manager){
		this.manager = manager;
	}
	
	public UsuarioDao(){
		
	}
	
	public void adiciona(Usuario usuario){
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
	}
	
	public List<Usuario> lista(){
		TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u", Usuario.class);
		return query.getResultList();
	}

	public Usuario busca(String email, String senha) {
		TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u where u.email=:email and u.senha=:senha", Usuario.class);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		
		Usuario usuario;
		
		try {
			usuario = query.getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
			usuario = null;
		}
		
		return usuario;
	}
	
}
