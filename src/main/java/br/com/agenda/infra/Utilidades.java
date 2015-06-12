package br.com.agenda.infra;

import java.util.Calendar;

import org.joda.time.DateTime;

import br.com.agenda.modelos.Tarefas;

public class Utilidades {
	
	public DateTime formataHora(Tarefas t){
		String hora = t.getHorario().substring(0, 2);
		String minuto = t.getHorario().substring(3, 5);
		
		t.getData().set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
		t.getData().set(Calendar.MINUTE, Integer.parseInt(minuto));
		
		DateTime dtFormatada = new DateTime(t.getData());
		
		return dtFormatada;
	}
	
}
