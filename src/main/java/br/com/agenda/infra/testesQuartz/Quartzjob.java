package br.com.agenda.infra.testesQuartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Quartzjob implements Job {

	TesteMensagem msg = new TesteMensagem();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String texto = dataMap.getString("msg");
		msg.exibirMensagem(texto);
	}

}
