package com.celebritycrm.ott.mapper;

import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.ott.dto.OttProjectRequest;
import com.celebritycrm.ott.dto.OttProjectResponse;
import com.celebritycrm.ott.entity.OttProject;
import org.springframework.stereotype.Component;

@Component
public class OttProjectMapper {

    public OttProject toEntity(OttProjectRequest request, Celebrity celebrity) {
        if (request == null) return null;
        return OttProject.builder()
                .title(request.getTitle())
                .platform(request.getPlatform())
                .celebrity(celebrity)
                .roleType(request.getRoleType())
                .budget(request.getBudget())
                .shootStartDate(request.getShootStartDate())
                .status(request.getStatus() != null ? request.getStatus() : "IN_TALKS")
                .description(request.getDescription())
                .build();
    }

    public OttProjectResponse toResponse(OttProject entity) {
        if (entity == null) return null;
        return OttProjectResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .platform(entity.getPlatform())
                .celebrityId(entity.getCelebrity() != null ? entity.getCelebrity().getId() : null)
                .celebrityName(entity.getCelebrity() != null ? entity.getCelebrity().getFullName() : null)
                .roleType(entity.getRoleType())
                .budget(entity.getBudget())
                .shootStartDate(entity.getShootStartDate())
                .status(entity.getStatus())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public void updateEntityFromRequest(OttProjectRequest request, OttProject entity, Celebrity celebrity) {
        if (request == null || entity == null) return;
        entity.setTitle(request.getTitle());
        entity.setPlatform(request.getPlatform());
        entity.setRoleType(request.getRoleType());
        entity.setBudget(request.getBudget());
        entity.setShootStartDate(request.getShootStartDate());
        entity.setDescription(request.getDescription());
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        if (celebrity != null) {
            entity.setCelebrity(celebrity);
        }
    }
}

