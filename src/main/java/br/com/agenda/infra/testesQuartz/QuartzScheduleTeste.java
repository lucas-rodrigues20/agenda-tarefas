package br.com.agenda.infra.testesQuartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import com.amazonaws.services.importexport.model.Job;

public class QuartzScheduleTeste {
	
	public QuartzScheduleTeste() throws Exception{		
		
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		
		JobDetailImpl jd = new JobDetailImpl("job1", "group1", Quartzjob.class);
		CronTriggerImpl ct = new CronTriggerImpl("cronTrigger", "group2", "0/5 * * * * ?");
		
		//http://javacodingtutorial.blogspot.com.br/2014/01/quartz-schedulerusing-maven.html
		
		sched.scheduleJob(jd, ct);
		sched.start();
		
	}
	
	public static void main(String[] args) {
		try {
			new QuartzScheduleTeste();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
