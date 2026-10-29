package com.celebritycrm.celebrity.repository;

import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.common.enums.CelebrityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity, Long> {

    List<Celebrity> findByStatus(CelebrityStatus status);

    List<Celebrity> findByCategoryIgnoreCase(String category);

    @Query("SELECT c FROM Celebrity c WHERE " +
           "(:query IS NULL OR LOWER(c.fullName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(c.stageName) LIKE LOWER(CONCAT('%', :query, '%'))) AND " +
           "(:category IS NULL OR LOWER(c.category) = LOWER(:category)) AND " +
           "(:status IS NULL OR c.status = :status)")
    Page<Celebrity> searchCelebrities(@Param("query") String query,
                                      @Param("category") String category,
                                      @Param("status") CelebrityStatus status,
                                      Pageable pageable);

    long countByStatus(CelebrityStatus status);
}

