package com.example.visitsservice.application.services.impl;

import com.example.visitsservice.application.dto.request.SaveScheduleRequest;
import com.example.visitsservice.application.dto.response.SaveScheduleResponse;
import com.example.visitsservice.application.dto.response.ScheduleResponse;
import com.example.visitsservice.application.mappers.ScheduleDtoMapper;
import com.example.visitsservice.application.services.ScheduleService;
import com.example.visitsservice.commons.configuration.utils.Constants;
import com.example.visitsservice.domain.filters.ScheduleFilter;
import com.example.visitsservice.domain.model.ScheduleModel;
import com.example.visitsservice.domain.ports.in.ScheduleServicePort;
import com.example.visitsservice.domain.utils.MyPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleServicePort scheduleServicePort;
    private final ScheduleDtoMapper scheduleDtoMapper;

    @Override
    public SaveScheduleResponse saveSchedule(SaveScheduleRequest request) {
        scheduleServicePort.saveSchedule(scheduleDtoMapper.requestToModel(request));
        return new SaveScheduleResponse(Constants.SAVE_SCHEDULE_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public MyPage<ScheduleResponse> getSchedules(ScheduleFilter filter, Integer page, Integer size, boolean orderAsc) {
        MyPage<ScheduleModel> schedulePage = scheduleServicePort.getSchedules(filter, page, size, orderAsc);
        List<ScheduleResponse> scheduleResponseList = scheduleDtoMapper.modelListToResponseList(schedulePage.getContent());
        return new MyPage<>(scheduleResponseList, page, size, orderAsc, schedulePage.getTotalObjects());
    }
}
