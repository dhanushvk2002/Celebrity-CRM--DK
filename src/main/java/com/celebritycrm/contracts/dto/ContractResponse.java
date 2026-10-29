package com.celebritycrm.contracts.dto;

import com.celebritycrm.common.enums.ContractStatus;
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
public class ContractResponse {

    private Long id;
    private String contractNumber;
    private String title;
    private Long celebrityId;
    private String celebrityName;
    private String clientName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalValue;
    private ContractStatus status;
    private String terms;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

