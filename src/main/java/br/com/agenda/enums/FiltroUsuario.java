package br.com.agenda.enums;

public enum FiltroUsuario {
	ID("Id"),
	NOME("Nome"),
	EMAIL("Email");
	
	private final String name;
	
	FiltroUsuario (String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
