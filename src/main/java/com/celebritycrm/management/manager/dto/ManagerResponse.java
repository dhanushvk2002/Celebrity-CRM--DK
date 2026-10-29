package com.celebritycrm.management.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerResponse {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private Long agencyId;
    private String agencyName;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

