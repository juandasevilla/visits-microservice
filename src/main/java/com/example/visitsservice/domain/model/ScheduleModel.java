package com.example.visitsservice.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ScheduleModel {
    private Long id;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;;
    private Long realStateId;
    private Long userId;
    private Integer amountReserved;

    public ScheduleModel(Long id, LocalDateTime initialDate, LocalDateTime finalDate,
                         Long realStateId, Long userId, Integer amountReserved) {
        this.id = id;
        setInitialDate(initialDate);
        setFinalDate(finalDate);
        setRealStateId(realStateId);
        setUserId(userId);
        setAmountReserved(amountReserved);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public Long getRealStateId() {
        return realStateId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getAmountReserved() {
        return amountReserved;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInitialDate(LocalDateTime initialDate) {
        if (initialDate == null || initialDate.isBefore(LocalDateTime.now()) || initialDate.isAfter(LocalDateTime.now().plusDays(21))) {
            throw new IllegalArgumentException("La fecha inicial no puede ser nula, anterior a hoy o posterior a 90 días");
        }
        this.initialDate = initialDate;
    }

    public void setFinalDate(LocalDateTime finalDate) {
        if (finalDate == null ||
                finalDate.isBefore(LocalDateTime.now()) ||
                finalDate.isAfter(LocalDateTime.now().plusDays(21)) ||
                finalDate.isBefore(initialDate)) {

            throw new IllegalArgumentException("La fecha final no puede ser nula, anterior a hoy, posterior a 21 días o anterior a la fecha inicial");
        }
        this.finalDate = finalDate;
    }

    public void setRealStateId(Long realStateId) {
        if (realStateId == null) {
            throw new IllegalArgumentException("El ID de la propiedad no puede ser nulo");
        }
        this.realStateId = realStateId;
    }

    public void setUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }
        this.userId = userId;
    }

    public void setAmountReserved(Integer amountReserved) {
        this.amountReserved = (amountReserved != null) ? amountReserved : 0;
    }

    public void  incrementReservation(){
        this.amountReserved++;
    }

}
