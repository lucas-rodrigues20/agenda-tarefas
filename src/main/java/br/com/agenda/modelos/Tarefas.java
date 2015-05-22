package br.com.agenda.modelos;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.agenda.enums.Finalizado;

@Entity
public class Tarefas {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @NotEmpty
    private String descricao;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar data;
    
    @NotEmpty
    @Pattern(regexp="\\d{2}:\\d{2}")
    private String horario;
    
    @Enumerated(EnumType.STRING)
    private Finalizado finalizado;
    
    @ManyToOne
    private Usuario usuario;

    //getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Finalizado getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Finalizado finalizado) {
		this.finalizado = finalizado;
	}
	
}
