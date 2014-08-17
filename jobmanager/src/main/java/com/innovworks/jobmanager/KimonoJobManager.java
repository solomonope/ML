/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovworks.jobmanager;

import java.util.ArrayList;
import java.util.List;
import org.quartz.Job;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Folorunsho Solomon
 */
@Component
public class KimonoJobManager {

    private Scheduler scheduler;
    private final List<KimonoJob> kimonoJobs;
    private final SchedulerFactory schedulerFactory;

    private JobDetail creatJobDetail(String jobName, String jobGroup, Class<Job> jobClss) {
        return newJob(jobClss).withIdentity(jobName, jobGroup).build();
    }

    private Trigger createJobTrigger(String triggerName, String triggerGroup, int interval) {
        return newTrigger().withIdentity(triggerName, triggerGroup).startNow().withSchedule(simpleSchedule().withIntervalInSeconds(interval).repeatForever()).build();
    }

    public List<KimonoJob> getAllScheduledJobs(KimonoJob job) throws SchedulerException {
        List<JobExecutionContext> jobExecutionContexts = scheduler.getCurrentlyExecutingJobs();
        for (JobExecutionContext jobExecutionContext : jobExecutionContexts) {
            for (KimonoJob kimonoJob : kimonoJobs) {
                if (kimonoJob.getName().equalsIgnoreCase(jobExecutionContext.getTrigger().getJobKey().getName()) && kimonoJob.getGroup().equalsIgnoreCase(jobExecutionContext.getTrigger().getJobKey().getGroup())) {
                    kimonoJob.setStartTime(jobExecutionContext.getTrigger().getStartTime());
                    kimonoJob.setEndTime(jobExecutionContext.getTrigger().getEndTime());
                    kimonoJob.setNextFireTime(jobExecutionContext.getTrigger().getNextFireTime());
                    kimonoJob.setPreviousFireTime(jobExecutionContext.getTrigger().getPreviousFireTime());
                    kimonoJob.setMayFireAgain(jobExecutionContext.getTrigger().mayFireAgain());
                }
            }

        }
        return this.kimonoJobs;
    }

    public KimonoJobManager() throws SchedulerException {
        kimonoJobs = new ArrayList<>();
        schedulerFactory = new StdSchedulerFactory();
        init();
    }

    public void init() throws SchedulerException {
        scheduler = schedulerFactory.getScheduler();
        scheduler.start();
    }

    public void scheduleJob(KimonoJob kimonoJob) throws SchedulerException {
        this.scheduler.scheduleJob(creatJobDetail(kimonoJob.getName(), kimonoJob.getGroup(), kimonoJob.getJobClass()), createJobTrigger(kimonoJob.getName(), kimonoJob.getGroup(), kimonoJob.getJobInterval()));
        kimonoJobs.add(kimonoJob);
    }

    public void removeJob(KimonoJob kimonoJob) throws SchedulerException {
        List<JobExecutionContext> jobExecutionContexts = scheduler.getCurrentlyExecutingJobs();
        for (JobExecutionContext jobExecutionContext : jobExecutionContexts) {
            if (kimonoJob.getName().equalsIgnoreCase(jobExecutionContext.getTrigger().getJobKey().getName()) && kimonoJob.getGroup().equalsIgnoreCase(jobExecutionContext.getTrigger().getJobKey().getGroup())) {
                scheduler.deleteJob(jobExecutionContext.getTrigger().getJobKey());
                kimonoJobs.remove(kimonoJob);
            }
        }
    }

    public void stopJob(KimonoJob kimonoJob) throws SchedulerException {
        List<JobExecutionContext> jobExecutionContexts = scheduler.getCurrentlyExecutingJobs();
        for (JobExecutionContext jobExecutionContext : jobExecutionContexts) {
            if (kimonoJob.getName().equalsIgnoreCase(jobExecutionContext.getTrigger().getJobKey().getName()) && kimonoJob.getGroup().equalsIgnoreCase(jobExecutionContext.getTrigger().getJobKey().getGroup())) {
                scheduler.unscheduleJob(jobExecutionContext.getTrigger().getKey());
            }
        }

    }

    public void pauseJob(KimonoJob kimonoJob) throws SchedulerException {
        List<JobExecutionContext> jobExecutionContexts = scheduler.getCurrentlyExecutingJobs();
        for (JobExecutionContext jobExecutionContext : jobExecutionContexts) {
            if (kimonoJob.getName().equalsIgnoreCase(jobExecutionContext.getTrigger().getJobKey().getName()) && kimonoJob.getGroup().equalsIgnoreCase(jobExecutionContext.getTrigger().getJobKey().getGroup())) {
                scheduler.pauseJob(jobExecutionContext.getTrigger().getJobKey());
            }
        }
    }

    public void resumeJob(KimonoJob kimonoJob) throws SchedulerException {
        List<JobExecutionContext> jobExecutionContexts = scheduler.getCurrentlyExecutingJobs();
        for (JobExecutionContext jobExecutionContext : jobExecutionContexts) {
            if (kimonoJob.getName().equalsIgnoreCase(jobExecutionContext.getTrigger().getJobKey().getName()) && kimonoJob.getGroup().equalsIgnoreCase(jobExecutionContext.getTrigger().getJobKey().getGroup())) {
                scheduler.resumeJob(jobExecutionContext.getTrigger().getJobKey());
            }
        }
    }

    public void pauseScheduler() throws SchedulerException {
        this.scheduler.pauseAll();

    }

    public void restartScheduler() throws SchedulerException {
        this.scheduler.shutdown();
        this.scheduler.start();
        this.scheduler.resumeAll();

    }

    public void stopScheduler() throws SchedulerException {
         this.scheduler.shutdown();
    }

}
