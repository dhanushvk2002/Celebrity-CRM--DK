package com.celebritycrm.casting.dto;

import com.celebritycrm.common.enums.CastingStatus;
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
public class CastingCallRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotBlank(message = "Role name is required")
    private String roleName;

    private String requirements;
    private LocalDate deadline;
    private BigDecimal budget;
    private CastingStatus status;
}

