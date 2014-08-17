/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovworks.jobmanager;

import java.util.Date;
import org.quartz.Job;

/**
 *
 * @author Folorunsho Solomon
 */
public class KimonoJob {

    private String name;
    private String group;
    private long jobInterval;
    private Date fireTime;
    private Date scheduledFireTime;
    private Date previousFireTime;
    private Date nextFireTime;
    private Class<Job> jobClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Class getJobClass() {
        return jobClass;
    }

    public void setJobClass(Class<Job> jobClass) {
        this.jobClass = jobClass;
    }

    public long getJobInterval() {
        return jobInterval;
    }

    public void setJobInterval(long jobInterval) {
        this.jobInterval = jobInterval;
    }

    public Date getFireTime() {
        return fireTime;
    }

    public void setFireTime(Date fireTime) {
        this.fireTime = fireTime;
    }

    public Date getScheduledFireTime() {
        return scheduledFireTime;
    }

    public void setScheduledFireTime(Date scheduledFireTime) {
        this.scheduledFireTime = scheduledFireTime;
    }

    public Date getPreviousFireTime() {
        return previousFireTime;
    }

    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

}
