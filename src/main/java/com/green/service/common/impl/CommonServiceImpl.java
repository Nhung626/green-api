package com.green.service.common.impl;

import com.green.repository.UserRepo;
import com.green.security.UserDetailsImpl;
import com.green.service.common.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final UserRepo userRepo;

    public UserDetails userDetails() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal);
        } else {
            return null;
        }
    }

    public Long getIdLogin() {
        UserDetails userDetails = userDetails();
        if (userDetails == null) {
            return null;
        }
        return ((UserDetailsImpl) userDetails).getId();
    }

    public String getUsernameLogin() {
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetails();
        if (userDetails == null) {
            return null;
        }
        return userDetails.getUsername();
    }

//    public String getLang() {
//        UserDetailsImpl userDetails = (UserDetailsImpl) userDetails();
//        if (userDetails == null) {
//            return "vi";
//        }
//        return userDetails.getLanguage();
//    }

//    public Long getSessionId() {
//        UserDetailsImpl userDetails = (UserDetailsImpl) userDetails();
//        if (userDetails == null) {
//            return null;
//        }
//        return userDetails.getSessionId();
//    }

    @Override
    public Boolean existUser(Long userId) {
        return userRepo.findUserById(userId).isPresent();
    }
}
