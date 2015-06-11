package br.com.agenda.infra.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.joda.time.DateTime;

import br.com.agenda.enums.TipoEmail;
import br.com.agenda.infra.EnviadorDeEmail;
import br.com.agenda.modelos.Tarefas;
import br.com.caelum.vraptor.tasks.Task;
import br.com.caelum.vraptor.tasks.scheduler.Scheduled;

@ApplicationScoped
@Scheduled(fixedRate=300000)
public class AgendadorDeEmail implements Task {

	private EnviadorDeEmail enviador;
	private List<Tarefas> ltTarefas;

	@Inject
	public AgendadorDeEmail(EnviadorDeEmail enviador){
		this.enviador = enviador;
	}
	
	public AgendadorDeEmail(){
		
	}
	
	@PostConstruct
	public void inicalizarLista(){
		ltTarefas = new ArrayList<Tarefas>();
	}
	
	@Override
	public void execute() {
		
		System.out.println("EXECUTOU A TASK");

		if(!ltTarefas.isEmpty()){

			DateTime dtAtual = new DateTime();
			
			for (Tarefas t : ltTarefas) {
				DateTime dtTarefa = formataHora(t);
				
				//Se a dtTarefa for depois da dtAtual E for nos proximos 5 minutos
				if (dtTarefa.isAfter(dtAtual)
						&& (dtTarefa.isBefore(dtAtual.plusMinutes(5))
								|| dtTarefa.equals(dtAtual.plusMinutes(5)))) {
					enviador.Enviar(t, TipoEmail.LEMBRETETAREFA);
					ltTarefas.remove(t);
				}
			}
		}
		
	}
	
	public void agendarNovaTarefa(Tarefas t){
		ltTarefas.add(t);
	}
	
	public DateTime formataHora(Tarefas t){
		String hora = t.getHorario().substring(0, 2);
		String minuto = t.getHorario().substring(3, 5);
		
		t.getData().set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
		t.getData().set(Calendar.MINUTE, Integer.parseInt(minuto));
		
		DateTime dtFormatada = new DateTime(t.getData());
		
		return dtFormatada;
	}

}
