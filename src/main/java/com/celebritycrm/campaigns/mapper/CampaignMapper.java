package com.celebritycrm.campaigns.mapper;

import com.celebritycrm.campaigns.dto.CampaignRequest;
import com.celebritycrm.campaigns.dto.CampaignResponse;
import com.celebritycrm.campaigns.entity.Campaign;
import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.common.enums.CampaignStatus;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapper {

    public Campaign toEntity(CampaignRequest request, Celebrity celebrity) {
        if (request == null) return null;
        return Campaign.builder()
                .title(request.getTitle())
                .brandName(request.getBrandName())
                .celebrity(celebrity)
                .budget(request.getBudget())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(request.getStatus() != null ? request.getStatus() : CampaignStatus.PLANNING)
                .description(request.getDescription())
                .build();
    }

    public CampaignResponse toResponse(Campaign entity) {
        if (entity == null) return null;
        return CampaignResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .brandName(entity.getBrandName())
                .celebrityId(entity.getCelebrity() != null ? entity.getCelebrity().getId() : null)
                .celebrityName(entity.getCelebrity() != null ? entity.getCelebrity().getFullName() : null)
                .budget(entity.getBudget())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .status(entity.getStatus())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public void updateEntityFromRequest(CampaignRequest request, Campaign entity, Celebrity celebrity) {
        if (request == null || entity == null) return;
        entity.setTitle(request.getTitle());
        entity.setBrandName(request.getBrandName());
        entity.setBudget(request.getBudget());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setDescription(request.getDescription());
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        if (celebrity != null) {
            entity.setCelebrity(celebrity);
        }
    }
}

