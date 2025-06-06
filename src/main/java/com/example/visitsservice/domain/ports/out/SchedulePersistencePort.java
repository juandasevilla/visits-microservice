package com.example.visitsservice.domain.ports.out;

import com.example.visitsservice.domain.filters.ScheduleFilter;
import com.example.visitsservice.domain.model.ScheduleModel;
import com.example.visitsservice.domain.model.VisitModel;
import com.example.visitsservice.domain.utils.MyPage;

public interface SchedulePersistencePort {
    void saveSchedule(ScheduleModel scheduleModel);
    boolean existsOverlappingSchedule(ScheduleModel scheduleModel);
    MyPage<ScheduleModel> getSchedules(ScheduleFilter filter, Integer page, Integer size, boolean orderAsc);
    void saveVisit(VisitModel visitModel);
    boolean existsScheduleWithAvailability(Long scheduleId);
    void updateSchedule(ScheduleModel scheduleModel);
    boolean existsRealState(Long realStateId);
}
