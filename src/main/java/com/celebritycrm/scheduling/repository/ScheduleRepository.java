package com.celebritycrm.scheduling.repository;

import com.celebritycrm.common.enums.ScheduleStatus;
import com.celebritycrm.scheduling.entity.ScheduleEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEvent, Long> {

    List<ScheduleEvent> findByCelebrityId(Long celebrityId);

    @Query("SELECT s FROM ScheduleEvent s WHERE s.celebrity.id = :celebrityId AND " +
           "s.startTime >= :start AND s.endTime <= :end ORDER BY s.startTime ASC")
    List<ScheduleEvent> findCelebrityScheduleBetween(
            @Param("celebrityId") Long celebrityId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    @Query("SELECT s FROM ScheduleEvent s WHERE s.startTime >= :now ORDER BY s.startTime ASC")
    List<ScheduleEvent> findUpcomingEvents(@Param("now") LocalDateTime now);

    Page<ScheduleEvent> findByStatus(ScheduleStatus status, Pageable pageable);

    long countByStartTimeAfter(LocalDateTime time);
}

