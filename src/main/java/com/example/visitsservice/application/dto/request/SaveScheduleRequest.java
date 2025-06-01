package com.example.visitsservice.application.dto.request;

import java.time.LocalDateTime;

public class SaveScheduleRequest{
        LocalDateTime initialDate;
        LocalDateTime finalDate;
        Long realStateId;
        Long userId;
        Integer amountReserved;

    public SaveScheduleRequest(LocalDateTime initialDate, LocalDateTime finalDate, Long realStateId, Long userId, Integer amountReserved) {
            this.initialDate = initialDate;
            this.finalDate = finalDate;
            this.realStateId = realStateId;
            this.userId = userId;
            this.amountReserved = amountReserved;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
