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
    private int jobInterval;
    private Date previousFireTime;
    private Date nextFireTime;
    public boolean mayFireAgain;
    public Date startTime;
    public Date endTime;
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

    public int getJobInterval() {
        return jobInterval;
    }

    public void setJobInterval(int jobInterval) {
        this.jobInterval = jobInterval;
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

    public boolean isMayFireAgain() {
        return mayFireAgain;
    }

    public void setMayFireAgain(boolean mayFireAgain) {
        this.mayFireAgain = mayFireAgain;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
