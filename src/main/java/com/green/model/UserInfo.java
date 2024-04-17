package com.green.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "user_info")
public class UserInfo extends AbstractAudit{
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "avatar_id")
    private Long avatarId;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "is_date_of_birth_p", nullable = false, columnDefinition="boolean DEFAULT false")
    private boolean isDateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "is_gender_p", nullable = false, columnDefinition="boolean DEFAULT false")
    private boolean isGenderP;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_phone_p", nullable = false, columnDefinition="boolean DEFAULT false")
    private boolean isPhoneP;

    @Column(name = "address")
    private String address;

    @Column(name = "is_address_p", nullable = false, columnDefinition="boolean DEFAULT false")
    private boolean isAddressP;

}
