package com.example.visitsservice.infrastructure.exceptionshandler;

import com.example.visitsservice.domain.exceptions.*;
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

    @ExceptionHandler(FinalDateException.class)
    public ResponseEntity<ExceptionResponse> handleFinalDateException(FinalDateException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.FINAL_DATE_EXCEPTION, LocalDateTime.now()));
    }

    @ExceptionHandler(InitialDateException.class)
    public ResponseEntity<ExceptionResponse> handleInitialDateException(InitialDateException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.INITIAL_DATE_EXCEPTION, LocalDateTime.now()));
    }

    @ExceptionHandler(UserIsRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleUserIsRequiredException(UserIsRequiredException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.USER_IS_REQUIRED_EXCEPTION, LocalDateTime.now()));
    }

    @ExceptionHandler(RealStateIsRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleRealStateIsRequiredException(RealStateIsRequiredException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.REAL_STATE_IS_REQUIRED_EXCEPTION, LocalDateTime.now()));
    }
}
