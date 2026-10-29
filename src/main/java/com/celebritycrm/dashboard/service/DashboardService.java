package com.celebritycrm.dashboard.service;

import com.celebritycrm.campaigns.repository.CampaignRepository;
import com.celebritycrm.casting.repository.CastingCallRepository;
import com.celebritycrm.celebrity.repository.CelebrityRepository;
import com.celebritycrm.common.enums.CampaignStatus;
import com.celebritycrm.common.enums.CastingStatus;
import com.celebritycrm.common.enums.CelebrityStatus;
import com.celebritycrm.common.enums.ContractStatus;
import com.celebritycrm.contracts.repository.ContractRepository;
import com.celebritycrm.dashboard.dto.AnalyticsDto;
import com.celebritycrm.dashboard.dto.DashboardSummaryDto;
import com.celebritycrm.dashboard.repository.DashboardRepository;
import com.celebritycrm.scheduling.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CelebrityRepository celebrityRepository;
    private final ContractRepository contractRepository;
    private final CampaignRepository campaignRepository;
    private final ScheduleRepository scheduleRepository;
    private final CastingCallRepository castingCallRepository;
    private final DashboardRepository dashboardRepository;

    @Transactional(readOnly = true)
    public DashboardSummaryDto getSummary() {
        BigDecimal totalValue = contractRepository.sumTotalValue();
        if (totalValue == null) {
            totalValue = BigDecimal.ZERO;
        }

        return DashboardSummaryDto.builder()
                .totalCelebrities(celebrityRepository.count())
                .activeCelebrities(celebrityRepository.countByStatus(CelebrityStatus.ACTIVE))
                .totalContracts(contractRepository.count())
                .activeContracts(contractRepository.countByStatus(ContractStatus.ACTIVE))
                .totalContractValue(totalValue)
                .totalCampaigns(campaignRepository.count())
                .activeCampaigns(campaignRepository.countByStatus(CampaignStatus.ACTIVE))
                .upcomingEvents(scheduleRepository.countByStartTimeAfter(LocalDateTime.now()))
                .openCastingCalls(castingCallRepository.countByStatus(CastingStatus.OPEN))
                .build();
    }

    @Transactional(readOnly = true)
    public AnalyticsDto getAnalytics() {
        BigDecimal totalRevenue = contractRepository.sumTotalValue();
        if (totalRevenue == null) {
            totalRevenue = BigDecimal.ZERO;
        }

        return AnalyticsDto.builder()
                .contractsByStatus(dashboardRepository.getCountGroupedBy("Contract", "status"))
                .campaignsByStatus(dashboardRepository.getCountGroupedBy("Campaign", "status"))
                .celebritiesByStatus(dashboardRepository.getCountGroupedBy("Celebrity", "status"))
                .castingCallsByStatus(dashboardRepository.getCountGroupedBy("CastingCall", "status"))
                .totalRevenue(totalRevenue)
                .build();
    }
}

