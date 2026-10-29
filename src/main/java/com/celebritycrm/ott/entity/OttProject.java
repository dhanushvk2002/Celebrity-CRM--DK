package com.celebritycrm.ott.entity;

import com.celebritycrm.celebrity.entity.Celebrity;
import com.celebritycrm.common.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ott_projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OttProject extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String platform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "celebrity_id")
    private Celebrity celebrity;

    @Column(name = "role_type", length = 100)
    private String roleType;

    @Column(precision = 15, scale = 2)
    private BigDecimal budget;

    @Column(name = "shoot_start_date")
    private LocalDate shootStartDate;

    @Column(nullable = false, length = 50)
    @Builder.Default
    private String status = "IN_TALKS";

    @Column(columnDefinition = "TEXT")
    private String description;
}

