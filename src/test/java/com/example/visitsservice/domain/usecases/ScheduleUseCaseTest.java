package com.example.visitsservice.domain.usecases;

import com.example.visitsservice.domain.exceptions.RealStateIsRequiredException;
import com.example.visitsservice.domain.exceptions.ScheduleExistsException;
import com.example.visitsservice.domain.exceptions.ScheduleRequiredException;
import com.example.visitsservice.domain.filters.ScheduleFilter;
import com.example.visitsservice.domain.model.ScheduleModel;
import com.example.visitsservice.domain.model.VisitModel;
import com.example.visitsservice.domain.ports.out.SchedulePersistencePort;
import com.example.visitsservice.domain.utils.MyPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleUseCaseTest {

    @Mock
    private SchedulePersistencePort schedulePersistencePort;

    private ScheduleUseCase scheduleUseCase;

    @BeforeEach
    void setUp() {
        scheduleUseCase = new ScheduleUseCase(schedulePersistencePort);
    }

    @Test
    void saveSchedule_whenRealStateExistsAndNoOverlapping_shouldSaveSchedule() {
        // Arrange
        LocalDateTime initialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime finalDate = initialDate.plusHours(2);
        ScheduleModel scheduleModel = new ScheduleModel(1L, initialDate, finalDate, 18L, 5L, 0);

        when(schedulePersistencePort.existsRealState(18L)).thenReturn(true);
        when(schedulePersistencePort.existsOverlappingSchedule(any(ScheduleModel.class))).thenReturn(false);

        // Act
        scheduleUseCase.saveSchedule(scheduleModel);

        // Assert
        verify(schedulePersistencePort).saveSchedule(scheduleModel);
    }

    @Test
    void saveSchedule_whenRealStateDoesNotExist_shouldThrowException() {
        // Arrange
        LocalDateTime initialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime finalDate = initialDate.plusHours(2);
        ScheduleModel scheduleModel = new ScheduleModel(1L, initialDate, finalDate, 18L, 5L, 0);

        when(schedulePersistencePort.existsRealState(18L)).thenReturn(false);

        // Act & Assert
        assertThrows(RealStateIsRequiredException.class, () -> {
            scheduleUseCase.saveSchedule(scheduleModel);
        });

        verify(schedulePersistencePort, never()).saveSchedule(any(ScheduleModel.class));
    }

    @Test
    void saveSchedule_whenOverlappingScheduleExists_shouldThrowException() {
        // Arrange
        LocalDateTime initialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime finalDate = initialDate.plusHours(2);
        ScheduleModel scheduleModel = new ScheduleModel(1L, initialDate, finalDate, 18L, 5L, 0);

        when(schedulePersistencePort.existsRealState(18L)).thenReturn(true);
        when(schedulePersistencePort.existsOverlappingSchedule(any(ScheduleModel.class))).thenReturn(true);

        // Act & Assert
        assertThrows(ScheduleExistsException.class, () -> {
            scheduleUseCase.saveSchedule(scheduleModel);
        });

        verify(schedulePersistencePort, never()).saveSchedule(any(ScheduleModel.class));
    }

    @Test
    void getSchedules_shouldReturnPagedResults() {
        LocalDateTime testInitialDate = LocalDateTime.now();
        LocalDateTime testFinalDate = LocalDateTime.now().plusDays(7);
        Long testRealStateId = 18L;
        Long testUserId = 5L;

        ScheduleFilter filter = new ScheduleFilter(testInitialDate, testFinalDate, testRealStateId, testUserId);
        int page = 0;
        int size = 10;
        boolean orderAsc = true;

        List<ScheduleModel> schedules = Arrays.asList(
                new ScheduleModel(1L, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), 18L, 5L, 0),
                new ScheduleModel(2L, LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2), 19L, 6L, 0)
        );

        MyPage<ScheduleModel> expectedPage = new MyPage<>(schedules, page, size, orderAsc, 2);

        when(schedulePersistencePort.getSchedules(filter, page, size, orderAsc)).thenReturn(expectedPage);

        // Act
        MyPage<ScheduleModel> result = scheduleUseCase.getSchedules(filter, page, size, orderAsc);

        // Assert
        assertEquals(expectedPage, result);
        assertEquals(2, result.getContent().size());
    }

    @Test
    void saveVisit_whenScheduleExistsWithAvailability_shouldSaveVisit() {
        // Arrange
        LocalDateTime initialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime finalDate = initialDate.plusHours(2);
        ScheduleModel scheduleModel = new ScheduleModel(1L, initialDate, finalDate, 18L, 5L, 0);
        VisitModel visitModel = new VisitModel(scheduleModel, "test@example.com");

        when(schedulePersistencePort.existsScheduleWithAvailability(1L)).thenReturn(true);

        // Act
        scheduleUseCase.saveVisit(visitModel);

        // Assert
        verify(schedulePersistencePort).saveVisit(visitModel);
    }

    @Test
    void saveVisit_whenScheduleDoesNotExistOrNoAvailability_shouldThrowException() {
        // Arrange
        LocalDateTime initialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime finalDate = initialDate.plusHours(2);
        ScheduleModel scheduleModel = new ScheduleModel(1L, initialDate, finalDate, 18L, 5L, 0);
        VisitModel visitModel = new VisitModel(scheduleModel, "test@example.com");

        when(schedulePersistencePort.existsScheduleWithAvailability(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(ScheduleRequiredException.class, () -> {
            scheduleUseCase.saveVisit(visitModel);
        });

        verify(schedulePersistencePort, never()).saveVisit(any(VisitModel.class));
    }
}