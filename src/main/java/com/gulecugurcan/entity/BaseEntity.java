package com.gulecugurcan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
public class BaseEntity implements Serializable {

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Timestamp createdDate;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private Timestamp updatedDate;

    private Boolean deleteFlag = Boolean.FALSE;
}
