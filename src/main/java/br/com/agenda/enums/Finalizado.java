package br.com.agenda.enums;

public enum Finalizado {
	SIM("Finalizado"), NAO("Não Finalizado");
	
	private final String name;
	
	Finalizado (String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
}
