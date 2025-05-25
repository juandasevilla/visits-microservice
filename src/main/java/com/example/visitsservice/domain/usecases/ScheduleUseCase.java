package com.example.visitsservice.domain.usecases;

import com.example.visitsservice.domain.model.ScheduleModel;
import com.example.visitsservice.domain.ports.in.ScheduleServicePort;
import com.example.visitsservice.domain.ports.out.SchedulePersistencePort;

public class ScheduleUseCase implements ScheduleServicePort {
    private final SchedulePersistencePort schedulePersistencePort;

    public ScheduleUseCase(SchedulePersistencePort schedulePersistencePort) {
        this.schedulePersistencePort = schedulePersistencePort;
    }

    @Override
    public void saveSchedule(ScheduleModel scheduleModel) {
        schedulePersistencePort.saveSchedule(scheduleModel);
    }
}
