package com.celebritycrm.management.agency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgencyResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String website;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

