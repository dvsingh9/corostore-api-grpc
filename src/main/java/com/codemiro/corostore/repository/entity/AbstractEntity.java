package com.codemiro.corostore.repository.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor
@MappedSuperclass
@Slf4j
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    // @Type(type = "pg-uuid")
    public UUID id;

    @CreatedBy
    public String createdBy;

    @LastModifiedBy
    public String updatedBy;

    @CreatedDate
    public LocalDateTime createdOn;

    @LastModifiedDate
    public LocalDateTime updatedOn;

    public String deleteBy;

    public Boolean isDelete;

    @Version
    public Integer version;


    @PrePersist
    protected void prePersist() {
        this.id = UUID.randomUUID();
        createdOn = LocalDateTime.now();
        isDelete = false;

    }

    @PreUpdate
    protected void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

}