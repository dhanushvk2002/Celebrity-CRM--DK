package com.celebritycrm.contracts.mapper;

import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.common.enums.ContractStatus;
import com.celebritycrm.contracts.dto.ContractRequest;
import com.celebritycrm.contracts.dto.ContractResponse;
import com.celebritycrm.contracts.entity.Contract;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ContractMapper {

    public Contract toEntity(ContractRequest request, Celebrity celebrity) {
        if (request == null) return null;
        String contractNumber = request.getContractNumber();
        if (contractNumber == null || contractNumber.isBlank()) {
            contractNumber = "CTR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
        return Contract.builder()
                .contractNumber(contractNumber)
                .title(request.getTitle())
                .celebrity(celebrity)
                .clientName(request.getClientName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .totalValue(request.getTotalValue())
                .status(request.getStatus() != null ? request.getStatus() : ContractStatus.DRAFT)
                .terms(request.getTerms())
                .build();
    }

    public ContractResponse toResponse(Contract entity) {
        if (entity == null) return null;
        return ContractResponse.builder()
                .id(entity.getId())
                .contractNumber(entity.getContractNumber())
                .title(entity.getTitle())
                .celebrityId(entity.getCelebrity() != null ? entity.getCelebrity().getId() : null)
                .celebrityName(entity.getCelebrity() != null ? entity.getCelebrity().getFullName() : null)
                .clientName(entity.getClientName())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .totalValue(entity.getTotalValue())
                .status(entity.getStatus())
                .terms(entity.getTerms())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public void updateEntityFromRequest(ContractRequest request, Contract entity, Celebrity celebrity) {
        if (request == null || entity == null) return;
        entity.setTitle(request.getTitle());
        entity.setClientName(request.getClientName());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setTotalValue(request.getTotalValue());
        entity.setTerms(request.getTerms());
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        if (celebrity != null) {
            entity.setCelebrity(celebrity);
        }
    }
}

