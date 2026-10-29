package com.celebritycrm.ott.dto;

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
public class OttProjectResponse {

    private Long id;
    private String title;
    private String platform;
    private Long celebrityId;
    private String celebrityName;
    private String roleType;
    private BigDecimal budget;
    private LocalDate shootStartDate;
    private String status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

