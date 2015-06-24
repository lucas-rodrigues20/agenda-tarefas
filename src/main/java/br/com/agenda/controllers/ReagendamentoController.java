package br.com.agenda.controllers;

import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;

import br.com.agenda.dao.TarefaDao;
import br.com.agenda.infra.Utilidades;
import br.com.agenda.infra.tasks.AgendadorDeEmail;
import br.com.agenda.modelos.Tarefas;
import br.com.agenda.seguranca.OpenAdmin;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;

@Controller
public class ReagendamentoController {
	
	
	private TarefaDao tarefaDao;
	private Result result;
	private Utilidades utilidades;
	private AgendadorDeEmail agendador;

	@Inject
	public ReagendamentoController(TarefaDao tarefaDao, Result result, Utilidades utilidades,
			AgendadorDeEmail agendador){
		this.tarefaDao = tarefaDao;
		this.result = result;
		this.utilidades = utilidades;
		this.agendador = agendador;
	}
	
	public ReagendamentoController(){
		
	}
	
	@OpenAdmin
	public void formReagendamento(){
		result.include("reagendamentoFeito", agendador.isReagendamentoFeito());
	}
	
	@OpenAdmin
	public void reagendar(){
		List<Tarefas> ltReagendar = tarefaDao.listarTarefasParaReagendar();
		
		for (Tarefas t : ltReagendar) {
			DateTime dtTarefa = utilidades.formataHora(t);
			if(dtTarefa.isAfterNow()){
				agendador.agendarNovaTarefa(t);
			}else{
				agendador.reagendarTarefaAtrasada(t);
			}
		}
		
		agendador.setReagendamentoFeito(true);
		
		result.include("mensagem", "Reagendamento completado");
		result.redirectTo(this).formReagendamento();
	}
	
}
