package com.celebritycrm.campaigns.service;

import com.celebritycrm.campaigns.dto.CampaignRequest;
import com.celebritycrm.campaigns.dto.CampaignResponse;
import com.celebritycrm.campaigns.entity.Campaign;
import com.celebritycrm.campaigns.mapper.CampaignMapper;
import com.celebritycrm.campaigns.repository.CampaignRepository;
import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.celebrity.repository.CelebrityRepository;
import com.celebritycrm.common.enums.CampaignStatus;
import com.celebritycrm.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final CelebrityRepository celebrityRepository;
    private final CampaignMapper campaignMapper;

    @Override
    @Transactional
    public CampaignResponse createCampaign(CampaignRequest request) {
        Celebrity celebrity = null;
        if (request.getCelebrityId() != null) {
            celebrity = celebrityRepository.findById(request.getCelebrityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Celebrity", "id", request.getCelebrityId()));
        }

        Campaign campaign = campaignMapper.toEntity(request, celebrity);
        Campaign saved = campaignRepository.save(campaign);
        return campaignMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public CampaignResponse updateCampaign(Long id, CampaignRequest request) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign", "id", id));

        Celebrity celebrity = null;
        if (request.getCelebrityId() != null) {
            celebrity = celebrityRepository.findById(request.getCelebrityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Celebrity", "id", request.getCelebrityId()));
        }

        campaignMapper.updateEntityFromRequest(request, campaign, celebrity);
        Campaign updated = campaignRepository.save(campaign);
        return campaignMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public CampaignResponse updateStatus(Long id, CampaignStatus status) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign", "id", id));
        campaign.setStatus(status);
        return campaignMapper.toResponse(campaignRepository.save(campaign));
    }

    @Override
    @Transactional(readOnly = true)
    public CampaignResponse getCampaignById(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign", "id", id));
        return campaignMapper.toResponse(campaign);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CampaignResponse> getAllCampaigns(Pageable pageable) {
        return campaignRepository.findAll(pageable)
                .map(campaignMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampaignResponse> getCampaignsByCelebrity(Long celebrityId) {
        return campaignRepository.findByCelebrityId(celebrityId).stream()
                .map(campaignMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteCampaign(Long id) {
        if (!campaignRepository.existsById(id)) {
            throw new ResourceNotFoundException("Campaign", "id", id);
        }
        campaignRepository.deleteById(id);
    }
}

