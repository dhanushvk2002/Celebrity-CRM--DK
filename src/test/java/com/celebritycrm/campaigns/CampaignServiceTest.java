package com.celebritycrm.campaigns;

import com.celebritycrm.campaigns.dto.CampaignRequest;
import com.celebritycrm.campaigns.dto.CampaignResponse;
import com.celebritycrm.campaigns.entity.Campaign;
import com.celebritycrm.campaigns.mapper.CampaignMapper;
import com.celebritycrm.campaigns.repository.CampaignRepository;
import com.celebritycrm.campaigns.service.CampaignServiceImpl;
import com.celebritycrm.celebrity.repository.CelebrityRepository;
import com.celebritycrm.common.enums.CampaignStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private CelebrityRepository celebrityRepository;

    @Mock
    private CampaignMapper campaignMapper;

    @InjectMocks
    private CampaignServiceImpl campaignService;

    @Test
    void testCreateCampaign() {
        CampaignRequest request = CampaignRequest.builder()
                .title("Summer Launch")
                .brandName("Nike")
                .budget(new BigDecimal("75000.00"))
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(2))
                .status(CampaignStatus.PLANNING)
                .build();

        Campaign campaign = Campaign.builder()
                .id(1L)
                .title("Summer Launch")
                .brandName("Nike")
                .budget(new BigDecimal("75000.00"))
                .status(CampaignStatus.PLANNING)
                .build();

        CampaignResponse response = CampaignResponse.builder()
                .id(1L)
                .title("Summer Launch")
                .brandName("Nike")
                .budget(new BigDecimal("75000.00"))
                .status(CampaignStatus.PLANNING)
                .build();

        when(campaignMapper.toEntity(request, null)).thenReturn(campaign);
        when(campaignRepository.save(campaign)).thenReturn(campaign);
        when(campaignMapper.toResponse(campaign)).thenReturn(response);

        CampaignResponse result = campaignService.createCampaign(request);

        assertNotNull(result);
        assertEquals("Summer Launch", result.getTitle());
        verify(campaignRepository, times(1)).save(campaign);
    }
}

