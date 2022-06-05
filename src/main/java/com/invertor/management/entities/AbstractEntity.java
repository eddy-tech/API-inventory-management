package com.invertor.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // Ecouter les changement et assigner une valeur à creationDate et lastModifiedDate dans la BD
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreatedDate
    @Column(name = "creationDate",nullable = false)
    @JsonIgnore
    private Instant creationTime;

    @LastModifiedDate
    @Column(name = "lastModifiedDate",nullable = false)
    @JsonIgnore
    private Instant lastModifiedTime;
}
