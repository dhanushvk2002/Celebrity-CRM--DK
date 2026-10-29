package com.celebritycrm.dashboard.controller;

import com.celebritycrm.common.response.ApiResponse;
import com.celebritycrm.dashboard.dto.AnalyticsDto;
import com.celebritycrm.dashboard.dto.DashboardSummaryDto;
import com.celebritycrm.dashboard.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Endpoints for executive summaries and analytics KPIs")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    @Operation(summary = "Get executive dashboard KPIs and summary statistics")
    public ResponseEntity<ApiResponse<DashboardSummaryDto>> getSummary() {
        return ResponseEntity.ok(ApiResponse.success(dashboardService.getSummary()));
    }

    @GetMapping("/analytics")
    @Operation(summary = "Get detailed analytics and distributions across CRM modules")
    public ResponseEntity<ApiResponse<AnalyticsDto>> getAnalytics() {
        return ResponseEntity.ok(ApiResponse.success(dashboardService.getAnalytics()));
    }
}

