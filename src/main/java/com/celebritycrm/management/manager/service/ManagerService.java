package com.celebritycrm.management.manager.service;

import com.celebritycrm.management.manager.dto.ManagerRequest;
import com.celebritycrm.management.manager.dto.ManagerResponse;

import java.util.List;

public interface ManagerService {
    ManagerResponse createManager(ManagerRequest request);
    ManagerResponse updateManager(Long id, ManagerRequest request);
    ManagerResponse getManagerById(Long id);
    List<ManagerResponse> getAllManagers();
    List<ManagerResponse> getManagersByAgency(Long agencyId);
    void deleteManager(Long id);
}

