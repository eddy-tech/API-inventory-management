package com.inventor.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // Ecouter les changements et assigner une valeur à creationDate et lastModifiedDate dans la BD
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@CreatedDate
    @Column(name = "creation_date",nullable = false)
    private Instant creationTime;

    //@LastModifiedDate
    @Column(name = "last_modified_date",nullable = false)
    private Instant lastModifiedTime;

    @PrePersist
    void prePersist(){
        creationTime = Instant.now();
    }

    @PreUpdate
    void preUpdate(){
        lastModifiedTime = Instant.now();
    }
}