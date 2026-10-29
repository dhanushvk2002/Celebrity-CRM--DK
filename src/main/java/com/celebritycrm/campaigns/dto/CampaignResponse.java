package com.celebritycrm.campaigns.dto;

import com.celebritycrm.common.enums.CampaignStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignResponse {

    private Long id;
    private String title;
    private String brandName;
    private Long celebrityId;
    private String celebrityName;
    private BigDecimal budget;
    private LocalDate startDate;
    private LocalDate endDate;
    private CampaignStatus status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

