package com.lpsmin.onemovie.model;

/**
 * Created by younes on 17/06/2017.
 */

public class CrewCredit extends Credit {
    private String department;
    private String job;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
