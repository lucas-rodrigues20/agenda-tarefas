package br.com.agenda.infra;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.simplemail.Mailer;

public class EnviadorDeEmail {
	
	private Mailer mailer;

	@Inject
	public EnviadorDeEmail(Mailer mailer){
		this.mailer = mailer;
	}
	
	public EnviadorDeEmail(){
		
	}
	
	public void Enviar(String assunto, String mensagem, String destinatario){
		Email email = new SimpleEmail();
		email.setSubject(assunto);
		try {
			email.setMsg(mensagem);
			email.addTo(destinatario);
			mailer.send(email);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
}
