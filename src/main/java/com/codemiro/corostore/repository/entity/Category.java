package com.codemiro.corostore.repository.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@EqualsAndHashCode(of = {"name"})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    // @Type(type = "pg-uuid")
    private UUID id;

    private String name;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    @CreatedDate
    private LocalDateTime createdOn;

    @LastModifiedDate
    private LocalDateTime updatedOn;

    private String deleteBy;

    private Boolean isDelete;

    @Version
    private Integer version;


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
