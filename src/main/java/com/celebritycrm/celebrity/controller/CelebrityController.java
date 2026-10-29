package com.celebritycrm.celebrity.controller;

import com.celebritycrm.celebrity.dto.CelebrityRequest;
import com.celebritycrm.celebrity.dto.CelebrityResponse;
import com.celebritycrm.celebrity.dto.CelebritySummaryDto;
import com.celebritycrm.celebrity.service.CelebrityService;
import com.celebritycrm.common.enums.CelebrityStatus;
import com.celebritycrm.common.response.ApiResponse;
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
@RequestMapping("/api/celebrities")
@RequiredArgsConstructor
@Tag(name = "Celebrities", description = "Endpoints for managing celebrities and talent")
public class CelebrityController {

    private final CelebrityService celebrityService;

    @PostMapping
    @Operation(summary = "Create a new celebrity profile")
    public ResponseEntity<ApiResponse<CelebrityResponse>> createCelebrity(@Valid @RequestBody CelebrityRequest request) {
        CelebrityResponse created = celebrityService.createCelebrity(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Celebrity created successfully", created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing celebrity profile")
    public ResponseEntity<ApiResponse<CelebrityResponse>> updateCelebrity(
            @PathVariable Long id,
            @Valid @RequestBody CelebrityRequest request) {
        CelebrityResponse updated = celebrityService.updateCelebrity(id, request);
        return ResponseEntity.ok(ApiResponse.success("Celebrity updated successfully", updated));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get celebrity details by ID")
    public ResponseEntity<ApiResponse<CelebrityResponse>> getCelebrityById(@PathVariable Long id) {
        CelebrityResponse celebrity = celebrityService.getCelebrityById(id);
        return ResponseEntity.ok(ApiResponse.success(celebrity));
    }

    @GetMapping
    @Operation(summary = "Get all celebrities paginated")
    public ResponseEntity<ApiResponse<Page<CelebrityResponse>>> getAllCelebrities(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        Page<CelebrityResponse> page = celebrityService.getAllCelebrities(pageable);
        return ResponseEntity.ok(ApiResponse.success(page));
    }

    @GetMapping("/search")
    @Operation(summary = "Search celebrities by name, category, or status")
    public ResponseEntity<ApiResponse<Page<CelebrityResponse>>> searchCelebrities(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) CelebrityStatus status,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<CelebrityResponse> results = celebrityService.searchCelebrities(query, category, status, pageable);
        return ResponseEntity.ok(ApiResponse.success(results));
    }

    @GetMapping("/summaries")
    @Operation(summary = "Get lightweight list of all celebrities for dropdowns/selectors")
    public ResponseEntity<ApiResponse<List<CelebritySummaryDto>>> getCelebritySummaries() {
        return ResponseEntity.ok(ApiResponse.success(celebrityService.getCelebritySummaries()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a celebrity profile")
    public ResponseEntity<ApiResponse<Void>> deleteCelebrity(@PathVariable Long id) {
        celebrityService.deleteCelebrity(id);
        return ResponseEntity.ok(ApiResponse.success("Celebrity deleted successfully", null));
    }
}

