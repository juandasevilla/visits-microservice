package com.example.visitsservice.domain.model;

import com.example.visitsservice.domain.exceptions.FinalDateException;
import com.example.visitsservice.domain.exceptions.InitialDateException;
import com.example.visitsservice.domain.exceptions.RealStateIsRequiredException;
import com.example.visitsservice.domain.exceptions.UserIsRequiredException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleModelTest {

    @Test
    @DisplayName("Debería crear un ScheduleModel con valores válidos")
    void createScheduleModel_withValidValues_shouldCreateObject() {
        // Arrange
        LocalDateTime initialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime finalDate = initialDate.plusHours(2);
        Long realStateId = 1L;
        Long userId = 2L;
        Integer amountReserved = 0;

        // Act
        ScheduleModel scheduleModel = new ScheduleModel(1L, initialDate, finalDate, realStateId, userId, amountReserved);

        // Assert
        assertEquals(1L, scheduleModel.getId());
        assertEquals(initialDate, scheduleModel.getInitialDate());
        assertEquals(finalDate, scheduleModel.getFinalDate());
        assertEquals(realStateId, scheduleModel.getRealStateId());
        assertEquals(userId, scheduleModel.getUserId());
        assertEquals(amountReserved, scheduleModel.getAmountReserved());
    }

    @Test
    @DisplayName("Debería lanzar InitialDateException cuando initialDate es null")
    void setInitialDate_withNullValue_shouldThrowException() {
        // Arrange
        LocalDateTime validFinalDate = LocalDateTime.now().plusDays(1);

        // Act & Assert
        assertThrows(InitialDateException.class, () -> {
            new ScheduleModel(1L, null, validFinalDate, 1L, 2L, 0);
        });
    }

    @Test
    @DisplayName("Debería lanzar InitialDateException cuando initialDate está en el pasado")
    void setInitialDate_withPastValue_shouldThrowException() {
        // Arrange
        LocalDateTime pastDate = LocalDateTime.now().minusDays(1);
        LocalDateTime validFinalDate = LocalDateTime.now().plusDays(1);

        // Act & Assert
        assertThrows(InitialDateException.class, () -> {
            new ScheduleModel(1L, pastDate, validFinalDate, 1L, 2L, 0);
        });
    }

    @Test
    @DisplayName("Debería lanzar InitialDateException cuando initialDate está más allá de 21 días")
    void setInitialDate_withFarFutureValue_shouldThrowException() {
        // Arrange
        LocalDateTime farFutureDate = LocalDateTime.now().plusDays(22);
        LocalDateTime validFinalDate = farFutureDate.plusHours(2);

        // Act & Assert
        assertThrows(InitialDateException.class, () -> {
            new ScheduleModel(1L, farFutureDate, validFinalDate, 1L, 2L, 0);
        });
    }

    @Test
    @DisplayName("Debería lanzar FinalDateException cuando finalDate es null")
    void setFinalDate_withNullValue_shouldThrowException() {
        // Arrange
        LocalDateTime validInitialDate = LocalDateTime.now().plusDays(1);

        // Act & Assert
        assertThrows(FinalDateException.class, () -> {
            new ScheduleModel(1L, validInitialDate, null, 1L, 2L, 0);
        });
    }

    @Test
    @DisplayName("Debería lanzar FinalDateException cuando finalDate está en el pasado")
    void setFinalDate_withPastValue_shouldThrowException() {
        // Arrange
        LocalDateTime validInitialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime pastDate = LocalDateTime.now().minusDays(1);

        // Act & Assert
        assertThrows(FinalDateException.class, () -> {
            new ScheduleModel(1L, validInitialDate, pastDate, 1L, 2L, 0);
        });
    }

    @Test
    @DisplayName("Debería lanzar FinalDateException cuando finalDate está más allá de 21 días")
    void setFinalDate_withFarFutureValue_shouldThrowException() {
        // Arrange
        LocalDateTime validInitialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime farFutureDate = LocalDateTime.now().plusDays(22);

        // Act & Assert
        assertThrows(FinalDateException.class, () -> {
            new ScheduleModel(1L, validInitialDate, farFutureDate, 1L, 2L, 0);
        });
    }

    @Test
    @DisplayName("Debería lanzar FinalDateException cuando finalDate es anterior a initialDate")
    void setFinalDate_beforeInitialDate_shouldThrowException() {
        // Arrange
        LocalDateTime initialDate = LocalDateTime.now().plusDays(2);
        LocalDateTime finalDate = LocalDateTime.now().plusDays(1);

        // Act & Assert
        assertThrows(FinalDateException.class, () -> {
            new ScheduleModel(1L, initialDate, finalDate, 1L, 2L, 0);
        });
    }

    @Test
    @DisplayName("Debería lanzar RealStateIsRequiredException cuando realStateId es null")
    void setRealStateId_withNullValue_shouldThrowException() {
        // Arrange
        LocalDateTime initialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime finalDate = initialDate.plusHours(2);

        // Act & Assert
        assertThrows(RealStateIsRequiredException.class, () -> {
            new ScheduleModel(1L, initialDate, finalDate, null, 2L, 0);
        });
    }

    @Test
    @DisplayName("Debería lanzar UserIsRequiredException cuando userId es null")
    void setUserId_withNullValue_shouldThrowException() {
        // Arrange
        LocalDateTime initialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime finalDate = initialDate.plusHours(2);

        // Act & Assert
        assertThrows(UserIsRequiredException.class, () -> {
            new ScheduleModel(1L, initialDate, finalDate, 1L, null, 0);
        });
    }

    @Test
    @DisplayName("Debería establecer amountReserved a 0 cuando es null")
    void setAmountReserved_withNullValue_shouldSetZero() {
        // Arrange
        LocalDateTime initialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime finalDate = initialDate.plusHours(2);

        // Act
        ScheduleModel scheduleModel = new ScheduleModel(1L, initialDate, finalDate, 1L, 2L, null);

        // Assert
        assertEquals(0, scheduleModel.getAmountReserved());
    }

    @Test
    @DisplayName("Debería incrementar amountReserved en 1")
    void incrementReservation_shouldAddOne() {
        // Arrange
        LocalDateTime initialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime finalDate = initialDate.plusHours(2);
        ScheduleModel scheduleModel = new ScheduleModel(1L, initialDate, finalDate, 1L, 2L, 0);

        // Act
        scheduleModel.incrementReservation();

        // Assert
        assertEquals(1, scheduleModel.getAmountReserved());
    }
}