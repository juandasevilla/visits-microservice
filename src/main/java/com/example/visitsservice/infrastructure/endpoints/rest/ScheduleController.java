package com.example.visitsservice.infrastructure.endpoints.rest;

import com.example.visitsservice.application.dto.request.SaveScheduleRequest;
import com.example.visitsservice.application.dto.response.SaveScheduleResponse;
import com.example.visitsservice.application.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<SaveScheduleResponse> saveSchedule(@RequestBody SaveScheduleRequest saveScheduleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.saveSchedule(saveScheduleRequest));
    }
}
