package br.com.agenda.infra.tasks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.joda.time.DateTime;

import br.com.agenda.enums.TipoEmail;
import br.com.agenda.infra.EnviadorDeEmail;
import br.com.agenda.infra.Utilidades;
import br.com.agenda.modelos.Tarefas;
import br.com.caelum.vraptor.tasks.Task;
import br.com.caelum.vraptor.tasks.scheduler.Scheduled;

@ApplicationScoped
@Scheduled(fixedRate=300000)
public class AgendadorDeEmail implements Task {

	private EnviadorDeEmail enviador;
	private List<Tarefas> ltTarefas;
	private Utilidades utilidades;

	@Inject
	public AgendadorDeEmail(EnviadorDeEmail enviador, Utilidades utilidades){
		this.enviador = enviador;
		this.utilidades = utilidades;
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
				DateTime dtTarefa = utilidades.formataHora(t);
				
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
	
}
