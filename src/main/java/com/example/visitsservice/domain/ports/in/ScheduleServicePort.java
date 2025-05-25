package com.example.visitsservice.domain.ports.in;

import com.example.visitsservice.domain.model.ScheduleModel;

public interface ScheduleServicePort {
    void saveSchedule(ScheduleModel scheduleModel);
}
