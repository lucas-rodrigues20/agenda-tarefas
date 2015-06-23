package br.com.agenda.seguranca;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.agenda.enums.TipoUsuario;
import br.com.agenda.modelos.Usuario;

@Named
@SessionScoped
public class UsuarioLogado implements Serializable {

	private static final long serialVersionUID = -8223502417221046912L;

	private Usuario usuario;
	
	public void fazLogin(Usuario usuario){
		this.usuario = usuario;
	}
	
	public void desloga(){
		this.usuario = null;
	}
	
	public boolean isLogado(){
		return this.usuario != null;
	}
	
	public boolean isAdmin(){
		return this.usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR);
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
