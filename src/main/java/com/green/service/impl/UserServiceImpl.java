package com.green.service.impl;

import com.green.constants.Const;
import com.green.constants.Role;
import com.green.dto.post.sdo.PostSaveSdo;
import com.green.dto.post.sdo.PostUnSaveSdo;
import com.green.dto.user.sdi.*;
import com.green.dto.user.sdo.*;
import com.green.exception.AppException;
import com.green.model.Follow;
import com.green.model.LikePost;
import com.green.model.SavePost;
import com.green.model.User;
import com.green.repository.FollowRepo;
import com.green.repository.UserRepo;
import com.green.service.UserService;
import com.green.service.common.CommonService;
import com.green.utils.EncryptAndDecrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.green.constants.LabelKey.*;
import static com.green.utils.DataUtil.copyProperties;
import static com.green.utils.DataUtil.validatePassword;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final FollowRepo followRepo;
    private final PasswordEncoder encoder;
    private final CommonService commonService;

    @Override
    public List<UserSearchSdo> search(UserSearchSdi request) {
        return userRepo.search(request);
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

    @Override
    public UserFollowSdo follow(UserFollowSdi req) {
        var userId = req.getUserId();
        var userFollowId = req.getUserFollowId();

        Optional<Follow> existingSave = followRepo.findByUserIdAndUserFollowId(userId, userFollowId);
        if (existingSave.isPresent()) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_POST_SAVE));
        }

        var newSave = new Follow();
        newSave.setUserId(userId);
        newSave.setUserId(userFollowId);

        followRepo.save(newSave);
        return UserFollowSdo.of(true);
    }

    @Override
    public UserUnfollowSdo unfollow(UserUnfollowSdi req) {
        var userId = req.getUserId();
        var userFollowId = req.getUserFollowId();

        Optional<Follow> existingUnSave = followRepo.findByUserIdAndUserFollowId(userId, userFollowId);
        if (existingUnSave.isPresent()) {
            followRepo.deleteFollow(userId, userFollowId);
            return UserUnfollowSdo.of(true);
        }
        throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_POST_UNSAVE));
    }

    private User getUser(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_USER, id)));
    }
}
