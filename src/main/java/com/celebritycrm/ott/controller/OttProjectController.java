package com.celebritycrm.ott.controller;

import com.celebritycrm.common.response.ApiResponse;
import com.celebritycrm.ott.dto.OttProjectRequest;
import com.celebritycrm.ott.dto.OttProjectResponse;
import com.celebritycrm.ott.service.OttProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ott")
@RequiredArgsConstructor
@Tag(name = "OTT Projects", description = "Endpoints for managing web series, films, and OTT streaming projects")
public class OttProjectController {

    private final OttProjectService ottProjectService;

    @PostMapping
    @Operation(summary = "Create a new OTT project")
    public ResponseEntity<ApiResponse<OttProjectResponse>> createProject(@Valid @RequestBody OttProjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("OTT project created successfully", ottProjectService.createProject(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing OTT project")
    public ResponseEntity<ApiResponse<OttProjectResponse>> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody OttProjectRequest request) {
        return ResponseEntity.ok(ApiResponse.success("OTT project updated successfully", ottProjectService.updateProject(id, request)));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update OTT project status")
    public ResponseEntity<ApiResponse<OttProjectResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(ApiResponse.success("Project status updated", ottProjectService.updateStatus(id, status)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get OTT project by ID")
    public ResponseEntity<ApiResponse<OttProjectResponse>> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(ottProjectService.getProjectById(id)));
    }

    @GetMapping
    @Operation(summary = "Get all OTT projects paginated")
    public ResponseEntity<ApiResponse<Page<OttProjectResponse>>> getAllProjects(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(ottProjectService.getAllProjects(pageable)));
    }

    @GetMapping("/celebrity/{celebrityId}")
    @Operation(summary = "Get OTT projects by celebrity ID")
    public ResponseEntity<ApiResponse<List<OttProjectResponse>>> getProjectsByCelebrity(@PathVariable Long celebrityId) {
        return ResponseEntity.ok(ApiResponse.success(ottProjectService.getProjectsByCelebrity(celebrityId)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an OTT project")
    public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable Long id) {
        ottProjectService.deleteProject(id);
        return ResponseEntity.ok(ApiResponse.success("OTT project deleted successfully", null));
    }
}

