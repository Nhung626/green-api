package com.green.service.impl;

import com.green.constants.Const;
import com.green.constants.Role;
import com.green.dto.common.pagination.PageInfo;
import com.green.dto.user.sdi.*;
import com.green.dto.user.sdo.*;
import com.green.exception.AppException;
import com.green.model.User;
import com.green.repository.UserRepo;
import com.green.service.UserService;
import com.green.service.common.CommonService;
import com.green.utils.EncryptAndDecrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.green.constants.LabelKey.*;
import static com.green.utils.DataUtil.copyProperties;
import static com.green.utils.DataUtil.validatePassword;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final CommonService commonService;

    @Override
    public Page<UserSearchSdo> search(UserSearchSdi req, PageInfo pageInfo) {
        return userRepo.search(req, pageInfo);
    }

    @Override
    public UserSelfSdo self(UserSelfSdi req) {
        var user = getUser(req.getId());
        return copyProperties(user, UserSelfSdo.class);
    }

    @Override
    public UserRegisterSdo register(UserRegisterSdi req) {
        var userOptional = userRepo.findByEmail(req.getEmail());
        if (userOptional.isPresent())
            throw new AppException(ERROR_ALREADY_EXIST, List.of(LABEL_EMAIL));

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepo.save(user);

        return UserRegisterSdo.of(user.getId());
    }


    @Override
    public UserResetPasswordSdo resetPassword(UserResetPasswordSdi req) {
        var existUser = userRepo.findByEmail(req.getEmail()).orElseThrow(
                () -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_EMAIL))
        );
        if (req.getPassword() == null){
//            otpService.create(otpCreate(existUser.getId(), OTP.Purpose.RESET_PASSWORD));
            return UserResetPasswordSdo.of(existUser.getId());
        }
        var password = req.getPassword();


        if (!validatePassword(password))
            throw new AppException(ERROR_NOT_MATCH, List.of(LABEL_PASSWORD));

        String encryptPassword = encoder.encode(req.getPassword());
        existUser.setPassword(encryptPassword);
        userRepo.save(existUser);

        return UserResetPasswordSdo.of(existUser.getId());
    }

    @Override
    public UserChangePasswordSdo changePassword(UserChangePasswordSdi req) {
        var user = getUser(commonService.getIdLogin());

        if (!EncryptAndDecrypt.decrypt(user.getPassword()).equals(req.getOldPassword()))
            throw new AppException(ERROR_NOT_MATCH, List.of(LABEL_PASSWORD));

        var newPassword = req.getNewPassword();
        if (!validatePassword(newPassword))
            throw new AppException(ERROR_NOT_MATCH, List.of(LABEL_PASSWORD));

        String encryptPassword = encoder.encode(req.getNewPassword());
        user.setPassword(encryptPassword);
        userRepo.save(user);

        return UserChangePasswordSdo.of(user.getId());
    }

    @Override
    public UserUpdateSdo update(UserUpdateSdi req) {
        return null;
    }

    @Override
    public UserDeleteSdo delete(UserDeleteSdi req) {
        var user = getUser(req.getId());
        user.setStatus(Const.GeneralStatus.DELETED);
        return UserDeleteSdo.of(true);
    }
    private User getUser(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_USER, id)));
    }
}
