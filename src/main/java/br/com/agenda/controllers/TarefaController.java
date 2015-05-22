package br.com.agenda.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.agenda.dao.TarefaDao;
import br.com.agenda.enums.Finalizado;
import br.com.agenda.infra.EnviadorDeEmail;
import br.com.agenda.modelos.Tarefas;
import br.com.agenda.seguranca.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class TarefaController {
	
	private TarefaDao tarefaDao;
	private Validator validator;
	private Result result;
	private UsuarioLogado usuarioLogado;
	private EnviadorDeEmail enviadorEmail;

	@Inject
	public TarefaController(TarefaDao tarefaDao, Validator validator,
			Result result, UsuarioLogado usuarioLogado, EnviadorDeEmail enviadorEmail) {
		this.tarefaDao = tarefaDao;
		this.validator = validator;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
		this.enviadorEmail = enviadorEmail;
	}
	
	public TarefaController(){
		
	}
	
	public void formTarefa(){
		
	}
	
	@IncludeParameters
	public void adiciona(@Valid Tarefas tarefa){
		validator.onErrorRedirectTo(this).formTarefa();
		tarefa.setUsuario(usuarioLogado.getUsuario());
		tarefa.setFinalizado(Finalizado.NAO);
		tarefaDao.adiciona(tarefa);
		
		enviadorEmail.Enviar("Teste", "Teste Teste", "lrpg_doidao@hotmail.com");
		
		result.redirectTo(this).lista();
	}

	public void lista(){
		List<Tarefas> ltTarefas = tarefaDao.lista(usuarioLogado.getUsuario());
		result.include("ltTarefas", ltTarefas);
	}
	
}
