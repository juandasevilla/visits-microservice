package com.example.visitsservice.domain.usecases;

import com.example.visitsservice.domain.exceptions.ScheduleExistsException;
import com.example.visitsservice.domain.filters.ScheduleFilter;
import com.example.visitsservice.domain.model.ScheduleModel;
import com.example.visitsservice.domain.ports.in.ScheduleServicePort;
import com.example.visitsservice.domain.ports.out.SchedulePersistencePort;
import com.example.visitsservice.domain.utils.MyPage;

public class ScheduleUseCase implements ScheduleServicePort {
    private final SchedulePersistencePort schedulePersistencePort;

    public ScheduleUseCase(SchedulePersistencePort schedulePersistencePort) {
        this.schedulePersistencePort = schedulePersistencePort;
    }

    @Override
    public void saveSchedule(ScheduleModel scheduleModel) {
        if (schedulePersistencePort.existsOverlappingSchedule(scheduleModel)) {
            throw new ScheduleExistsException();
        }
        schedulePersistencePort.saveSchedule(scheduleModel);
    }

    @Override
    public MyPage<ScheduleModel> getSchedules(ScheduleFilter filter, Integer page, Integer size, boolean orderAsc) {
        return schedulePersistencePort.getSchedules(filter, page, size, orderAsc);
    }
}
