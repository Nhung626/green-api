package com.green.dto.userinfo.sdo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfoSelfSdo {
    private Long id;

    private Long userId;

    private Long avatarId;

    private String gender;

    private boolean isGenderP;

    private String dateOfBirth;

    private boolean isDateOfBirthP;

    private String phone;

    private boolean isPhoneP;

    private String address;

    private boolean isAddressP;

}
