package com.celebritycrm.ott.service;

import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.celebrity.repository.CelebrityRepository;
import com.celebritycrm.common.exception.ResourceNotFoundException;
import com.celebritycrm.ott.dto.OttProjectRequest;
import com.celebritycrm.ott.dto.OttProjectResponse;
import com.celebritycrm.ott.entity.OttProject;
import com.celebritycrm.ott.mapper.OttProjectMapper;
import com.celebritycrm.ott.repository.OttProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OttProjectServiceImpl implements OttProjectService {

    private final OttProjectRepository ottProjectRepository;
    private final CelebrityRepository celebrityRepository;
    private final OttProjectMapper ottProjectMapper;

    @Override
    @Transactional
    public OttProjectResponse createProject(OttProjectRequest request) {
        Celebrity celebrity = null;
        if (request.getCelebrityId() != null) {
            celebrity = celebrityRepository.findById(request.getCelebrityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Celebrity", "id", request.getCelebrityId()));
        }

        OttProject project = ottProjectMapper.toEntity(request, celebrity);
        OttProject saved = ottProjectRepository.save(project);
        return ottProjectMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public OttProjectResponse updateProject(Long id, OttProjectRequest request) {
        OttProject project = ottProjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OttProject", "id", id));

        Celebrity celebrity = null;
        if (request.getCelebrityId() != null) {
            celebrity = celebrityRepository.findById(request.getCelebrityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Celebrity", "id", request.getCelebrityId()));
        }

        ottProjectMapper.updateEntityFromRequest(request, project, celebrity);
        OttProject updated = ottProjectRepository.save(project);
        return ottProjectMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public OttProjectResponse updateStatus(Long id, String status) {
        OttProject project = ottProjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OttProject", "id", id));
        project.setStatus(status);
        return ottProjectMapper.toResponse(ottProjectRepository.save(project));
    }

    @Override
    @Transactional(readOnly = true)
    public OttProjectResponse getProjectById(Long id) {
        OttProject project = ottProjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OttProject", "id", id));
        return ottProjectMapper.toResponse(project);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OttProjectResponse> getAllProjects(Pageable pageable) {
        return ottProjectRepository.findAll(pageable)
                .map(ottProjectMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OttProjectResponse> getProjectsByCelebrity(Long celebrityId) {
        return ottProjectRepository.findByCelebrityId(celebrityId).stream()
                .map(ottProjectMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        if (!ottProjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("OttProject", "id", id);
        }
        ottProjectRepository.deleteById(id);
    }
}

