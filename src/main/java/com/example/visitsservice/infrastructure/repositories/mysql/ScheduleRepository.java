package com.example.visitsservice.infrastructure.repositories.mysql;

import com.example.visitsservice.infrastructure.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    Optional<ScheduleEntity> findById(Long id);
    List<ScheduleEntity> findAll();
    ScheduleEntity save(ScheduleEntity schedule);
    void deleteById(Long id);
    boolean existsById(Long id);
}
