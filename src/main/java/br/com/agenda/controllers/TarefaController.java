package br.com.agenda.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.joda.time.DateTime;

import br.com.agenda.dao.TarefaDao;
import br.com.agenda.enums.Finalizado;
import br.com.agenda.infra.EnviadorDeEmail;
import br.com.agenda.infra.Utilidades;
import br.com.agenda.infra.tasks.AgendadorDeEmail;
import br.com.agenda.modelos.Tarefas;
import br.com.agenda.seguranca.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class TarefaController {
	
	private TarefaDao tarefaDao;
	private Validator validator;
	private Result result;
	private UsuarioLogado usuarioLogado;
	private EnviadorDeEmail enviadorEmail;
	private AgendadorDeEmail agendador;
	private Utilidades utilidades;

	@Inject
	public TarefaController(TarefaDao tarefaDao, Validator validator,
			Result result, UsuarioLogado usuarioLogado, EnviadorDeEmail enviadorEmail,
			AgendadorDeEmail agendador, Utilidades utilidades) {
		this.tarefaDao = tarefaDao;
		this.validator = validator;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
		this.enviadorEmail = enviadorEmail;
		this.agendador = agendador;
		this.utilidades = utilidades;
	}
	
	public TarefaController(){
		
	}
	
	public void formTarefa(){
		
	}
	
	@IncludeParameters
	public void adiciona(@Valid Tarefas tarefa){
		validator.onErrorRedirectTo(this).formTarefa();
		validarData(tarefa);
		
		tarefa.setUsuario(usuarioLogado.getUsuario());
		tarefa.setFinalizado(Finalizado.NAO);
		tarefaDao.adiciona(tarefa);
		
		agendador.agendarNovaTarefa(tarefa);
		
		result.include("mensagem", "A nova tarefa foi agendada");
		result.redirectTo(this).lista();
	}


	public void lista(){
		List<Tarefas> ltTarefas = tarefaDao.lista(usuarioLogado.getUsuario());
		result.include("ltTarefas", ltTarefas);
	}
	
	public void remove(Tarefas tarefa){
		tarefa = tarefaDao.listaUmaTarefa(tarefa, usuarioLogado.getUsuario());
		
		if(tarefa == null){
			validator.add(new SimpleMessage("exclusao_invalida", "Você não pode excluir esta tarefa"));
			validator.onErrorRedirectTo(this).lista();
		}
		
		tarefaDao.remove(tarefa);
		result.include("mensagem", "A tarefa foi Removida");			
		result.redirectTo(this).lista();
	}
	
	public void formEdita(){
		
	}
	
	public void edita(Tarefas tarefa){
		tarefa = tarefaDao.listaUmaTarefa(tarefa, usuarioLogado.getUsuario());
		
		if(tarefa == null){
			validator.add(new SimpleMessage("edicao_invalida", "Você não pode editar esta tarefa"));
			validator.onErrorRedirectTo(this).lista();
		}
		
		result.include("tarefa", tarefa);
		result.redirectTo(this).formEdita();
	}
	
	@IncludeParameters
	public void salvaEdicao(@Valid Tarefas tarefa){
		validator.onErrorRedirectTo(this).formEdita();
		validarData(tarefa);
		
		tarefa.setUsuario(usuarioLogado.getUsuario());
		tarefa.setFinalizado(Finalizado.NAO);
		tarefaDao.edita(tarefa);
		
		agendador.agendarNovaTarefa(tarefa);
		
		result.include("mensagem", "A tarefa foi alterada");
		result.redirectTo(this).lista();
	}
	
	public void validarData(Tarefas t){
		DateTime dtTarefa = utilidades.formataHora(t);
		
		if(dtTarefa.isBeforeNow()){
			validator.add(new SimpleMessage("data_invalida", "A Data e/ou Horário não podem ser anteriores a data atual"));
			validator.onErrorRedirectTo(this).formTarefa();
		}
	}
	
}
