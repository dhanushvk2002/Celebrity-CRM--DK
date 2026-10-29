package com.celebritycrm.celebrity.dto;

import com.celebritycrm.common.enums.CelebrityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CelebritySummaryDto {

    private Long id;
    private String fullName;
    private String stageName;
    private String category;
    private String avatarUrl;
    private BigDecimal baseFee;
    private CelebrityStatus status;
}

