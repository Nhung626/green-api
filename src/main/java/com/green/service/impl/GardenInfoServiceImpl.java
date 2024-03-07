package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.common.pagination.PageInfo;
import com.green.dto.gardeninfo.sdi.*;
import com.green.dto.gardeninfo.sdo.*;
import com.green.dto.userinfo.sdo.*;
import com.green.exception.AppException;
import com.green.model.GardenInfo;
import com.green.model.UserInfo;
import com.green.repository.GardenInfoRepo;
import com.green.service.FileService;
import com.green.service.GardenInfoService;
import com.green.service.common.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.green.constants.LabelKey.*;
import static com.green.constants.LabelKey.LABEL_USER_INFO_USER_ID;
import static com.green.utils.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class GardenInfoServiceImpl implements GardenInfoService {
    private final GardenInfoRepo gardenInfoRepo;
    private final FileService fileService;
    private final CommonService commonService;

    @Override
    public GardenInfoCreateSdo create(GardenInfoCreateSdi req) {
        var cover = req.getCover();
        checkUser(req.getUserId());

        var gardenInfoOptional = gardenInfoRepo.findByUserId(req.getUserId());
        if (gardenInfoOptional.isPresent())
            throw new AppException(ERROR_ALREADY_EXIST, List.of(LABEL_USER_INFO));

        if (cover.isEmpty()) {
            throw new AppException(ERROR_FILE_OR_URL_REQUIRED, List.of(LABEL_USER_INFO_AVATA));
        }

        var newGardenInfo = copyProperties(req, GardenInfo.class);
        var coverDto = fileService.uploadFile(cover);
        newGardenInfo.setCoverId(coverDto.getId());

        gardenInfoRepo.save(newGardenInfo);
        return GardenInfoCreateSdo.of(newGardenInfo.getId());
    }

    @Override
    public GardenInfoUpdateSdo update(GardenInfoUpdateSdi req) {
        checkUser(req.getUserId());

        var gardenInfo = getGardenInfoByUserID(req.getUserId());
        BeanUtils.copyProperties(req, gardenInfo);
        gardenInfoRepo.save(gardenInfo);
        return GardenInfoUpdateSdo.of(gardenInfo.getId());
    }

    @Override
    public Page<GardenInfoSearchSdo> search(GardenInfoSearchSdi req, PageInfo pageInfo) {
        return gardenInfoRepo.search(req, pageInfo);
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
        return copyProperties(gardenInfo, GardenInfoSelfSdo.class);
    }

    @Override
    public GardenInfoUpdateCoverSdo uploadCover(GardenInfoUpdateCoverSdi req) {
        var cover = req.getCover();
        checkUser(req.getUserId());

        var gardenInfo = getGardenInfoByUserID(req.getUserId());
        var coverDto = fileService.uploadFile(cover);
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
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_USER_INFO_ID, id)));
    }

    private GardenInfo getGardenInfoByUserID(Long userId) {
        return gardenInfoRepo.findByUserId(userId)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_USER_INFO_USER_ID, userId)));
    }
}
