package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.gardeninfo.sdi.*;
import com.green.dto.gardeninfo.sdo.*;
import com.green.exception.AppException;
import com.green.model.GardenInfo;
import com.green.repository.GardenInfoRepo;
import com.green.service.MediaService;
import com.green.service.GardenInfoService;
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
    private final MediaService mediaService;
    private final CommonService commonService;

    @Override
    public GardenInfoCreateSdo create(GardenInfoCreateSdi req) throws IOException {
        var cover = req.getCover();
        checkUser(req.getUserId());

        var gardenInfoOptional = gardenInfoRepo.findByUserId(req.getUserId());
        if (gardenInfoOptional.isPresent())
            throw new AppException(ERROR_ALREADY_EXIST, List.of(LABEL_USER_INFO));

        if (cover.isEmpty()) {
            throw new AppException(ERROR_FILE_OR_URL_REQUIRED, List.of(LABEL_USER_INFO_AVATA));
        }

        var newGardenInfo = copyProperties(req, GardenInfo.class);
        var coverDto = mediaService.uploadFile(cover);
        newGardenInfo.setCoverId(coverDto.getId());

        gardenInfoRepo.save(newGardenInfo);
        return GardenInfoCreateSdo.of(newGardenInfo.getId());
    }

    @Override
    public GardenInfoUpdateSdo update(GardenInfoUpdateSdi req) throws IOException {
        var gardenInfo = getGardenInfo(req.getId());

        checkUser(req.getUserId());

        var img = req.getCover();
        if (img.isEmpty()) {
            throw new AppException(ERROR_FILE_OR_URL_REQUIRED, List.of(LABEL_USER_INFO_AVATA));
        }
        var cover = mediaService.uploadFile(img);

        BeanUtils.copyProperties(req, gardenInfo);
        gardenInfo.setCoverId(cover.getId());

        gardenInfoRepo.save(gardenInfo);
        return GardenInfoUpdateSdo.of(gardenInfo.getId());
    }

    @Override
    public List<GardenInfoSearchSdo> search(GardenInfoSearchSdi req) {
        return gardenInfoRepo.search(req);
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
