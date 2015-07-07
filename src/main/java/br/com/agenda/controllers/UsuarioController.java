package br.com.agenda.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.agenda.dao.TarefaDao;
import br.com.agenda.dao.UsuarioDao;
import br.com.agenda.enums.FiltroUsuario;
import br.com.agenda.enums.TipoEmail;
import br.com.agenda.enums.TipoUsuario;
import br.com.agenda.infra.EnviadorDeEmail;
import br.com.agenda.infra.tasks.AgendadorDeEmail;
import br.com.agenda.modelos.Tarefas;
import br.com.agenda.modelos.Usuario;
import br.com.agenda.seguranca.Open;
import br.com.agenda.seguranca.OpenAdmin;
import br.com.agenda.seguranca.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class UsuarioController {
	
	private UsuarioDao usuarioDao;
	private Result result;
	private Validator validator;
	private UsuarioLogado usuarioLogado;
	private EnviadorDeEmail enviadorDeEmail;
	private TarefaDao tarefaDao;
	private AgendadorDeEmail agendador;

	@Inject
	public UsuarioController(UsuarioDao usuarioDao, Result result, Validator validator,
			UsuarioLogado usuarioLogado, EnviadorDeEmail enviadorDeEmail,
			TarefaDao tarefaDao, AgendadorDeEmail agendador){
		this.usuarioDao = usuarioDao;
		this.result = result;
		this.validator = validator;
		this.usuarioLogado = usuarioLogado;
		this.enviadorDeEmail = enviadorDeEmail;
		this.tarefaDao = tarefaDao;
		this.agendador = agendador;
	}
	
	public UsuarioController(){
		
	}
	
	@Open
	@Path("/")
	public void formCadastro(){
		if(usuarioLogado.isLogado()){
			if(usuarioLogado.isAdmin()){
				result.redirectTo(this).lista(null, null);
			}else{				
				result.redirectTo(TarefaController.class).lista();
			}
		}
	}
	
	@Open
	@IncludeParameters
	public void adiciona(@Valid Usuario usuario){
		validator.onErrorRedirectTo(this).formCadastro();
		
		validarEmailCadastro(usuario);
		
		usuario.setTipoUsuario(TipoUsuario.USUARIO);
		usuarioDao.adiciona(usuario);
		enviadorDeEmail.EnviarEmailUsuario(usuario, TipoEmail.CONTACRIADA);
		
		result.include("mensagem", "Conta criada com sucesso");
		result.redirectTo(LoginController.class).formLogin();
	}
	
	private void validarEmailCadastro(Usuario usuario) {
		usuario = usuarioDao.buscaUsuarioPorEmail(usuario.getEmail());
		
		if(usuario != null){
			validator.add(new SimpleMessage("email_invalido", "O email informado já existe no sistema"));
			validator.onErrorRedirectTo(this).formCadastro();
		}
	}
	
	public void formEdita(){
		Usuario usuario = usuarioDao.buscaUsuarioPorId(usuarioLogado.getUsuario().getId());
		result.include("usuario", usuario);
	}
	
	@IncludeParameters
	public void editarUsuario(@Valid Usuario usuario){
		validator.onErrorRedirectTo(this).formEdita();
		
		validarEmailEdicao(usuario);
		
		usuario.setTipoUsuario(TipoUsuario.USUARIO);
		usuarioDao.edita(usuario);
		enviadorDeEmail.EnviarEmailUsuario(usuario, TipoEmail.CONTAATUALIZADA);
		
		atualizarEmailTarefasNaoFinalizadas(usuario);
		
		result.include("mensagem", "Informações atualizadas");
		result.redirectTo(this).formEdita();
	}
	
	private void validarEmailEdicao(Usuario usuario) {
		usuario = usuarioDao.buscaUsuarioPorEmail(usuario.getEmail());
		
		if(usuario != null && usuario.getId() != usuarioLogado.getUsuario().getId()){
			validator.add(new SimpleMessage("email_invalido", "O email informado já existe no sistema"));
			validator.onErrorRedirectTo(this).formEdita();
		}
	}
	
	public void atualizarEmailTarefasNaoFinalizadas(Usuario usuario){
		List<Tarefas> tarefasNaoFinalizadas = tarefaDao.buscarTarefasNaoFinalizadasPorUsuario(usuario);
		agendador.atualizaEmail(tarefasNaoFinalizadas, usuario);
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
