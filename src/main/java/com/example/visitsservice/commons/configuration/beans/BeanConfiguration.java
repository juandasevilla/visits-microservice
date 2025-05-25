package com.example.visitsservice.commons.configuration.beans;

import com.example.visitsservice.domain.ports.in.ScheduleServicePort;
import com.example.visitsservice.domain.ports.out.SchedulePersistencePort;
import com.example.visitsservice.domain.usecases.ScheduleUseCase;
import com.example.visitsservice.infrastructure.adapters.persistence.SchedulePersistenceAdapter;
import com.example.visitsservice.infrastructure.mappers.ScheduleEntityMapper;
import com.example.visitsservice.infrastructure.repositories.mysql.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleEntityMapper scheduleEntityMapper;

    public SchedulePersistencePort schedulePersistencePort() {
        return new SchedulePersistenceAdapter(scheduleRepository, scheduleEntityMapper);
    }

    @Bean
    public ScheduleServicePort scheduleServicePort() {
        return new ScheduleUseCase(schedulePersistencePort());
    }
}
