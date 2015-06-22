package br.com.agenda.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.agenda.dao.UsuarioDao;
import br.com.agenda.enums.FiltroUsuario;
import br.com.agenda.enums.TipoUsuario;
import br.com.agenda.modelos.Usuario;
import br.com.agenda.seguranca.Open;
import br.com.agenda.seguranca.OpenAdmin;
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
		usuario.setTipoUsuario(TipoUsuario.USUARIO);
		usuarioDao.adiciona(usuario);
		result.redirectTo(LoginController.class).formLogin();
	}
	
	@OpenAdmin
	@IncludeParameters
	public void lista(String parametro, FiltroUsuario filtro){
		Integer id = null;
		String nome = "";
		String email = "";
		
		if(filtro != null){			
			if(filtro.equals(FiltroUsuario.ID)){
				try {
					id = Integer.parseInt(parametro);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(filtro.equals(FiltroUsuario.NOME)){
				nome = parametro;
			}else if(filtro.equals(FiltroUsuario.EMAIL)){
				email = parametro;
			}
		}
		
		List<Usuario> ltUsuarios = usuarioDao.listaEspecifica(id, nome, email);
		result.include("ltUsuarios", ltUsuarios);
		result.include("filtroUsuario", FiltroUsuario.values());
	}
	
}
