package br.com.agenda.infra;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.agenda.enums.TipoEmail;
import br.com.agenda.modelos.Tarefas;
import br.com.caelum.vraptor.simplemail.Mailer;

public class EnviadorDeEmail {
	
	private Mailer mailer;

	@Inject
	public EnviadorDeEmail(Mailer mailer){
		this.mailer = mailer;
	}
	
	public EnviadorDeEmail(){
		
	}
	
	public void Enviar(Tarefas tarefa, TipoEmail tpEmail){
		Email email = new SimpleEmail();
		
		if(tpEmail.equals(TipoEmail.LEMBRETETAREFA)){
			email = montarEmailLembreteTarefa(tarefa);
		}
		
		try {
			mailer.send(email);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	public Email montarEmailLembreteTarefa(Tarefas t){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Email email = new SimpleEmail();
		
		try {
			email.setSubject("Não perca seu compromisso");
			email.setMsg("Não perca seu compromisso!<br>Está quase na hora da tarefa que você agendou:<br>"
					+ t.getDescricao()
					+ "<br>"
					+ sdf.format(t.getData().getTime()) + " às " + t.getHorario());
			email.addTo(t.getUsuario().getEmail());
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		return email;
	}
	
}
