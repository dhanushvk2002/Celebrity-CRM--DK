package com.celebritycrm.contracts;

import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.celebrity.repository.CelebrityRepository;
import com.celebritycrm.common.enums.ContractStatus;
import com.celebritycrm.contracts.dto.ContractRequest;
import com.celebritycrm.contracts.dto.ContractResponse;
import com.celebritycrm.contracts.entity.Contract;
import com.celebritycrm.contracts.mapper.ContractMapper;
import com.celebritycrm.contracts.repository.ContractRepository;
import com.celebritycrm.contracts.service.ContractServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContractServiceTest {

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private CelebrityRepository celebrityRepository;

    @Mock
    private ContractMapper contractMapper;

    @InjectMocks
    private ContractServiceImpl contractService;

    @Test
    void testCreateContract() {
        Celebrity celebrity = Celebrity.builder().id(1L).fullName("Star Person").build();

        ContractRequest request = ContractRequest.builder()
                .title("Brand Endorsement")
                .celebrityId(1L)
                .clientName("MegaBrand")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(6))
                .totalValue(new BigDecimal("100000.00"))
                .build();

        Contract contract = Contract.builder()
                .id(1L)
                .contractNumber("CTR-12345")
                .title("Brand Endorsement")
                .celebrity(celebrity)
                .clientName("MegaBrand")
                .totalValue(new BigDecimal("100000.00"))
                .status(ContractStatus.DRAFT)
                .build();

        ContractResponse response = ContractResponse.builder()
                .id(1L)
                .contractNumber("CTR-12345")
                .title("Brand Endorsement")
                .celebrityId(1L)
                .celebrityName("Star Person")
                .clientName("MegaBrand")
                .totalValue(new BigDecimal("100000.00"))
                .status(ContractStatus.DRAFT)
                .build();

        when(celebrityRepository.findById(1L)).thenReturn(Optional.of(celebrity));
        when(contractMapper.toEntity(request, celebrity)).thenReturn(contract);
        when(contractRepository.save(contract)).thenReturn(contract);
        when(contractMapper.toResponse(contract)).thenReturn(response);

        ContractResponse result = contractService.createContract(request);

        assertNotNull(result);
        assertEquals("CTR-12345", result.getContractNumber());
        assertEquals("MegaBrand", result.getClientName());
        verify(contractRepository, times(1)).save(contract);
    }
}

