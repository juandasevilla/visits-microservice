package com.example.visitsservice.application.mappers;

import java.util.List;

import java.util.List;

import com.example.visitsservice.application.dto.request.SaveScheduleRequest;
import com.example.visitsservice.application.dto.response.SaveScheduleResponse;
import com.example.visitsservice.domain.model.ScheduleModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScheduleDtoMapper {
    ScheduleModel requestToModel(SaveScheduleRequest saveScheduleRequest);
    SaveScheduleResponse modelToResponse(ScheduleModel scheduleModel);
    List<SaveScheduleResponse> modelListToResponseList(List<ScheduleModel> scheduleModels);
}
