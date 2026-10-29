package com.celebritycrm.celebrity.service;

import com.celebritycrm.celebrity.dto.CelebrityRequest;
import com.celebritycrm.celebrity.dto.CelebrityResponse;
import com.celebritycrm.celebrity.dto.CelebritySummaryDto;
import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.celebrity.mapper.CelebrityMapper;
import com.celebritycrm.celebrity.repository.CelebrityRepository;
import com.celebritycrm.common.enums.CelebrityStatus;
import com.celebritycrm.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CelebrityServiceImpl implements CelebrityService {

    private final CelebrityRepository celebrityRepository;
    private final CelebrityMapper celebrityMapper;

    @Override
    @Transactional
    public CelebrityResponse createCelebrity(CelebrityRequest request) {
        Celebrity celebrity = celebrityMapper.toEntity(request);
        Celebrity saved = celebrityRepository.save(celebrity);
        return celebrityMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public CelebrityResponse updateCelebrity(Long id, CelebrityRequest request) {
        Celebrity celebrity = celebrityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Celebrity", "id", id));
        celebrityMapper.updateEntityFromRequest(request, celebrity);
        Celebrity updated = celebrityRepository.save(celebrity);
        return celebrityMapper.toResponse(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public CelebrityResponse getCelebrityById(Long id) {
        Celebrity celebrity = celebrityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Celebrity", "id", id));
        return celebrityMapper.toResponse(celebrity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CelebrityResponse> getAllCelebrities(Pageable pageable) {
        return celebrityRepository.findAll(pageable)
                .map(celebrityMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CelebrityResponse> searchCelebrities(String query, String category, CelebrityStatus status, Pageable pageable) {
        return celebrityRepository.searchCelebrities(query, category, status, pageable)
                .map(celebrityMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CelebritySummaryDto> getCelebritySummaries() {
        return celebrityRepository.findAll().stream()
                .map(celebrityMapper::toSummary)
                .toList();
    }

    @Override
    @Transactional
    public void deleteCelebrity(Long id) {
        if (!celebrityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Celebrity", "id", id);
        }
        celebrityRepository.deleteById(id);
    }
}

