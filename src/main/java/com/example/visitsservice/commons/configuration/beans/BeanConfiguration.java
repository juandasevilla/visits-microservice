package com.example.visitsservice.commons.configuration.beans;

import com.example.visitsservice.domain.ports.in.ScheduleServicePort;
import com.example.visitsservice.domain.ports.out.SchedulePersistencePort;
import com.example.visitsservice.domain.usecases.ScheduleUseCase;
import com.example.visitsservice.infrastructure.adapters.persistence.SchedulePersistenceAdapter;
import com.example.visitsservice.infrastructure.mappers.ScheduleEntityMapper;
import com.example.visitsservice.infrastructure.repositories.mysql.ScheduleRepository;
import com.example.visitsservice.infrastructure.repositories.mysql.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleEntityMapper scheduleEntityMapper;
    private final VisitRepository visitRepository;

    public SchedulePersistencePort schedulePersistencePort() {
        return new SchedulePersistenceAdapter(scheduleRepository, scheduleEntityMapper, visitRepository);
    }

    @Bean
    public ScheduleServicePort scheduleServicePort() {
        return new ScheduleUseCase(schedulePersistencePort());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200") // Cambia esto a la URL de tu frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
