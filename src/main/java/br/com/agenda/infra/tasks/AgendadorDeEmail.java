package br.com.agenda.infra.tasks;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.joda.time.DateTime;

import br.com.agenda.enums.Frequencia;
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
	private Map<Integer, Tarefas> ltTarefas;
	private Utilidades utilidades;
	private boolean reagendamentoFeito;

	@Inject
	public AgendadorDeEmail(EnviadorDeEmail enviador, Utilidades utilidades){
		this.enviador = enviador;
		this.utilidades = utilidades;
	}
	
	public AgendadorDeEmail(){
		
	}
	
	@PostConstruct
	public void inicalizarLista(){
		ltTarefas = new HashMap<Integer, Tarefas>();
		setReagendamentoFeito(false);
	}
	
	@Override
	public void execute() {
		
		System.out.println("EXECUTOU A TASK");

		if(!ltTarefas.isEmpty()){

			DateTime dtAtual = new DateTime();
			
			for (Integer chave : ltTarefas.keySet()) {
				Tarefas t = ltTarefas.get(chave);
				
				//Se a dtTarefa for depois da dtAtual E for nos proximos 5 minutos
				if (t.getDataCompleta().isAfter(dtAtual)
						&& (t.getDataCompleta().isBefore(dtAtual.plusMinutes(5))
								|| t.getDataCompleta().equals(dtAtual.plusMinutes(5)))) {
					if(t.isPrimeiraExecucao()){						
						enviador.EnviarEmailTarefa(t, TipoEmail.LEMBRETETAREFA);
						t.setPrimeiraExecucao(false);
					}else{
						enviador.EnviarEmailTarefa(t, TipoEmail.LEMBRETETAREFANAOFINALIZADA);
					}
					controlarFrequencia(t);
				}
			}
		}
		
	}
	
	public void agendarNovaTarefa(Tarefas t){
		t.setDataCompleta(utilidades.formataHora(t));
		t.setPrimeiraExecucao(true);
		ltTarefas.put(t.getId(), t);
	}
	
	public void reagendarTarefaAtrasada(Tarefas t) {
		t.setDataCompleta(utilidades.atualizarDatasAtrasadas(t));
		t.setPrimeiraExecucao(false);
		ltTarefas.put(t.getId(), t);
	}
	
	public void removerTarefa(Tarefas t){
		ltTarefas.remove(t.getId());
	}
	
	public void controlarFrequencia(Tarefas t){
		if(t.getFrequencia().equals(Frequencia.NENHUMA)){
			t.setDataCompleta(t.getDataCompleta().plusHours(12));
		}else if(t.getFrequencia().equals(Frequencia.HORA)){
			t.setDataCompleta(t.getDataCompleta().plusHours(t.getValorFrequencia()));
		}else if(t.getFrequencia().equals(Frequencia.DIA)){
			t.setDataCompleta(t.getDataCompleta().plusDays(t.getValorFrequencia()));
		}else if(t.getFrequencia().equals(Frequencia.SEMANA)){
			t.setDataCompleta(t.getDataCompleta().plusWeeks(t.getValorFrequencia()));
		}else if(t.getFrequencia().equals(Frequencia.MES)){
			t.setDataCompleta(t.getDataCompleta().plusMonths(t.getValorFrequencia()));
		}else if(t.getFrequencia().equals(Frequencia.ANO)){
			t.setDataCompleta(t.getDataCompleta().plusYears(t.getValorFrequencia()));
		}
	}

	public boolean isReagendamentoFeito() {
		return reagendamentoFeito;
	}

	public void setReagendamentoFeito(boolean reagendamentoFeito) {
		this.reagendamentoFeito = reagendamentoFeito;
	}
	
}
