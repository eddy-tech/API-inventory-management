package com.inventor.management.inventor_management.core.domains;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor @NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Instant creationTime;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedTime;

}
