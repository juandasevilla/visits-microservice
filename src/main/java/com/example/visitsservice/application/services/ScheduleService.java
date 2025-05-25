package com.example.visitsservice.application.services;

import com.example.visitsservice.application.dto.request.SaveScheduleRequest;
import com.example.visitsservice.application.dto.response.SaveScheduleResponse;

public interface ScheduleService {
    SaveScheduleResponse saveSchedule(SaveScheduleRequest request);
}
