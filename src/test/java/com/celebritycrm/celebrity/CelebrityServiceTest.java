package com.celebritycrm.celebrity;

import com.celebritycrm.celebrity.dto.CelebrityRequest;
import com.celebritycrm.celebrity.dto.CelebrityResponse;
import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.celebrity.mapper.CelebrityMapper;
import com.celebritycrm.celebrity.repository.CelebrityRepository;
import com.celebritycrm.celebrity.service.CelebrityServiceImpl;
import com.celebritycrm.common.enums.CelebrityStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CelebrityServiceTest {

    @Mock
    private CelebrityRepository celebrityRepository;

    @Mock
    private CelebrityMapper celebrityMapper;

    @InjectMocks
    private CelebrityServiceImpl celebrityService;

    @Test
    void testCreateCelebrity() {
        CelebrityRequest request = CelebrityRequest.builder()
                .fullName("John Doe")
                .stageName("JD")
                .baseFee(new BigDecimal("50000.00"))
                .status(CelebrityStatus.ACTIVE)
                .build();

        Celebrity entity = Celebrity.builder()
                .id(1L)
                .fullName("John Doe")
                .stageName("JD")
                .baseFee(new BigDecimal("50000.00"))
                .status(CelebrityStatus.ACTIVE)
                .build();

        CelebrityResponse response = CelebrityResponse.builder()
                .id(1L)
                .fullName("John Doe")
                .stageName("JD")
                .baseFee(new BigDecimal("50000.00"))
                .status(CelebrityStatus.ACTIVE)
                .build();

        when(celebrityMapper.toEntity(request)).thenReturn(entity);
        when(celebrityRepository.save(entity)).thenReturn(entity);
        when(celebrityMapper.toResponse(entity)).thenReturn(response);

        CelebrityResponse result = celebrityService.createCelebrity(request);

        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
        verify(celebrityRepository, times(1)).save(entity);
    }

    @Test
    void testGetCelebrityById() {
        Celebrity entity = Celebrity.builder()
                .id(1L)
                .fullName("John Doe")
                .build();

        CelebrityResponse response = CelebrityResponse.builder()
                .id(1L)
                .fullName("John Doe")
                .build();

        when(celebrityRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(celebrityMapper.toResponse(entity)).thenReturn(response);

        CelebrityResponse result = celebrityService.getCelebrityById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
}

