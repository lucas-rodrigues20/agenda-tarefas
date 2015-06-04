package br.com.agenda.infra.testesQuartz;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.agenda.infra.EnviadorDeEmail;
import br.com.caelum.vraptor.simplemail.Mailer;

public class EnviarEmailJob implements Job {
	
	private EnviadorDeEmail enviadorEmail;
	private Mailer mailer;

	@Inject
	public EnviarEmailJob(EnviadorDeEmail enviadorEmail, Mailer mailer){
		this.enviadorEmail = enviadorEmail;
		this.mailer = mailer;
	}
	
	public EnviarEmailJob(){
		
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("ENTROU NO JOB");
		Enviar("TesteJob", "Teste do funcionamento do Job Quartz", "lrpg_doidao@hotmail.com");
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
	
	//http://pt.stackoverflow.com/questions/2781/como-usar-o-quartz-scheduler-com-o-demoiselle

}
