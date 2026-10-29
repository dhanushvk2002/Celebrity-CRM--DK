package com.celebritycrm.celebrity.mapper;

import com.celebritycrm.celebrity.dto.CelebrityRequest;
import com.celebritycrm.celebrity.dto.CelebrityResponse;
import com.celebritycrm.celebrity.dto.CelebritySummaryDto;
import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.common.enums.CelebrityStatus;
import org.springframework.stereotype.Component;

@Component
public class CelebrityMapper {

    public Celebrity toEntity(CelebrityRequest request) {
        if (request == null) return null;
        return Celebrity.builder()
                .fullName(request.getFullName())
                .stageName(request.getStageName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .category(request.getCategory())
                .instagramHandle(request.getInstagramHandle())
                .twitterHandle(request.getTwitterHandle())
                .bio(request.getBio())
                .baseFee(request.getBaseFee())
                .avatarUrl(request.getAvatarUrl())
                .status(request.getStatus() != null ? request.getStatus() : CelebrityStatus.ACTIVE)
                .build();
    }

    public CelebrityResponse toResponse(Celebrity entity) {
        if (entity == null) return null;
        return CelebrityResponse.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .stageName(entity.getStageName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .category(entity.getCategory())
                .instagramHandle(entity.getInstagramHandle())
                .twitterHandle(entity.getTwitterHandle())
                .bio(entity.getBio())
                .baseFee(entity.getBaseFee())
                .avatarUrl(entity.getAvatarUrl())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public CelebritySummaryDto toSummary(Celebrity entity) {
        if (entity == null) return null;
        return CelebritySummaryDto.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .stageName(entity.getStageName())
                .category(entity.getCategory())
                .avatarUrl(entity.getAvatarUrl())
                .baseFee(entity.getBaseFee())
                .status(entity.getStatus())
                .build();
    }

    public void updateEntityFromRequest(CelebrityRequest request, Celebrity entity) {
        if (request == null || entity == null) return;
        entity.setFullName(request.getFullName());
        entity.setStageName(request.getStageName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setCategory(request.getCategory());
        entity.setInstagramHandle(request.getInstagramHandle());
        entity.setTwitterHandle(request.getTwitterHandle());
        entity.setBio(request.getBio());
        entity.setBaseFee(request.getBaseFee());
        entity.setAvatarUrl(request.getAvatarUrl());
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
    }
}

