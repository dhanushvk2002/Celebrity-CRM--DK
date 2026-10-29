package com.celebritycrm.ott.dto;

import jakarta.validation.constraints.NotBlank;
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
public class OttProjectRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Platform is required (e.g. Netflix, Amazon Prime, Hotstar)")
    private String platform;

    private Long celebrityId;
    private String roleType;
    private BigDecimal budget;
    private LocalDate shootStartDate;
    private String status;
    private String description;
}

