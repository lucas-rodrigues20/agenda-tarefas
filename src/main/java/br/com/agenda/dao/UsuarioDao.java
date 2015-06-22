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
	
	public List<Usuario> listaEspecifica(Integer id, String nome, String email){
		TypedQuery<Usuario> query;
		
		if(id != null){
			query = manager.createQuery("select u from Usuario u where u.id=:id", Usuario.class);
			query.setParameter("id", id);
		}else if(!nome.isEmpty()){
			query = manager.createQuery("select u from Usuario u where u.nome like :nome", Usuario.class);
			query.setParameter("nome", "%"+nome+"%");
		}else if(!email.isEmpty()){
			query = manager.createQuery("select u from Usuario u where u.email like :email", Usuario.class);
			query.setParameter("email", "%"+email+"%");
		}else{
			query = manager.createQuery("select u from Usuario u", Usuario.class);
		}
		
		return query.getResultList();
	}
	
}
