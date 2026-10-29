package com.celebritycrm.celebrity.dto;

import com.celebritycrm.common.enums.CelebrityStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CelebrityRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    private String stageName;
    private String email;
    private String phone;
    private String category;
    private String instagramHandle;
    private String twitterHandle;
    private String bio;
    private BigDecimal baseFee;
    private String avatarUrl;
    private CelebrityStatus status;
}

