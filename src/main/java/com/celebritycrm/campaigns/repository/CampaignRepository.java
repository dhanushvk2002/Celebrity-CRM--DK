package com.celebritycrm.campaigns.repository;

import com.celebritycrm.campaigns.entity.Campaign;
import com.celebritycrm.common.enums.CampaignStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findByCelebrityId(Long celebrityId);

    Page<Campaign> findByStatus(CampaignStatus status, Pageable pageable);

    long countByStatus(CampaignStatus status);
}

