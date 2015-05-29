package br.com.agenda.infra.testesQuartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzSchedule {
	
	public static void main(String[] args) throws SchedulerException {
		
		JobDetail job = JobBuilder.newJob(Quartzjob.class).build();
		job.getJobDataMap().put("msg", "CHUPA MUNDO!");
		
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
		
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
		
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		
		scheduler.start();
		
		scheduler.scheduleJob(job, trigger);
		
	}
	
}
