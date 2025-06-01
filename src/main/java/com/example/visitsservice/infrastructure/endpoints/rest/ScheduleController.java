package com.example.visitsservice.infrastructure.endpoints.rest;

import com.example.visitsservice.application.dto.request.SaveScheduleRequest;
import com.example.visitsservice.application.dto.response.SaveScheduleResponse;
import com.example.visitsservice.application.dto.response.ScheduleResponse;
import com.example.visitsservice.application.services.ScheduleService;
import com.example.visitsservice.domain.filters.ScheduleFilter;
import com.example.visitsservice.domain.utils.MyPage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<SaveScheduleResponse> saveSchedule(@RequestBody SaveScheduleRequest saveScheduleRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> details = (Map<String, Object>) authentication.getDetails();
        Long userId = (Long) details.get("userId");
        saveScheduleRequest.setUserId(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.saveSchedule(saveScheduleRequest));
    }

    @GetMapping
    public ResponseEntity<MyPage<ScheduleResponse>> getSchedules(
            @RequestParam(required = false) LocalDateTime initialDate,
            @RequestParam(required = false) LocalDateTime finalDate,
            @RequestParam(required = false) Long realStateId,
            @RequestParam(required = false) Long userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "false") boolean orderAsc) {

        ScheduleFilter filter = new ScheduleFilter(initialDate, finalDate, realStateId, userId);
        return ResponseEntity.ok(scheduleService.getSchedules(filter, page, size, orderAsc));
    }


}
