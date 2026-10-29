package com.celebritycrm.management.agency.service;

import com.celebritycrm.common.exception.ResourceNotFoundException;
import com.celebritycrm.management.agency.dto.AgencyRequest;
import com.celebritycrm.management.agency.dto.AgencyResponse;
import com.celebritycrm.management.agency.entity.Agency;
import com.celebritycrm.management.agency.repository.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;

    @Override
    @Transactional
    public AgencyResponse createAgency(AgencyRequest request) {
        Agency agency = Agency.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .website(request.getWebsite())
                .build();
        return mapToResponse(agencyRepository.save(agency));
    }

    @Override
    @Transactional
    public AgencyResponse updateAgency(Long id, AgencyRequest request) {
        Agency agency = agencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agency", "id", id));
        agency.setName(request.getName());
        agency.setEmail(request.getEmail());
        agency.setPhone(request.getPhone());
        agency.setAddress(request.getAddress());
        agency.setWebsite(request.getWebsite());
        return mapToResponse(agencyRepository.save(agency));
    }

    @Override
    @Transactional(readOnly = true)
    public AgencyResponse getAgencyById(Long id) {
        Agency agency = agencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agency", "id", id));
        return mapToResponse(agency);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgencyResponse> getAllAgencies() {
        return agencyRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteAgency(Long id) {
        if (!agencyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agency", "id", id);
        }
        agencyRepository.deleteById(id);
    }

    private AgencyResponse mapToResponse(Agency entity) {
        return AgencyResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .website(entity.getWebsite())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}

