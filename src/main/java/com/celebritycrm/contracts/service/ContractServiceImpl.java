package com.celebritycrm.contracts.service;

import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.celebrity.repository.CelebrityRepository;
import com.celebritycrm.common.enums.ContractStatus;
import com.celebritycrm.common.exception.ResourceNotFoundException;
import com.celebritycrm.contracts.dto.ContractRequest;
import com.celebritycrm.contracts.dto.ContractResponse;
import com.celebritycrm.contracts.entity.Contract;
import com.celebritycrm.contracts.mapper.ContractMapper;
import com.celebritycrm.contracts.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final CelebrityRepository celebrityRepository;
    private final ContractMapper contractMapper;

    @Override
    @Transactional
    public ContractResponse createContract(ContractRequest request) {
        Celebrity celebrity = celebrityRepository.findById(request.getCelebrityId())
                .orElseThrow(() -> new ResourceNotFoundException("Celebrity", "id", request.getCelebrityId()));

        Contract contract = contractMapper.toEntity(request, celebrity);
        Contract saved = contractRepository.save(contract);
        return contractMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ContractResponse updateContract(Long id, ContractRequest request) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", id));

        Celebrity celebrity = null;
        if (request.getCelebrityId() != null) {
            celebrity = celebrityRepository.findById(request.getCelebrityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Celebrity", "id", request.getCelebrityId()));
        }

        contractMapper.updateEntityFromRequest(request, contract, celebrity);
        Contract updated = contractRepository.save(contract);
        return contractMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public ContractResponse updateStatus(Long id, ContractStatus status) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", id));
        contract.setStatus(status);
        return contractMapper.toResponse(contractRepository.save(contract));
    }

    @Override
    @Transactional(readOnly = true)
    public ContractResponse getContractById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", id));
        return contractMapper.toResponse(contract);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ContractResponse> getAllContracts(Pageable pageable) {
        return contractRepository.findAll(pageable)
                .map(contractMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractResponse> getContractsByCelebrity(Long celebrityId) {
        return contractRepository.findByCelebrityId(celebrityId).stream()
                .map(contractMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteContract(Long id) {
        if (!contractRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contract", "id", id);
        }
        contractRepository.deleteById(id);
    }
}

