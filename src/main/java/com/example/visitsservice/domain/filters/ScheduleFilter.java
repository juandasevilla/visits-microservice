package com.example.visitsservice.domain.filters;

import java.time.LocalDateTime;

public class ScheduleFilter {
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private Long realStateId;
    private Long userId;

    public ScheduleFilter(LocalDateTime initialDate, LocalDateTime finalDate, Long realStateId, Long userId) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.realStateId = realStateId;
        this.userId = userId;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDateTime initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }

    public Long getRealStateId() {
        return realStateId;
    }

    public void setRealStateId(Long realStateId) {
        this.realStateId = realStateId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
