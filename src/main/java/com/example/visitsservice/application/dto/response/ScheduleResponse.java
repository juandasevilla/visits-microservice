package com.example.visitsservice.application.dto.response;

import java.time.LocalDateTime;

public record ScheduleResponse(Long id, LocalDateTime initialDate,
                               LocalDateTime finalDate, Long realStateId, Long userId) {
}
