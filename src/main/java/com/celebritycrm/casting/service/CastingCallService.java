package com.celebritycrm.casting.service;

import com.celebritycrm.casting.dto.CastingCallRequest;
import com.celebritycrm.casting.dto.CastingCallResponse;
import com.celebritycrm.common.enums.CastingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CastingCallService {
    CastingCallResponse createCastingCall(CastingCallRequest request);
    CastingCallResponse updateCastingCall(Long id, CastingCallRequest request);
    CastingCallResponse updateStatus(Long id, CastingStatus status);
    CastingCallResponse getCastingCallById(Long id);
    Page<CastingCallResponse> getAllCastingCalls(Pageable pageable);
    List<CastingCallResponse> getOpenCastingCalls();
    void deleteCastingCall(Long id);
}

