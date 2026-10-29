package com.celebritycrm.celebrity.dto;

import com.celebritycrm.common.enums.CelebrityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CelebrityResponse {

    private Long id;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

