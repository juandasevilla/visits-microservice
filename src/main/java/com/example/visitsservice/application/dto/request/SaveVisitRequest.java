package com.example.visitsservice.application.dto.request;

import com.example.visitsservice.domain.model.ScheduleModel;

public record SaveVisitRequest (ScheduleModel schedule, String email){
}
