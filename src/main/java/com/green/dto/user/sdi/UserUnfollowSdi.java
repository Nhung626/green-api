package com.green.dto.user.sdi;

import lombok.Data;

@Data
public class UserUnfollowSdi {
    private Long userId;

    private Long userFollowId;
}
