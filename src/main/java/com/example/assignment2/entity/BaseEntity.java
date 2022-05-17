package com.example.assignment2.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@Data
@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    private ZonedDateTime createdAt = null;
    @UpdateTimestamp
    private ZonedDateTime updatedAt = null;
}
