package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.userinfo.sdi.*;
import com.green.dto.userinfo.sdo.*;
import com.green.exception.AppException;
import com.green.model.Follow;
import com.green.model.UserInfo;
import com.green.repository.FollowRepo;
import com.green.repository.UserInfoRepo;
import com.green.service.MediaService;
import com.green.service.UserInfoService;
import com.green.service.common.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.green.constants.LabelKey.*;
import static com.green.utils.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepo userInfoRepo;
    private final CommonService commonService;
    private final MediaService mediaService;
    private final FollowRepo followRepo;

    public UserInfoCreateSdo create(UserInfoCreateSdi req) throws IOException {
        var avatar = req.getAvatar();
        Long userId = commonService.getIdLogin();

        var userInfoOptional = userInfoRepo.findByUserId(userId);
        if (userInfoOptional.isPresent())
            throw new AppException(ERROR_ALREADY_EXIST, List.of(LABEL_USER_INFO));

        var newUserInfo = copyProperties(req, UserInfo.class);
        newUserInfo.setUserId(userId);
        if (!avatar.isEmpty()) {
            var avatarDto = mediaService.uploadFile(avatar);
            newUserInfo.setAvatarId(avatarDto.getId());
        }

        userInfoRepo.save(newUserInfo);
        return UserInfoCreateSdo.of(newUserInfo.getId());
    }

    public UserInfoUpdateSdo update(UserInfoUpdateSdi req) throws IOException {
        MultipartFile avatar = req.getAvatar();
        Long userId = commonService.getIdLogin();
        var userInfo = getUserInfoByUserID(userId);

        if (!avatar.isEmpty()) {
            var avatarDto = mediaService.uploadFile(avatar);
            userInfo.setAvatarId(avatarDto.getId());
        }

        BeanUtils.copyProperties(req, userInfo);
        userInfoRepo.save(userInfo);
        return UserInfoUpdateSdo.of(userInfo.getId());
    }

    public UserInfoSearchSdo search(UserInfoSearchSdi req) {
        Long userId = commonService.getIdLogin();
        return userInfoRepo.search(req, userId);
    }

    public UserInfoDeleteSdo delete(UserInfoDeleteSdi req) {
        var userInfo = getUserInfo(req.getId());
        checkUser(userInfo.getUserId());

        userInfo.setStatus(Const.GeneralStatus.DELETED);
        userInfoRepo.save(userInfo);
        return UserInfoDeleteSdo.of(true);
    }

    public UserInfoSelfSdo self(UserInfoSelfSdi req) {
        var userInfo = getUserInfo(req.getId());
        return copyProperties(userInfo, UserInfoSelfSdo.class);
    }

    public UserAvatarUpdateSdo uploadAvatar(UserAvatarUpdateSdi req) throws IOException {
        var avatar = req.getAvatar();
        checkUser(req.getUserId());

        var userInfo = getUserInfoByUserID(req.getUserId());
        if (avatar.isEmpty()) {
            throw new AppException(ERROR_FILE_OR_URL_REQUIRED, LABEL_USER_INFO_AVATAR);
        }

        var avatarDto = mediaService.uploadFile(avatar);
        userInfo.setAvatarId(avatarDto.getId());

        userInfoRepo.save(userInfo);
        return UserAvatarUpdateSdo.of(userInfo.getId());
    }

    @Override
    public UserFollowSdo follow(UserFollowSdi req) {
        var userId = commonService.getIdLogin();
        var userFollowId = req.getUserFollowId();

        var existingSave = followRepo.findByUserIdAndUserFollowId(userId, userFollowId);
        if (existingSave.isPresent()) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_USER));
        }

        var newSave = new Follow(userId, userFollowId);

        followRepo.save(newSave);
        return UserFollowSdo.of(true);
    }

    @Override
    public UserUnfollowSdo unfollow(UserUnfollowSdi req) {
        var userId = commonService.getIdLogin();
        var userFollowId = req.getUserFollowId();

        var existingUnSave = followRepo.findByUserIdAndUserFollowId(userId, userFollowId);
        if (existingUnSave.isPresent()) {
            followRepo.deleteFollow(userId, userFollowId);
            return UserUnfollowSdo.of(true);
        }
        throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_USER));
    }

    private void checkUser(Long userId) {
        if (!commonService.getIdLogin().equals(userId)) {
            throw new AppException(ERROR_AUTH_YOU_ARE_NOT_ACCESS_TO_THIS_CLIENT, LABEL_USER_INFO);
        }
    }

    private UserInfo getUserInfo(Long id) {
        return userInfoRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_USER_INFO_ID, id)));
    }

    private UserInfo getUserInfoByUserID(Long userId) {
        return userInfoRepo.findByUserId(userId)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_USER_INFO_USER_ID, userId)));
    }
}
