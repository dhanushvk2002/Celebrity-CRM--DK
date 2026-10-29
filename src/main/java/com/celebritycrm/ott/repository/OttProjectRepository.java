package com.celebritycrm.ott.repository;

import com.celebritycrm.ott.entity.OttProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OttProjectRepository extends JpaRepository<OttProject, Long> {
    List<OttProject> findByCelebrityId(Long celebrityId);
    Page<OttProject> findByPlatformIgnoreCase(String platform, Pageable pageable);
    long countByStatus(String status);
}

