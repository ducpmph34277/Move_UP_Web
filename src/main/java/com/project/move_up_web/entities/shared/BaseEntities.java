package com.project.move_up_web.entities.shared;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntities {
    @CreatedDate
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @CreatedBy
    @Column(name = "createdBy")
    private String createdBy;
    @LastModifiedBy
    @Column(name = "updatedBy")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        createdBy = authentication != null ? authentication.getName() : "System";
        updatedBy = authentication != null ? authentication.getName() : "System";
    }

    @PreUpdate
    public void preUpdate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        updatedAt = LocalDateTime.now();
        updatedBy = authentication != null ? authentication.getName() : "System";
    }
}
