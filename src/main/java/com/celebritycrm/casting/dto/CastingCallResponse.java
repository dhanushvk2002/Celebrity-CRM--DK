package com.celebritycrm.casting.dto;

import com.celebritycrm.common.enums.CastingStatus;
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
public class CastingCallResponse {

    private Long id;
    private String title;
    private String projectName;
    private String roleName;
    private String requirements;
    private LocalDate deadline;
    private BigDecimal budget;
    private CastingStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

