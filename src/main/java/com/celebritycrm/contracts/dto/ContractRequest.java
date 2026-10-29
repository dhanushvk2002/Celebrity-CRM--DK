package com.celebritycrm.contracts.dto;

import com.celebritycrm.common.enums.ContractStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractRequest {

    private String contractNumber;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Celebrity ID is required")
    private Long celebrityId;

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Total value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total value must be greater than zero")
    private BigDecimal totalValue;

    private ContractStatus status;
    private String terms;
}

