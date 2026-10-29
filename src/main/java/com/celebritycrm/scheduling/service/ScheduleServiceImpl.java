package com.celebritycrm.scheduling.service;

import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.celebrity.repository.CelebrityRepository;
import com.celebritycrm.common.enums.ScheduleStatus;
import com.celebritycrm.common.exception.BusinessException;
import com.celebritycrm.common.exception.ResourceNotFoundException;
import com.celebritycrm.scheduling.dto.ScheduleRequest;
import com.celebritycrm.scheduling.dto.ScheduleResponse;
import com.celebritycrm.scheduling.entity.ScheduleEvent;
import com.celebritycrm.scheduling.mapper.ScheduleMapper;
import com.celebritycrm.scheduling.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CelebrityRepository celebrityRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    @Transactional
    public ScheduleResponse createScheduleEvent(ScheduleRequest request) {
        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new BusinessException("End time must be after start time");
        }

        Celebrity celebrity = celebrityRepository.findById(request.getCelebrityId())
                .orElseThrow(() -> new ResourceNotFoundException("Celebrity", "id", request.getCelebrityId()));

        ScheduleEvent event = scheduleMapper.toEntity(request, celebrity);
        ScheduleEvent saved = scheduleRepository.save(event);
        return scheduleMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ScheduleResponse updateScheduleEvent(Long id, ScheduleRequest request) {
        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new BusinessException("End time must be after start time");
        }

        ScheduleEvent event = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ScheduleEvent", "id", id));

        Celebrity celebrity = null;
        if (request.getCelebrityId() != null) {
            celebrity = celebrityRepository.findById(request.getCelebrityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Celebrity", "id", request.getCelebrityId()));
        }

        scheduleMapper.updateEntityFromRequest(request, event, celebrity);
        ScheduleEvent updated = scheduleRepository.save(event);
        return scheduleMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public ScheduleResponse updateStatus(Long id, ScheduleStatus status) {
        ScheduleEvent event = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ScheduleEvent", "id", id));
        event.setStatus(status);
        return scheduleMapper.toResponse(scheduleRepository.save(event));
    }

    @Override
    @Transactional(readOnly = true)
    public ScheduleResponse getScheduleEventById(Long id) {
        ScheduleEvent event = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ScheduleEvent", "id", id));
        return scheduleMapper.toResponse(event);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ScheduleResponse> getAllScheduleEvents(Pageable pageable) {
        return scheduleRepository.findAll(pageable)
                .map(scheduleMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleResponse> getScheduleByCelebrity(Long celebrityId) {
        return scheduleRepository.findByCelebrityId(celebrityId).stream()
                .map(scheduleMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleResponse> getCelebrityScheduleBetween(Long celebrityId, LocalDateTime start, LocalDateTime end) {
        return scheduleRepository.findCelebrityScheduleBetween(celebrityId, start, end).stream()
                .map(scheduleMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleResponse> getUpcomingEvents() {
        return scheduleRepository.findUpcomingEvents(LocalDateTime.now()).stream()
                .map(scheduleMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteScheduleEvent(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ResourceNotFoundException("ScheduleEvent", "id", id);
        }
        scheduleRepository.deleteById(id);
    }
}

