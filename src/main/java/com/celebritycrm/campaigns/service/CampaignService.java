package com.celebritycrm.campaigns.service;

import com.celebritycrm.campaigns.dto.CampaignRequest;
import com.celebritycrm.campaigns.dto.CampaignResponse;
import com.celebritycrm.common.enums.CampaignStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampaignService {
    CampaignResponse createCampaign(CampaignRequest request);
    CampaignResponse updateCampaign(Long id, CampaignRequest request);
    CampaignResponse updateStatus(Long id, CampaignStatus status);
    CampaignResponse getCampaignById(Long id);
    Page<CampaignResponse> getAllCampaigns(Pageable pageable);
    List<CampaignResponse> getCampaignsByCelebrity(Long celebrityId);
    void deleteCampaign(Long id);
}

