package br.com.agenda.controllers;

import javax.inject.Inject;

import br.com.agenda.dao.UsuarioDao;
import br.com.agenda.modelos.Usuario;
import br.com.agenda.seguranca.Open;
import br.com.agenda.seguranca.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class LoginController {
	
	private UsuarioDao usuarioDao;
	private UsuarioLogado usuarioLogado;
	private Result result;
	private Validator validator;

	@Inject
	public LoginController(UsuarioDao usuarioDao, UsuarioLogado usuarioLogado, Result result, Validator validator){
		this.usuarioDao = usuarioDao;
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
	}
	
	public LoginController(){
		
	}
	
	@Open
	public void formLogin(){
		
	}
	
	@Open
	public void autentica(String email, String senha){
		Usuario usuario = usuarioDao.busca(email, senha);
		if(usuario != null){
			usuarioLogado.fazLogin(usuario);
			result.redirectTo(UsuarioController.class).lista();
		}else{
			validator.add(new SimpleMessage("login_invalido", "Email ou Senha incorretos"));
			validator.onErrorRedirectTo(this).formLogin();
		}
	}
	
	@Open
	public void desloga(){
		usuarioLogado.desloga();
		result.redirectTo(this).formLogin();
	}
	
}