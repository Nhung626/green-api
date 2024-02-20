package com.green.model;

import com.green.constants.Const;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.sql.Timestamp;

import static com.green.utils.DataUtil.safeToInt;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAudit implements Serializable {
    private static final long serialVersionUID = 3581298718616904225L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private Integer status;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    private Timestamp updatedAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private Long createdBy;

    @LastModifiedBy
    @Column(name = "updated_by", insertable = false)
    private Long updatedBy;

    @PrePersist
    public void prePersist() {
        this.status = safeToInt(this.status, Const.GeneralStatus.ACTIVE);
    }

    @PreUpdate
    public void preUpdate() {
        this.status = safeToInt(this.status, Const.GeneralStatus.ACTIVE);
    }
}
