package com.celebritycrm.campaigns.controller;

import com.celebritycrm.campaigns.dto.CampaignRequest;
import com.celebritycrm.campaigns.dto.CampaignResponse;
import com.celebritycrm.campaigns.service.CampaignService;
import com.celebritycrm.common.enums.CampaignStatus;
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
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
@Tag(name = "Campaigns", description = "Endpoints for managing marketing campaigns and endorsements")
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    @Operation(summary = "Create a new campaign")
    public ResponseEntity<ApiResponse<CampaignResponse>> createCampaign(@Valid @RequestBody CampaignRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Campaign created successfully", campaignService.createCampaign(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing campaign")
    public ResponseEntity<ApiResponse<CampaignResponse>> updateCampaign(
            @PathVariable Long id,
            @Valid @RequestBody CampaignRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Campaign updated successfully", campaignService.updateCampaign(id, request)));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update campaign status")
    public ResponseEntity<ApiResponse<CampaignResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam CampaignStatus status) {
        return ResponseEntity.ok(ApiResponse.success("Campaign status updated", campaignService.updateStatus(id, status)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get campaign by ID")
    public ResponseEntity<ApiResponse<CampaignResponse>> getCampaignById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(campaignService.getCampaignById(id)));
    }

    @GetMapping
    @Operation(summary = "Get all campaigns paginated")
    public ResponseEntity<ApiResponse<Page<CampaignResponse>>> getAllCampaigns(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(campaignService.getAllCampaigns(pageable)));
    }

    @GetMapping("/celebrity/{celebrityId}")
    @Operation(summary = "Get campaigns by celebrity ID")
    public ResponseEntity<ApiResponse<List<CampaignResponse>>> getCampaignsByCelebrity(@PathVariable Long celebrityId) {
        return ResponseEntity.ok(ApiResponse.success(campaignService.getCampaignsByCelebrity(celebrityId)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a campaign")
    public ResponseEntity<ApiResponse<Void>> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.ok(ApiResponse.success("Campaign deleted successfully", null));
    }
}

