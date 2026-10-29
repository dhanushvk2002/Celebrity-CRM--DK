package com.celebritycrm.management.agency.controller;

import com.celebritycrm.common.response.ApiResponse;
import com.celebritycrm.management.agency.dto.AgencyRequest;
import com.celebritycrm.management.agency.dto.AgencyResponse;
import com.celebritycrm.management.agency.service.AgencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/management/agencies")
@RequiredArgsConstructor
@Tag(name = "Agencies", description = "Endpoints for managing talent and booking agencies")
public class AgencyController {

    private final AgencyService agencyService;

    @PostMapping
    @Operation(summary = "Create an agency")
    public ResponseEntity<ApiResponse<AgencyResponse>> createAgency(@Valid @RequestBody AgencyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Agency created successfully", agencyService.createAgency(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an agency")
    public ResponseEntity<ApiResponse<AgencyResponse>> updateAgency(
            @PathVariable Long id,
            @Valid @RequestBody AgencyRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Agency updated successfully", agencyService.updateAgency(id, request)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get agency by ID")
    public ResponseEntity<ApiResponse<AgencyResponse>> getAgencyById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(agencyService.getAgencyById(id)));
    }

    @GetMapping
    @Operation(summary = "Get all agencies")
    public ResponseEntity<ApiResponse<List<AgencyResponse>>> getAllAgencies() {
        return ResponseEntity.ok(ApiResponse.success(agencyService.getAllAgencies()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an agency")
    public ResponseEntity<ApiResponse<Void>> deleteAgency(@PathVariable Long id) {
        agencyService.deleteAgency(id);
        return ResponseEntity.ok(ApiResponse.success("Agency deleted successfully", null));
    }
}

