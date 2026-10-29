package com.celebritycrm.dashboard.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DashboardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Map<String, Long> getCountGroupedBy(String entityName, String groupField) {
        String jpql = "SELECT e." + groupField + ", COUNT(e) FROM " + entityName + " e GROUP BY e." + groupField;
        List<Object[]> results = entityManager.createQuery(jpql, Object[].class).getResultList();
        Map<String, Long> map = new HashMap<>();
        for (Object[] row : results) {
            String key = row[0] != null ? row[0].toString() : "UNKNOWN";
            Long count = ((Number) row[1]).longValue();
            map.put(key, count);
        }
        return map;
    }
}

