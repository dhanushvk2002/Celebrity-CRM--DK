package com.celebritycrm.scheduling.service;

import com.celebritycrm.common.enums.ScheduleStatus;
import com.celebritycrm.scheduling.dto.ScheduleRequest;
import com.celebritycrm.scheduling.dto.ScheduleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    ScheduleResponse createScheduleEvent(ScheduleRequest request);
    ScheduleResponse updateScheduleEvent(Long id, ScheduleRequest request);
    ScheduleResponse updateStatus(Long id, ScheduleStatus status);
    ScheduleResponse getScheduleEventById(Long id);
    Page<ScheduleResponse> getAllScheduleEvents(Pageable pageable);
    List<ScheduleResponse> getScheduleByCelebrity(Long celebrityId);
    List<ScheduleResponse> getCelebrityScheduleBetween(Long celebrityId, LocalDateTime start, LocalDateTime end);
    List<ScheduleResponse> getUpcomingEvents();
    void deleteScheduleEvent(Long id);
}

