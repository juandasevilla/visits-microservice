package com.example.visitsservice.infrastructure.mappers;

import com.example.visitsservice.domain.model.ScheduleModel;
import com.example.visitsservice.domain.model.VisitModel;
import com.example.visitsservice.infrastructure.entities.ScheduleEntity;
import com.example.visitsservice.infrastructure.entities.VisitEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleEntityMapper {
    ScheduleEntity modelToEntity(ScheduleModel scheduleModel);
    ScheduleModel entityToModel(ScheduleEntity scheduleEntity);
    List<ScheduleModel> entityListToModelList(List<ScheduleEntity> scheduleEntities);
    VisitEntity visitModelToEntity(VisitModel visitModel);
    VisitModel visitEntityToModel(VisitEntity visitEntity);
}
