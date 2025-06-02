package com.example.visitsservice.infrastructure.exceptionshandler;

import com.example.visitsservice.domain.exceptions.ScheduleExistsException;
import com.example.visitsservice.domain.exceptions.ScheduleRequiredException;
import com.example.visitsservice.domain.exceptions.VisitEmailException;
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

    @ExceptionHandler(VisitEmailException.class)
    public ResponseEntity<ExceptionResponse> handleVisitEmailException(VisitEmailException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.VISIT_EMAIL_EXCEPTION, LocalDateTime.now()));
    }

    @ExceptionHandler(ScheduleRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleScheduleRequiredException(ScheduleRequiredException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.SCHEDULE_REQUIRED_EXCEPTION, LocalDateTime.now()));
    }
}
