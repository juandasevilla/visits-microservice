package com.example.visitsservice.domain.ports.in;

import com.example.visitsservice.domain.filters.ScheduleFilter;
import com.example.visitsservice.domain.model.ScheduleModel;
import com.example.visitsservice.domain.model.VisitModel;
import com.example.visitsservice.domain.utils.MyPage;

public interface ScheduleServicePort {
    void saveSchedule(ScheduleModel scheduleModel);
    MyPage<ScheduleModel> getSchedules(ScheduleFilter filter, Integer page, Integer size, boolean orderAsc);
    void saveVisit(VisitModel visitModel);
}
