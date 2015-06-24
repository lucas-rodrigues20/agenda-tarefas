package br.com.agenda.infra;

import java.util.Calendar;

import org.joda.time.DateTime;

import br.com.agenda.enums.Frequencia;
import br.com.agenda.modelos.Tarefas;

public class Utilidades {
	
	//Retorna a data completa unindo a data com a hora
	public DateTime formataHora(Tarefas t){
		String hora = t.getHorario().substring(0, 2);
		String minuto = t.getHorario().substring(3, 5);
		
		t.getData().set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
		t.getData().set(Calendar.MINUTE, Integer.parseInt(minuto));
		
		DateTime dtFormatada = new DateTime(t.getData());
		
		return dtFormatada;
	}
	
	public DateTime atualizarDataParaHoje(Tarefas t){
		Calendar data = Calendar.getInstance();
		String hora = t.getHorario().substring(0, 2);
		String minuto = t.getHorario().substring(3, 5);
		
		data.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
		data.set(Calendar.MINUTE, Integer.parseInt(minuto));
		
		DateTime dtFormatada = new DateTime(data);
		
		return dtFormatada;
	}
	
	public DateTime atualizarDatasAtrasadas(Tarefas t){
		DateTime data = new DateTime();
		
		if(t.getFrequencia().equals(Frequencia.NENHUMA)){
			data = atualizarDataParaHoje(t);
			while(data.isBeforeNow()){
				data = data.plusHours(12);
			}
		}else if(t.getFrequencia().equals(Frequencia.HORA)){
			data = atualizarDataParaHoje(t);
			while(data.isBeforeNow()){
				data = data.plusHours(t.getValorFrequencia());
			}
		}else if(t.getFrequencia().equals(Frequencia.DIA)){
			data = formataHora(t);
			while(data.isBeforeNow()){
				data = data.plusDays(t.getValorFrequencia());
			}
		}else if(t.getFrequencia().equals(Frequencia.SEMANA)){
			data = formataHora(t);
			while(data.isBeforeNow()){
				data = data.plusWeeks(t.getValorFrequencia());
			}
		}else if(t.getFrequencia().equals(Frequencia.MES)){
			data = formataHora(t);
			while(data.isBeforeNow()){
				data = data.plusMonths(t.getValorFrequencia());
			}
		}else if(t.getFrequencia().equals(Frequencia.ANO)){
			data = formataHora(t);
			while(data.isBeforeNow()){
				data = data.plusYears(t.getValorFrequencia());
			}
		}
		return data;
	}
	
}
