package com.celebritycrm.scheduling.dto;

import com.celebritycrm.common.enums.ScheduleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse {

    private Long id;
    private String title;
    private Long celebrityId;
    private String celebrityName;
    private String eventType;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ScheduleStatus status;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

