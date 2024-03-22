package com.green.service.common;

import org.springframework.security.core.userdetails.UserDetails;

public interface CommonService {
    UserDetails userDetails();

    Long getIdLogin();

    String getUsernameLogin();

    Long getSessionId();

    Boolean existUser(Long userId);
}
