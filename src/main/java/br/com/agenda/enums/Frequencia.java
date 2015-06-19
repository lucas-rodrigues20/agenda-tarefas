package br.com.agenda.enums;

public enum Frequencia {
	NENHUMA ("Nenhuma"),
	HORA ("Hora(s)"),
	DIA ("Dia(s)"),
	SEMANA ("Semana(s)"),
	MES ("Mês(s)"),
	ANO ("Ano(s)");
	
	private final String name;
	
	Frequencia (String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
