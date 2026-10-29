package com.celebritycrm.casting.mapper;

import com.celebritycrm.casting.dto.CastingCallRequest;
import com.celebritycrm.casting.dto.CastingCallResponse;
import com.celebritycrm.casting.entity.CastingCall;
import com.celebritycrm.common.enums.CastingStatus;
import org.springframework.stereotype.Component;

@Component
public class CastingCallMapper {

    public CastingCall toEntity(CastingCallRequest request) {
        if (request == null) return null;
        return CastingCall.builder()
                .title(request.getTitle())
                .projectName(request.getProjectName())
                .roleName(request.getRoleName())
                .requirements(request.getRequirements())
                .deadline(request.getDeadline())
                .budget(request.getBudget())
                .status(request.getStatus() != null ? request.getStatus() : CastingStatus.OPEN)
                .build();
    }

    public CastingCallResponse toResponse(CastingCall entity) {
        if (entity == null) return null;
        return CastingCallResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .projectName(entity.getProjectName())
                .roleName(entity.getRoleName())
                .requirements(entity.getRequirements())
                .deadline(entity.getDeadline())
                .budget(entity.getBudget())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public void updateEntityFromRequest(CastingCallRequest request, CastingCall entity) {
        if (request == null || entity == null) return;
        entity.setTitle(request.getTitle());
        entity.setProjectName(request.getProjectName());
        entity.setRoleName(request.getRoleName());
        entity.setRequirements(request.getRequirements());
        entity.setDeadline(request.getDeadline());
        entity.setBudget(request.getBudget());
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
    }
}

