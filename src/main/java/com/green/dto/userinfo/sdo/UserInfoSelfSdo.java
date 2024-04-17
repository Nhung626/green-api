package com.green.dto.userinfo.sdo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfoSelfSdo {
    private Long id;

    private Long userId;

    private Long avatarId;

    private int gender;

    private boolean isGenderP;

    private LocalDate dateOfBirth;

    private boolean isDateOfBirthP;

    private String phone;

    private boolean isPhoneP;

    private String address;

    private boolean isAddressP;

}
