package com.celebritycrm.scheduling;

import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.celebrity.repository.CelebrityRepository;
import com.celebritycrm.common.enums.ScheduleStatus;
import com.celebritycrm.scheduling.dto.ScheduleRequest;
import com.celebritycrm.scheduling.dto.ScheduleResponse;
import com.celebritycrm.scheduling.entity.ScheduleEvent;
import com.celebritycrm.scheduling.mapper.ScheduleMapper;
import com.celebritycrm.scheduling.repository.ScheduleRepository;
import com.celebritycrm.scheduling.service.ScheduleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private CelebrityRepository celebrityRepository;

    @Mock
    private ScheduleMapper scheduleMapper;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Test
    void testCreateScheduleEvent() {
        Celebrity celebrity = Celebrity.builder().id(1L).fullName("Star Person").build();
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);

        ScheduleRequest request = ScheduleRequest.builder()
                .title("Press Conference")
                .celebrityId(1L)
                .eventType("Media")
                .location("Mumbai")
                .startTime(start)
                .endTime(end)
                .build();

        ScheduleEvent event = ScheduleEvent.builder()
                .id(1L)
                .title("Press Conference")
                .celebrity(celebrity)
                .eventType("Media")
                .location("Mumbai")
                .startTime(start)
                .endTime(end)
                .status(ScheduleStatus.SCHEDULED)
                .build();

        ScheduleResponse response = ScheduleResponse.builder()
                .id(1L)
                .title("Press Conference")
                .celebrityId(1L)
                .celebrityName("Star Person")
                .eventType("Media")
                .location("Mumbai")
                .startTime(start)
                .endTime(end)
                .status(ScheduleStatus.SCHEDULED)
                .build();

        when(celebrityRepository.findById(1L)).thenReturn(Optional.of(celebrity));
        when(scheduleMapper.toEntity(request, celebrity)).thenReturn(event);
        when(scheduleRepository.save(event)).thenReturn(event);
        when(scheduleMapper.toResponse(event)).thenReturn(response);

        ScheduleResponse result = scheduleService.createScheduleEvent(request);

        assertNotNull(result);
        assertEquals("Press Conference", result.getTitle());
        verify(scheduleRepository, times(1)).save(event);
    }
}

