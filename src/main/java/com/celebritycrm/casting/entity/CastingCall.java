package com.celebritycrm.casting.entity;

import com.celebritycrm.common.audit.BaseEntity;
import com.celebritycrm.common.enums.CastingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "casting_calls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CastingCall extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(name = "project_name", nullable = false, length = 150)
    private String projectName;

    @Column(name = "role_name", nullable = false, length = 100)
    private String roleName;

    @Column(columnDefinition = "TEXT")
    private String requirements;

    private LocalDate deadline;

    @Column(precision = 15, scale = 2)
    private BigDecimal budget;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    @Builder.Default
    private CastingStatus status = CastingStatus.OPEN;
}

