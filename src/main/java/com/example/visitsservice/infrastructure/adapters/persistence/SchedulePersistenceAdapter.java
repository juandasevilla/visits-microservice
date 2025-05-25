package com.example.visitsservice.infrastructure.adapters.persistence;


import com.example.visitsservice.domain.model.ScheduleModel;
import com.example.visitsservice.domain.ports.out.SchedulePersistencePort;
import com.example.visitsservice.infrastructure.mappers.ScheduleEntityMapper;
import com.example.visitsservice.infrastructure.repositories.mysql.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SchedulePersistenceAdapter implements SchedulePersistencePort {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleEntityMapper scheduleEntityMapper;

    @Override
    public void saveSchedule(ScheduleModel scheduleModel) {
        scheduleRepository.save(scheduleEntityMapper.modelToEntity(scheduleModel));
    }

}
