package com.celebritycrm.management.agency.service;

import com.celebritycrm.management.agency.dto.AgencyRequest;
import com.celebritycrm.management.agency.dto.AgencyResponse;

import java.util.List;

public interface AgencyService {
    AgencyResponse createAgency(AgencyRequest request);
    AgencyResponse updateAgency(Long id, AgencyRequest request);
    AgencyResponse getAgencyById(Long id);
    List<AgencyResponse> getAllAgencies();
    void deleteAgency(Long id);
}

