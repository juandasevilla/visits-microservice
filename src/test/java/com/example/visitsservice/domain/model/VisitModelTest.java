package com.example.visitsservice.domain.model;

import com.example.visitsservice.domain.exceptions.VisitEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VisitModelTest {

    private ScheduleModel createValidSchedule() {
        LocalDateTime initialDate = LocalDateTime.now().plusDays(1);
        LocalDateTime finalDate = initialDate.plusHours(2);
        return new ScheduleModel(1L, initialDate, finalDate, 1L, 2L, 0);
    }

    @Test
    @DisplayName("Debería crear un VisitModel con valores válidos")
    void createVisitModel_withValidValues_shouldCreateObject() {
        // Arrange
        ScheduleModel validSchedule = createValidSchedule();
        String validEmail = "test@example.com";

        // Act
        VisitModel visitModel = new VisitModel(validSchedule, validEmail);

        // Assert
        assertEquals(validSchedule, visitModel.getSchedule());
        assertEquals(validEmail, visitModel.getEmail());
    }

    @Test
    @DisplayName("Debería lanzar VisitEmailException cuando email es null")
    void setEmail_withNullValue_shouldThrowException() {
        // Arrange
        ScheduleModel validSchedule = createValidSchedule();

        // Act & Assert
        assertThrows(VisitEmailException.class, () -> {
            new VisitModel(validSchedule, null);
        });
    }

    @Test
    @DisplayName("Debería lanzar VisitEmailException cuando email está vacío")
    void setEmail_withEmptyValue_shouldThrowException() {
        // Arrange
        ScheduleModel validSchedule = createValidSchedule();

        // Act & Assert
        assertThrows(VisitEmailException.class, () -> {
            new VisitModel(validSchedule, "");
        });
    }

    @Test
    @DisplayName("Debería lanzar VisitEmailException cuando email solo contiene espacios")
    void setEmail_withOnlySpaces_shouldThrowException() {
        // Arrange
        ScheduleModel validSchedule = createValidSchedule();

        // Act & Assert
        assertThrows(VisitEmailException.class, () -> {
            new VisitModel(validSchedule, "   ");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "correosinformato",
            "correo@",
            "@dominio.com",
    })
    @DisplayName("Debería lanzar VisitEmailException cuando el formato de email es inválido")
    void setEmail_withInvalidFormat_shouldThrowException(String invalidEmail) {
        // Arrange
        ScheduleModel validSchedule = createValidSchedule();

        // Act & Assert
        assertThrows(VisitEmailException.class, () -> {
            new VisitModel(validSchedule, invalidEmail);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "usuario@dominio.com",
            "usuario.nombre@dominio.com",
            "usuario+etiqueta@dominio.com",
            "usuario123@dominio.co",
            "usuario@sub.dominio.com"
    })
    @DisplayName("Debería aceptar formatos de email válidos")
    void setEmail_withValidFormat_shouldAccept(String validEmail) {
        // Arrange
        ScheduleModel validSchedule = createValidSchedule();

        // Act
        VisitModel visitModel = new VisitModel(validSchedule, validEmail);

        // Assert
        assertEquals(validEmail, visitModel.getEmail());
    }

    @Test
    @DisplayName("Debería permitir actualizar el schedule")
    void setSchedule_withValidSchedule_shouldUpdateSchedule() {
        // Arrange
        ScheduleModel initialSchedule = createValidSchedule();
        VisitModel visitModel = new VisitModel(initialSchedule, "test@example.com");

        LocalDateTime newInitialDate = LocalDateTime.now().plusDays(2);
        LocalDateTime newFinalDate = newInitialDate.plusHours(3);
        ScheduleModel newSchedule = new ScheduleModel(2L, newInitialDate, newFinalDate, 3L, 4L, 0);

        // Act
        visitModel.setSchedule(newSchedule);

        // Assert
        assertEquals(newSchedule, visitModel.getSchedule());
        assertNotEquals(initialSchedule, visitModel.getSchedule());
    }
}