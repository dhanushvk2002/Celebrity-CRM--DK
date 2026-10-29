package com.celebritycrm.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryDto {

    private long totalCelebrities;
    private long activeCelebrities;
    private long totalContracts;
    private long activeContracts;
    private BigDecimal totalContractValue;
    private long totalCampaigns;
    private long activeCampaigns;
    private long upcomingEvents;
    private long openCastingCalls;
}

