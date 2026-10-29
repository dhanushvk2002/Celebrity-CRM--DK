package com.celebritycrm.contracts.repository;

import com.celebritycrm.common.enums.ContractStatus;
import com.celebritycrm.contracts.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    Optional<Contract> findByContractNumber(String contractNumber);

    List<Contract> findByCelebrityId(Long celebrityId);

    Page<Contract> findByStatus(ContractStatus status, Pageable pageable);

    long countByStatus(ContractStatus status);

    @Query("SELECT COALESCE(SUM(c.totalValue), 0) FROM Contract c WHERE c.status = :status")
    BigDecimal sumTotalValueByStatus(ContractStatus status);

    @Query("SELECT COALESCE(SUM(c.totalValue), 0) FROM Contract c")
    BigDecimal sumTotalValue();
}

