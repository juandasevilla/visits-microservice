package com.example.visitsservice.application.dto.request;

import java.time.LocalDateTime;

public record SaveScheduleRequest(LocalDateTime initialDate, LocalDateTime finalDate, Long realStateId, Long userId, Integer amountReserved) {
}
