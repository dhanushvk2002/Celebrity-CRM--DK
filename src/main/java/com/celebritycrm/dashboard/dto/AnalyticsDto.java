package com.celebritycrm.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsDto {

    private Map<String, Long> contractsByStatus;
    private Map<String, Long> campaignsByStatus;
    private Map<String, Long> celebritiesByStatus;
    private Map<String, Long> castingCallsByStatus;
    private BigDecimal totalRevenue;
}

