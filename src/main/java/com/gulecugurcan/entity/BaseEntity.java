package com.gulecugurcan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Data
public class BaseEntity implements Serializable {

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Timestamp createdDate;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private Timestamp updatedDate;
}
