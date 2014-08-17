/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovworks.jobmanager;

import java.util.ArrayList;
import java.util.List;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
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

    private class QuartzJob {

        JobDetail jobDetail;
        Trigger trigger;
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

        // newJob(HelloJob.class) .withIdentity("myJob", "group1").build();
        kimonoJobs.add(kimonoJob);
        //fire job added event;
        List<?> jobs = scheduler.getCurrentlyExecutingJobs();
    }

    public void removeJob(KimonoJob kimonoJob) {
    }

    public void stopJob(KimonoJob kimonoJob) {
    }

    public void pauseJob(KimonoJob kimonoJob) {
    }

    public void pauseScheduler() {

    }

    public void restartScheduler() {

    }

    public void stopScheduler() {
    }
}
