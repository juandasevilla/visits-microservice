package com.example.visitsservice.domain.model;

import com.example.visitsservice.domain.exceptions.ScheduleRequiredException;
import com.example.visitsservice.domain.exceptions.VisitEmailException;

public class VisitModel {
    private ScheduleModel schedule;
    private String email;

    public VisitModel(ScheduleModel schedule, String email) {
        setSchedule(schedule);
        setEmail(email);
    }

    public ScheduleModel getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleModel schedule) {
        this.schedule = schedule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new VisitEmailException();
        }
        this.email = email;
    }
}
