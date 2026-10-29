package com.celebritycrm.celebrity.service;

import com.celebritycrm.celebrity.dto.CelebrityRequest;
import com.celebritycrm.celebrity.dto.CelebrityResponse;
import com.celebritycrm.celebrity.dto.CelebritySummaryDto;
import com.celebritycrm.common.enums.CelebrityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CelebrityService {

    CelebrityResponse createCelebrity(CelebrityRequest request);

    CelebrityResponse updateCelebrity(Long id, CelebrityRequest request);

    CelebrityResponse getCelebrityById(Long id);

    Page<CelebrityResponse> getAllCelebrities(Pageable pageable);

    Page<CelebrityResponse> searchCelebrities(String query, String category, CelebrityStatus status, Pageable pageable);

    List<CelebritySummaryDto> getCelebritySummaries();

    void deleteCelebrity(Long id);
}

