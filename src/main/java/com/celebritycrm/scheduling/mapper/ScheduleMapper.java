package com.celebritycrm.scheduling.mapper;

import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.common.enums.ScheduleStatus;
import com.celebritycrm.scheduling.dto.ScheduleRequest;
import com.celebritycrm.scheduling.dto.ScheduleResponse;
import com.celebritycrm.scheduling.entity.ScheduleEvent;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

    public ScheduleEvent toEntity(ScheduleRequest request, Celebrity celebrity) {
        if (request == null) return null;
        return ScheduleEvent.builder()
                .title(request.getTitle())
                .celebrity(celebrity)
                .eventType(request.getEventType())
                .location(request.getLocation())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .status(request.getStatus() != null ? request.getStatus() : ScheduleStatus.SCHEDULED)
                .notes(request.getNotes())
                .build();
    }

    public ScheduleResponse toResponse(ScheduleEvent entity) {
        if (entity == null) return null;
        return ScheduleResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .celebrityId(entity.getCelebrity() != null ? entity.getCelebrity().getId() : null)
                .celebrityName(entity.getCelebrity() != null ? entity.getCelebrity().getFullName() : null)
                .eventType(entity.getEventType())
                .location(entity.getLocation())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .status(entity.getStatus())
                .notes(entity.getNotes())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public void updateEntityFromRequest(ScheduleRequest request, ScheduleEvent entity, Celebrity celebrity) {
        if (request == null || entity == null) return;
        entity.setTitle(request.getTitle());
        entity.setEventType(request.getEventType());
        entity.setLocation(request.getLocation());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setNotes(request.getNotes());
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        if (celebrity != null) {
            entity.setCelebrity(celebrity);
        }
    }
}

