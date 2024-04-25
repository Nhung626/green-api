package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.gardeninfo.sdi.*;
import com.green.dto.gardeninfo.sdo.*;
import com.green.dto.userinfo.sdi.UserInfoSearchSdi;
import com.green.dto.userinfo.sdo.UserInfoSearchSdo;
import com.green.exception.AppException;
import com.green.model.Follow;
import com.green.model.GardenInfo;
import com.green.repository.FollowRepo;
import com.green.repository.GardenInfoRepo;
import com.green.repository.UserInfoRepo;
import com.green.service.GardenInfoService;
import com.green.service.MediaService;
import com.green.service.common.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.green.constants.LabelKey.*;
import static com.green.utils.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class GardenInfoServiceImpl implements GardenInfoService {
    private final GardenInfoRepo gardenInfoRepo;
    private final UserInfoRepo userInfoRepo;
    private final MediaService mediaService;
    private final CommonService commonService;
    private final FollowRepo followRepo;

    @Override
    public GardenInfoCreateSdo create(GardenInfoCreateSdi req) throws IOException {
        var cover = req.getCover();
        Long userId = commonService.getIdLogin();

        var gardenInfoOptional = gardenInfoRepo.findByUserId(userId);
        if (gardenInfoOptional.isPresent())
            throw new AppException(ERROR_ALREADY_EXIST, LABEL_GARDEN_INFO);

        var newGardenInfo = copyProperties(req, GardenInfo.class);
        newGardenInfo.setUserId(userId);
        if (!cover.isEmpty()) {
            var coverDto = mediaService.uploadFile(cover);
            newGardenInfo.setCoverId(coverDto.getId());
        }

        gardenInfoRepo.save(newGardenInfo);
        return GardenInfoCreateSdo.of(newGardenInfo.getId());
    }

    @Override
    public GardenInfoUpdateSdo update(GardenInfoUpdateSdi req) throws IOException {
        var gardenInfo = getGardenInfo(req.getId());
        checkUser(req.getUserId());

        BeanUtils.copyProperties(req, gardenInfo);

        var img = req.getCover();
        if (!img.isEmpty()) {
            var cover = mediaService.uploadFile(img);
            gardenInfo.setCoverId(cover.getId());
        }

        gardenInfoRepo.save(gardenInfo);
        return GardenInfoUpdateSdo.of(gardenInfo.getId());
    }

    @Override
    public List<GardenInfoSearchSdo> search(GardenInfoSearchSdi req) {
        var res = gardenInfoRepo.search(req);
        Long userId = commonService.getIdLogin();
        res.forEach((data) -> {
            var sdo = userInfoRepo.search(new UserInfoSearchSdi(req.getUserId()), userId);
            data.setUserInfo(copyProperties(sdo,UserInfoSearchSdo.class));
        });
        return res;
    }

    @Override
    public List<GardenInfoSearchSdo> getFollow() {
        Long userId = commonService.getIdLogin();
        List<Follow> followList = followRepo.findByUserId(userId);
        List<GardenInfoSearchSdo> res = followList.stream().map((data)->{
            var user = gardenInfoRepo.search(new GardenInfoSearchSdi(data.getUserFollowId())).get(0);
            var sdo = userInfoRepo.search(new UserInfoSearchSdi(data.getUserFollowId()), userId);
            user.setUserInfo(copyProperties(sdo,UserInfoSearchSdo.class));
            return  user;
        }).toList();
        return res;
    }

    @Override
    public GardenInfoDeleteSdo delete(GardenInfoDeleteSdi req) {
        var gardenInfo = getGardenInfo(req.getId());
        checkUser(gardenInfo.getUserId());

        gardenInfo.setStatus(Const.GeneralStatus.DELETED);
        gardenInfoRepo.save(gardenInfo);
        return GardenInfoDeleteSdo.of(true);
    }

    @Override
    public GardenInfoSelfSdo self(GardenInfoSelfSdi req) {
        var gardenInfo = getGardenInfo(req.getId());
        GardenInfoSelfSdo rs = copyProperties(gardenInfo, GardenInfoSelfSdo.class);
        return rs;
    }

    @Override
    public GardenInfoUpdateCoverSdo uploadCover(GardenInfoUpdateCoverSdi req) throws IOException {
        var cover = req.getCover();
        checkUser(req.getId());

        var gardenInfo = getGardenInfo(req.getId());
        var coverDto = mediaService.uploadFile(cover);

        gardenInfo.setCoverId(coverDto.getId());
        return GardenInfoUpdateCoverSdo.of(gardenInfo.getId());
    }

    private void checkUser(Long userId) {
        if (!commonService.getIdLogin().equals(userId)) {
            throw new AppException(ERROR_AUTH_YOU_ARE_NOT_ACCESS_TO_THIS_CLIENT, LABEL_USER_INFO);
        }
    }

    private GardenInfo getGardenInfo(Long id) {
        return gardenInfoRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_GARDEN_INFO_ID, id)));
    }
}
