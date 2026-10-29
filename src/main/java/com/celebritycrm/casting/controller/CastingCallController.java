package com.celebritycrm.casting.controller;

import com.celebritycrm.casting.dto.CastingCallRequest;
import com.celebritycrm.casting.dto.CastingCallResponse;
import com.celebritycrm.casting.service.CastingCallService;
import com.celebritycrm.common.enums.CastingStatus;
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
@RequestMapping("/api/casting")
@RequiredArgsConstructor
@Tag(name = "Casting Calls", description = "Endpoints for managing auditions and casting calls")
public class CastingCallController {

    private final CastingCallService castingCallService;

    @PostMapping
    @Operation(summary = "Create a new casting call")
    public ResponseEntity<ApiResponse<CastingCallResponse>> createCastingCall(@Valid @RequestBody CastingCallRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Casting call created successfully", castingCallService.createCastingCall(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing casting call")
    public ResponseEntity<ApiResponse<CastingCallResponse>> updateCastingCall(
            @PathVariable Long id,
            @Valid @RequestBody CastingCallRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Casting call updated successfully", castingCallService.updateCastingCall(id, request)));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update casting call status")
    public ResponseEntity<ApiResponse<CastingCallResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam CastingStatus status) {
        return ResponseEntity.ok(ApiResponse.success("Status updated successfully", castingCallService.updateStatus(id, status)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get casting call by ID")
    public ResponseEntity<ApiResponse<CastingCallResponse>> getCastingCallById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(castingCallService.getCastingCallById(id)));
    }

    @GetMapping
    @Operation(summary = "Get all casting calls paginated")
    public ResponseEntity<ApiResponse<Page<CastingCallResponse>>> getAllCastingCalls(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(castingCallService.getAllCastingCalls(pageable)));
    }

    @GetMapping("/open")
    @Operation(summary = "Get all open casting calls")
    public ResponseEntity<ApiResponse<List<CastingCallResponse>>> getOpenCastingCalls() {
        return ResponseEntity.ok(ApiResponse.success(castingCallService.getOpenCastingCalls()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a casting call")
    public ResponseEntity<ApiResponse<Void>> deleteCastingCall(@PathVariable Long id) {
        castingCallService.deleteCastingCall(id);
        return ResponseEntity.ok(ApiResponse.success("Casting call deleted successfully", null));
    }
}

