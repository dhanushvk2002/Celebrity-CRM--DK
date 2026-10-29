package com.celebritycrm.management.manager.controller;

import com.celebritycrm.common.response.ApiResponse;
import com.celebritycrm.management.manager.dto.ManagerRequest;
import com.celebritycrm.management.manager.dto.ManagerResponse;
import com.celebritycrm.management.manager.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/management/managers")
@RequiredArgsConstructor
@Tag(name = "Managers", description = "Endpoints for talent and account managers")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping
    @Operation(summary = "Create a manager profile")
    public ResponseEntity<ApiResponse<ManagerResponse>> createManager(@Valid @RequestBody ManagerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Manager created successfully", managerService.createManager(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a manager profile")
    public ResponseEntity<ApiResponse<ManagerResponse>> updateManager(
            @PathVariable Long id,
            @Valid @RequestBody ManagerRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Manager updated successfully", managerService.updateManager(id, request)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get manager by ID")
    public ResponseEntity<ApiResponse<ManagerResponse>> getManagerById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(managerService.getManagerById(id)));
    }

    @GetMapping
    @Operation(summary = "Get all managers")
    public ResponseEntity<ApiResponse<List<ManagerResponse>>> getAllManagers(
            @RequestParam(required = false) Long agencyId) {
        if (agencyId != null) {
            return ResponseEntity.ok(ApiResponse.success(managerService.getManagersByAgency(agencyId)));
        }
        return ResponseEntity.ok(ApiResponse.success(managerService.getAllManagers()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a manager")
    public ResponseEntity<ApiResponse<Void>> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return ResponseEntity.ok(ApiResponse.success("Manager deleted successfully", null));
    }
}

