package com.celebritycrm.casting.repository;

import com.celebritycrm.casting.entity.CastingCall;
import com.celebritycrm.common.enums.CastingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastingCallRepository extends JpaRepository<CastingCall, Long> {
    Page<CastingCall> findByStatus(CastingStatus status, Pageable pageable);
    List<CastingCall> findByStatus(CastingStatus status);
    long countByStatus(CastingStatus status);
}

