package com.celebritycrm.contracts.controller;

import com.celebritycrm.common.enums.ContractStatus;
import com.celebritycrm.common.response.ApiResponse;
import com.celebritycrm.contracts.dto.ContractRequest;
import com.celebritycrm.contracts.dto.ContractResponse;
import com.celebritycrm.contracts.service.ContractService;
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
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
@Tag(name = "Contracts", description = "Endpoints for managing talent and brand endorsement contracts")
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    @Operation(summary = "Create a new contract")
    public ResponseEntity<ApiResponse<ContractResponse>> createContract(@Valid @RequestBody ContractRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Contract created successfully", contractService.createContract(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing contract")
    public ResponseEntity<ApiResponse<ContractResponse>> updateContract(
            @PathVariable Long id,
            @Valid @RequestBody ContractRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Contract updated successfully", contractService.updateContract(id, request)));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update contract status")
    public ResponseEntity<ApiResponse<ContractResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam ContractStatus status) {
        return ResponseEntity.ok(ApiResponse.success("Contract status updated", contractService.updateStatus(id, status)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get contract by ID")
    public ResponseEntity<ApiResponse<ContractResponse>> getContractById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(contractService.getContractById(id)));
    }

    @GetMapping
    @Operation(summary = "Get all contracts paginated")
    public ResponseEntity<ApiResponse<Page<ContractResponse>>> getAllContracts(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(contractService.getAllContracts(pageable)));
    }

    @GetMapping("/celebrity/{celebrityId}")
    @Operation(summary = "Get contracts by celebrity ID")
    public ResponseEntity<ApiResponse<List<ContractResponse>>> getContractsByCelebrity(@PathVariable Long celebrityId) {
        return ResponseEntity.ok(ApiResponse.success(contractService.getContractsByCelebrity(celebrityId)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a contract")
    public ResponseEntity<ApiResponse<Void>> deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        return ResponseEntity.ok(ApiResponse.success("Contract deleted successfully", null));
    }
}

