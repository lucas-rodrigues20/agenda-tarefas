package br.com.agenda.infra.testesQuartz;

import org.joda.time.DateTime;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import br.com.agenda.modelos.Tarefas;

public class QuartzScheduleMain {
	
	public void agendarEnvioDeEmail(Tarefas tarefa) throws SchedulerException{
		JobDetail job = JobBuilder.newJob(EnviarEmailJob.class).build();
		
		String data = gerarExpressaoCron(tarefa);
		
		System.out.println(data);
		//SEGUNDOS - MINUTOS - HORA - DIA - MES - DIA DA SEMANA(?) - ANO(OPCIONAL)
		//"* * * * * ? *"
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(data);
		
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
		
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		
		scheduler.start();
		
		scheduler.scheduleJob(job, trigger);
	}
	
	private String gerarExpressaoCron(Tarefas tarefa){
		DateTime dt = new DateTime(tarefa.getData());
		
		Integer dia = dt.getDayOfMonth();
		Integer mes = dt.getMonthOfYear();
		Integer ano = dt.getYear();
		
		String hora = tarefa.getHorario().substring(0, 2);
		String minuto = tarefa.getHorario().substring(3, 5);
		String segundos = "0";
		
		String espaco = " ";
		
		String expressao = segundos + espaco +
				minuto + espaco +
				hora + espaco +
				dia + espaco +
				mes + espaco +
				"?" + espaco +
				ano;
		
		return expressao;
				
	}
	
	public void testeJoda(Tarefas tarefa){
		DateTime dt = new DateTime(tarefa.getData());
		System.out.println(dt.getDayOfMonth());
		System.out.println(dt.getMonthOfYear());
		System.out.println(dt.getYear());
	}
	
	//http://www.guj.com.br/java/202515-resolvidovraptor--hibernate--joda-time
	
}
