package com.celebritycrm.contracts.service;

import com.celebritycrm.common.enums.ContractStatus;
import com.celebritycrm.contracts.dto.ContractRequest;
import com.celebritycrm.contracts.dto.ContractResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContractService {
    ContractResponse createContract(ContractRequest request);
    ContractResponse updateContract(Long id, ContractRequest request);
    ContractResponse updateStatus(Long id, ContractStatus status);
    ContractResponse getContractById(Long id);
    Page<ContractResponse> getAllContracts(Pageable pageable);
    List<ContractResponse> getContractsByCelebrity(Long celebrityId);
    void deleteContract(Long id);
}

