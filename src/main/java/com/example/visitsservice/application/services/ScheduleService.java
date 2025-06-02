package com.example.visitsservice.application.services;

import com.example.visitsservice.application.dto.request.SaveScheduleRequest;
import com.example.visitsservice.application.dto.request.SaveVisitRequest;
import com.example.visitsservice.application.dto.response.SaveScheduleResponse;
import com.example.visitsservice.application.dto.response.SaveVisitResponse;
import com.example.visitsservice.application.dto.response.ScheduleResponse;
import com.example.visitsservice.domain.filters.ScheduleFilter;
import com.example.visitsservice.domain.utils.MyPage;

public interface ScheduleService {
    SaveScheduleResponse saveSchedule(SaveScheduleRequest request);
    MyPage<ScheduleResponse> getSchedules(ScheduleFilter filter, Integer page, Integer size, boolean orderAsc);
    SaveVisitResponse saveVisit(SaveVisitRequest request);
}
