package com.example.visitsservice.infrastructure.exceptionshandler;

import com.example.visitsservice.domain.exceptions.ScheduleExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(ScheduleExistsException.class)
    public ResponseEntity<ExceptionResponse> handleScheduleExistsException(ScheduleExistsException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.SCHEDULE_EXISTS, LocalDateTime.now()));
    }
}
