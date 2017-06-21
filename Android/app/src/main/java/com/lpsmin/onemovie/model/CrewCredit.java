package com.lpsmin.onemovie.model;

import java.util.Date;

/**
 * Created by younes on 17/06/2017.
 */

public class CrewCredit extends Credit {
    private String department;
    private String job;

    public CrewCredit(String credit_id, Integer id, String media_type, String name, Boolean adult, String original_title, String poster_path, Date release_date, String title, String department, String job) {
        super(credit_id, id, media_type, name, adult, original_title, poster_path, release_date, title);
        this.department = department;
        this.job = job;
    }

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
