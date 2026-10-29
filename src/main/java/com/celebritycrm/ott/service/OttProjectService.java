package com.celebritycrm.ott.service;

import com.celebritycrm.ott.dto.OttProjectRequest;
import com.celebritycrm.ott.dto.OttProjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OttProjectService {
    OttProjectResponse createProject(OttProjectRequest request);
    OttProjectResponse updateProject(Long id, OttProjectRequest request);
    OttProjectResponse updateStatus(Long id, String status);
    OttProjectResponse getProjectById(Long id);
    Page<OttProjectResponse> getAllProjects(Pageable pageable);
    List<OttProjectResponse> getProjectsByCelebrity(Long celebrityId);
    void deleteProject(Long id);
}

