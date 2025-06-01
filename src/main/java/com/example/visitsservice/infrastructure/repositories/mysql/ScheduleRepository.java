package com.example.visitsservice.infrastructure.repositories.mysql;

import com.example.visitsservice.infrastructure.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;


public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    Optional<ScheduleEntity> findById(Long id);
    List<ScheduleEntity> findAll();
    ScheduleEntity save(ScheduleEntity schedule);
    void deleteById(Long id);
    boolean existsById(Long id);
    @Query("SELECT COUNT(s) > 0 FROM ScheduleEntity s WHERE s.userId = :userId " +
            "AND ((s.initialDate <= :finalDate AND s.finalDate >= :initialDate))")
    boolean existsByUserIdAndDateRangeOverlapping(
            Long userId,
            LocalDateTime initialDate,
            LocalDateTime finalDate);

    @Query("SELECT s FROM ScheduleEntity s WHERE " +
            "s.amountReserved < 2 AND " +
            "s.initialDate > CURRENT_TIMESTAMP AND " +
            "(:userId IS NULL OR s.userId = :userId) AND " +
            "(:realStateId IS NULL OR s.realStateId = :realStateId) AND " +
            "(:initialDate IS NULL OR s.initialDate >= :initialDate) AND " +
            "(:finalDate IS NULL OR s.finalDate <= :finalDate)")
    Page<ScheduleEntity> findAllByFilter(
            @Param("userId") Long userId,
            @Param("realStateId") Long realStateId,
            @Param("initialDate") LocalDateTime initialDate,
            @Param("finalDate") LocalDateTime finalDate,
            Pageable pageable);
}
