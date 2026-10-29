package com.celebritycrm.celebrity.entity;

import com.celebritycrm.common.audit.BaseEntity;
import com.celebritycrm.common.enums.CelebrityStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "celebrities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Celebrity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "stage_name", length = 150)
    private String stageName;

    @Column(unique = true, length = 150)
    private String email;

    @Column(length = 50)
    private String phone;

    @Column(length = 100)
    private String category;

    @Column(name = "instagram_handle", length = 100)
    private String instagramHandle;

    @Column(name = "twitter_handle", length = 100)
    private String twitterHandle;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "base_fee", precision = 15, scale = 2)
    private BigDecimal baseFee;

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    @Builder.Default
    private CelebrityStatus status = CelebrityStatus.ACTIVE;
}

