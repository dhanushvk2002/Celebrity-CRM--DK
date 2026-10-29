package com.celebritycrm.management.agency.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgencyRequest {

    @NotBlank(message = "Agency name is required")
    private String name;

    private String email;
    private String phone;
    private String address;
    private String website;
}

