package br.com.agenda.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.agenda.dao.UsuarioDao;
import br.com.agenda.modelos.Usuario;
import br.com.agenda.seguranca.Open;
import br.com.agenda.seguranca.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class UsuarioController {
	
	private UsuarioDao usuarioDao;
	private Result result;
	private Validator validator;
	private UsuarioLogado usuarioLogado;

	@Inject
	public UsuarioController(UsuarioDao usuarioDao, Result result, Validator validator,
			UsuarioLogado usuarioLogado){
		this.usuarioDao = usuarioDao;
		this.result = result;
		this.validator = validator;
		this.usuarioLogado = usuarioLogado;
	}
	
	public UsuarioController(){
		
	}
	
	@Open
	@Path("/")
	public void formCadastro(){
		if(usuarioLogado.isLogado()){
			result.redirectTo(TarefaController.class).lista();
		}
	}
	
	@Open
	@IncludeParameters
	public void adiciona(@Valid Usuario usuario){
		validator.onErrorRedirectTo(this).formCadastro();
		usuarioDao.adiciona(usuario);
		result.redirectTo(LoginController.class).formLogin();
	}
	
	public void lista(){
		List<Usuario> ltUsuarios = usuarioDao.lista();
		result.include("ltUsuarios", ltUsuarios);
	}
	
}
