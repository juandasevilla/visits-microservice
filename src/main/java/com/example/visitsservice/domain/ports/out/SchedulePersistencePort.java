package com.example.visitsservice.domain.ports.out;

import com.example.visitsservice.domain.model.ScheduleModel;

public interface SchedulePersistencePort {
    void saveSchedule(ScheduleModel scheduleModel);
}
