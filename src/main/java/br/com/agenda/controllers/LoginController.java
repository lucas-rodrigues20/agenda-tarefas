package br.com.agenda.controllers;

import javax.inject.Inject;

import br.com.agenda.dao.UsuarioDao;
import br.com.agenda.enums.TipoEmail;
import br.com.agenda.infra.EnviadorDeEmail;
import br.com.agenda.modelos.Usuario;
import br.com.agenda.seguranca.Open;
import br.com.agenda.seguranca.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class LoginController {
	
	private UsuarioDao usuarioDao;
	private UsuarioLogado usuarioLogado;
	private Result result;
	private Validator validator;
	private EnviadorDeEmail enviadorDeEmail;

	@Inject
	public LoginController(UsuarioDao usuarioDao, UsuarioLogado usuarioLogado, Result result,
			Validator validator, EnviadorDeEmail enviadorDeEmail){
		this.usuarioDao = usuarioDao;
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
		this.enviadorDeEmail = enviadorDeEmail;
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
			
			if(usuarioLogado.isAdmin()){
				result.redirectTo(UsuarioController.class).lista(null, null);
			}else{
				result.redirectTo(TarefaController.class).lista();				
			}
		}else{
			validator.add(new SimpleMessage("login_invalido", "Email ou Senha incorretos"));
			validator.onErrorRedirectTo(this).formLogin();
		}
	}
	
	@Open
	public void desloga(){
		usuarioLogado.desloga();
		result.redirectTo(UsuarioController.class).formCadastro();
	}
	
	@Open
	@IncludeParameters
	public void recuperaSenha(String email){
		Usuario usuario = usuarioDao.buscaUsuarioPorEmail(email);
		
		if(usuario != null){
			enviadorDeEmail.EnviarEmailUsuario(usuario, TipoEmail.RECUPERARSENHA);
			result.include("mensagem", "Suas dados foram enviados no seu email");
			result.redirectTo(this).formLogin();
		}else{
			validator.add(new SimpleMessage("email_invalido", "Email informado não existe no sistema"));
			validator.onErrorRedirectTo(this).formRecuperarSenha();
		}
	}

	@Open
	public void formRecuperarSenha() {
		
	}
	
}