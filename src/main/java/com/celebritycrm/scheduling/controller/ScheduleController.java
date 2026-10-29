package com.celebritycrm.scheduling.controller;

import com.celebritycrm.common.enums.ScheduleStatus;
import com.celebritycrm.common.response.ApiResponse;
import com.celebritycrm.scheduling.dto.ScheduleRequest;
import com.celebritycrm.scheduling.dto.ScheduleResponse;
import com.celebritycrm.scheduling.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/scheduling")
@RequiredArgsConstructor
@Tag(name = "Scheduling", description = "Endpoints for calendar and celebrity appearance scheduling")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @Operation(summary = "Schedule a new event")
    public ResponseEntity<ApiResponse<ScheduleResponse>> createScheduleEvent(@Valid @RequestBody ScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Event scheduled successfully", scheduleService.createScheduleEvent(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing schedule event")
    public ResponseEntity<ApiResponse<ScheduleResponse>> updateScheduleEvent(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Event updated successfully", scheduleService.updateScheduleEvent(id, request)));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update event status")
    public ResponseEntity<ApiResponse<ScheduleResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam ScheduleStatus status) {
        return ResponseEntity.ok(ApiResponse.success("Status updated successfully", scheduleService.updateStatus(id, status)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get schedule event by ID")
    public ResponseEntity<ApiResponse<ScheduleResponse>> getScheduleEventById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(scheduleService.getScheduleEventById(id)));
    }

    @GetMapping
    @Operation(summary = "Get all schedule events paginated")
    public ResponseEntity<ApiResponse<Page<ScheduleResponse>>> getAllScheduleEvents(
            @PageableDefault(size = 10, sort = "startTime") Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(scheduleService.getAllScheduleEvents(pageable)));
    }

    @GetMapping("/celebrity/{celebrityId}")
    @Operation(summary = "Get all events for a celebrity")
    public ResponseEntity<ApiResponse<List<ScheduleResponse>>> getScheduleByCelebrity(@PathVariable Long celebrityId) {
        return ResponseEntity.ok(ApiResponse.success(scheduleService.getScheduleByCelebrity(celebrityId)));
    }

    @GetMapping("/celebrity/{celebrityId}/range")
    @Operation(summary = "Get celebrity events in date range")
    public ResponseEntity<ApiResponse<List<ScheduleResponse>>> getCelebrityScheduleBetween(
            @PathVariable Long celebrityId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(ApiResponse.success(scheduleService.getCelebrityScheduleBetween(celebrityId, start, end)));
    }

    @GetMapping("/upcoming")
    @Operation(summary = "Get upcoming events")
    public ResponseEntity<ApiResponse<List<ScheduleResponse>>> getUpcomingEvents() {
        return ResponseEntity.ok(ApiResponse.success(scheduleService.getUpcomingEvents()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel / Delete a schedule event")
    public ResponseEntity<ApiResponse<Void>> deleteScheduleEvent(@PathVariable Long id) {
        scheduleService.deleteScheduleEvent(id);
        return ResponseEntity.ok(ApiResponse.success("Schedule event deleted successfully", null));
    }
}

