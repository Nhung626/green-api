package com.green.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "follow")
public class Follow extends AbstractAudit {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_follow_id")
    private Long userFollowId;
}
