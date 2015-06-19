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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import br.com.agenda.enums.Finalizado;
import br.com.agenda.enums.Frequencia;

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
    private Frequencia frequencia;
    
    @NotNull
    private Integer valorFrequencia;
    
    @Enumerated(EnumType.STRING)
    private Finalizado finalizado;
    
    @ManyToOne
    private Usuario usuario;
    
    @Transient
    private DateTime dataCompleta;
    
    //construtor
    public Tarefas(){
		this.valorFrequencia = new Integer(0);
	}

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

	public Frequencia getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

	public Integer getValorFrequencia() {
		return valorFrequencia;
	}

	public void setValorFrequencia(Integer valorFrequencia) {
		this.valorFrequencia = valorFrequencia;
	}

	public DateTime getDataCompleta() {
		return dataCompleta;
	}

	public void setDataCompleta(DateTime dataCompleta) {
		this.dataCompleta = dataCompleta;
	}
	
}
