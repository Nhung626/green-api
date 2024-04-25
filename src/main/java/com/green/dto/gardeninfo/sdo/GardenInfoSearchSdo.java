package com.green.dto.gardeninfo.sdo;

import com.green.dto.userinfo.sdo.UserInfoSearchSdo;
import com.green.model.UserInfo;
import lombok.Data;

@Data
public class GardenInfoSearchSdo {
    private Long id;
    private Long userId;
    private String name;
    private Long coverId;
    private String description;
    private UserInfoSearchSdo userInfo;
}
