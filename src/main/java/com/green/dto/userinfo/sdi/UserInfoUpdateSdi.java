package com.green.dto.userinfo.sdi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

import static com.green.constants.LabelKey.*;
import static com.green.utils.DateTimeUtils.DATE_FORMAT2;

@Data
public class UserInfoUpdateSdi {
    //    @ApiModelProperty(notes = "Id người dùng")
    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;

    @Validation(label = LABEL_USER_INFO_AVATAR, required = true)
    private MultipartFile avatar;

    //    @ApiModelProperty(notes = "Giới tính")
    @Validation(label = LABEL_USER_INFO_GENDER)
    private String gender;

    //    @ApiModelProperty(notes = "Không công khai giới tính")
    @Validation(label = LABEL_USER_INFO_IS_GENDER_P)
    private boolean isGenderP;

    @JsonFormat(pattern = DATE_FORMAT2)
//    @ApiModelProperty(notes = "Ngày sinh")
    @Validation(label = LABEL_USER_INFO_DATE_OF_BIRTH)
    private LocalDate dateOfBirth;

    //    @ApiModelProperty(notes = "Không công khai ngày sinh")
    @Validation(label = LABEL_USER_INFO_IS_DATE_OF_BIRTH_P)
    private boolean isDateOfBirthP;

    //    @ApiModelProperty(notes = "Email")
    @Validation(label = LABEL_USER_INFO_PHONE)
    private String phone;

    //    @ApiModelProperty(notes = "Không công khai email")
    @Validation(label = LABEL_USER_INFO_IS_PHONE_P)
    private boolean isPhoneP;

    //    @ApiModelProperty(notes = "Địa chỉ")
    @Validation(label = LABEL_USER_INFO_ADDRESS)
    private String address;

    //    @ApiModelProperty(notes = "không công khai địa chỉ")
    @Validation(label = LABEL_USER_INFO_IS_ADDRESS_P)
    private boolean isAddressP;
}
