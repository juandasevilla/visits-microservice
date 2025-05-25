package com.example.visitsservice.infrastructure.mappers;

import com.example.visitsservice.domain.model.ScheduleModel;
import com.example.visitsservice.infrastructure.entities.ScheduleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleEntityMapper {
    ScheduleEntity modelToEntity(ScheduleModel scheduleModel);
    ScheduleModel entityToModel(ScheduleEntity scheduleEntity);
}
