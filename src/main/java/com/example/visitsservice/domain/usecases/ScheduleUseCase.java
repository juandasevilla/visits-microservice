package com.example.visitsservice.domain.usecases;

import com.example.visitsservice.domain.exceptions.RealStateIsRequiredException;
import com.example.visitsservice.domain.exceptions.ScheduleExistsException;
import com.example.visitsservice.domain.exceptions.ScheduleRequiredException;
import com.example.visitsservice.domain.filters.ScheduleFilter;
import com.example.visitsservice.domain.model.ScheduleModel;
import com.example.visitsservice.domain.model.VisitModel;
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
        boolean realStateExists = schedulePersistencePort.existsRealState(scheduleModel.getRealStateId());
        System.out.println("Validando inmueble ID: " + scheduleModel.getRealStateId() + " - Existe: " + realStateExists);

        if (!realStateExists) {
            throw new RealStateIsRequiredException();
        }


        if (schedulePersistencePort.existsOverlappingSchedule(scheduleModel)) {
            throw new ScheduleExistsException();
        }
        schedulePersistencePort.saveSchedule(scheduleModel);
    }

    @Override
    public MyPage<ScheduleModel> getSchedules(ScheduleFilter filter, Integer page, Integer size, boolean orderAsc) {
        return schedulePersistencePort.getSchedules(filter, page, size, orderAsc);
    }

    @Override
    public void saveVisit(VisitModel visitModel) {
        if (!schedulePersistencePort.existsScheduleWithAvailability(visitModel.getSchedule().getId())) {
            throw new ScheduleRequiredException();
        }
        schedulePersistencePort.saveVisit(visitModel);
    }
}
