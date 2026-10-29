package com.celebritycrm.casting.service;

import com.celebritycrm.casting.dto.CastingCallRequest;
import com.celebritycrm.casting.dto.CastingCallResponse;
import com.celebritycrm.casting.entity.CastingCall;
import com.celebritycrm.casting.mapper.CastingCallMapper;
import com.celebritycrm.casting.repository.CastingCallRepository;
import com.celebritycrm.common.enums.CastingStatus;
import com.celebritycrm.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CastingCallServiceImpl implements CastingCallService {

    private final CastingCallRepository castingCallRepository;
    private final CastingCallMapper castingCallMapper;

    @Override
    @Transactional
    public CastingCallResponse createCastingCall(CastingCallRequest request) {
        CastingCall castingCall = castingCallMapper.toEntity(request);
        CastingCall saved = castingCallRepository.save(castingCall);
        return castingCallMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public CastingCallResponse updateCastingCall(Long id, CastingCallRequest request) {
        CastingCall castingCall = castingCallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CastingCall", "id", id));
        castingCallMapper.updateEntityFromRequest(request, castingCall);
        CastingCall updated = castingCallRepository.save(castingCall);
        return castingCallMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public CastingCallResponse updateStatus(Long id, CastingStatus status) {
        CastingCall castingCall = castingCallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CastingCall", "id", id));
        castingCall.setStatus(status);
        return castingCallMapper.toResponse(castingCallRepository.save(castingCall));
    }

    @Override
    @Transactional(readOnly = true)
    public CastingCallResponse getCastingCallById(Long id) {
        CastingCall castingCall = castingCallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CastingCall", "id", id));
        return castingCallMapper.toResponse(castingCall);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CastingCallResponse> getAllCastingCalls(Pageable pageable) {
        return castingCallRepository.findAll(pageable)
                .map(castingCallMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CastingCallResponse> getOpenCastingCalls() {
        return castingCallRepository.findByStatus(CastingStatus.OPEN).stream()
                .map(castingCallMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteCastingCall(Long id) {
        if (!castingCallRepository.existsById(id)) {
            throw new ResourceNotFoundException("CastingCall", "id", id);
        }
        castingCallRepository.deleteById(id);
    }
}

