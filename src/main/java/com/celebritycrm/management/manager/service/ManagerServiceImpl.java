package com.celebritycrm.management.manager.service;

import com.celebritycrm.common.exception.ResourceNotFoundException;
import com.celebritycrm.management.agency.entity.Agency;
import com.celebritycrm.management.agency.repository.AgencyRepository;
import com.celebritycrm.management.manager.dto.ManagerRequest;
import com.celebritycrm.management.manager.dto.ManagerResponse;
import com.celebritycrm.management.manager.entity.Manager;
import com.celebritycrm.management.manager.repository.ManagerRepository;
import com.celebritycrm.user.User;
import com.celebritycrm.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final AgencyRepository agencyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ManagerResponse createManager(ManagerRequest request) {
        Agency agency = null;
        if (request.getAgencyId() != null) {
            agency = agencyRepository.findById(request.getAgencyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Agency", "id", request.getAgencyId()));
        }

        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));
        }

        Manager manager = Manager.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .agency(agency)
                .user(user)
                .build();

        return mapToResponse(managerRepository.save(manager));
    }

    @Override
    @Transactional
    public ManagerResponse updateManager(Long id, ManagerRequest request) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager", "id", id));

        manager.setFullName(request.getFullName());
        manager.setEmail(request.getEmail());
        manager.setPhone(request.getPhone());

        if (request.getAgencyId() != null) {
            Agency agency = agencyRepository.findById(request.getAgencyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Agency", "id", request.getAgencyId()));
            manager.setAgency(agency);
        } else {
            manager.setAgency(null);
        }

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));
            manager.setUser(user);
        } else {
            manager.setUser(null);
        }

        return mapToResponse(managerRepository.save(manager));
    }

    @Override
    @Transactional(readOnly = true)
    public ManagerResponse getManagerById(Long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager", "id", id));
        return mapToResponse(manager);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ManagerResponse> getAllManagers() {
        return managerRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ManagerResponse> getManagersByAgency(Long agencyId) {
        return managerRepository.findByAgencyId(agencyId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteManager(Long id) {
        if (!managerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Manager", "id", id);
        }
        managerRepository.deleteById(id);
    }

    private ManagerResponse mapToResponse(Manager entity) {
        return ManagerResponse.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .agencyId(entity.getAgency() != null ? entity.getAgency().getId() : null)
                .agencyName(entity.getAgency() != null ? entity.getAgency().getName() : null)
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}

