package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.land.sdi.*;
import com.green.dto.land.sdo.*;
import com.green.exception.AppException;
import com.green.model.GardenInfo;
import com.green.model.Land;
import com.green.repository.GardenInfoRepo;
import com.green.repository.LandRepo;
import com.green.service.LandService;
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
public class LandServiceImpl implements LandService {
    private final LandRepo landRepo;
    private final GardenInfoRepo gardenInfoRepo;
    private final MediaService mediaService;
    private final CommonService commonService;

    @Override
    public LandCreateSdo create(LandCreateSdi req) throws IOException {
        var img = req.getImg();
        checkUser(req.getUserId());
        GardenInfo gardenInfo = gardenInfoRepo.findByUserId(req.getUserId())
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, LABEL_GARDEN_INFO));

        if(landRepo.findByName(req.getName()).isPresent()){
            throw new AppException(ERROR_ALREADY_EXIST, LABEL_LAND_NAME);
        }
        var newLand = copyProperties(req, Land.class);
        newLand.setGardenId(gardenInfo.getId());

        if (!img.isEmpty()) {
            var imgDto = mediaService.uploadFile(img);
            newLand.setImgId(imgDto.getId());
        }

        landRepo.save(newLand);
        return LandCreateSdo.of(newLand.getId());
    }

    @Override
    public LandUpdateSdo update(LandUpdateSdi req) throws IOException {
        Land land = getLand(req.getId());
        checkUser(req.getUserId());
        GardenInfo gardenInfo = gardenInfoRepo.findByUserId(req.getUserId())
                .orElseThrow(() -> new AppException(ERROR_ALREADY_EXIST, LABEL_USER_INFO));


        BeanUtils.copyProperties(req, land);

        var img = req.getImage();
        if (!img.isEmpty()) {
            var imgDto = mediaService.uploadFile(img);
            land.setImgId(imgDto.getId());
        }

        landRepo.save(land);
        return LandUpdateSdo.of(land.getId());
    }


    @Override
    public List<LandSearchSdo> search(LandSearchSdi req) {
        return landRepo.search(req);
    }

    @Override
    public LandDeleteSdo delete(LandDeleteSdi req) {
        var land = getLand(req.getId());
        checkUser(land.getUserId());

        land.setStatus(Const.GeneralStatus.DELETED);
        landRepo.save(land);
        return LandDeleteSdo.of(true);
    }

    @Override
    public LandSelfSdo self(LandSelfSdi req) {
        var land = getLand(req.getId());
        return copyProperties(land, LandSelfSdo.class);
    }

    @Override
    public LandUpdateImgSdo uploadImg(LandUpdateImgSdi req) throws IOException {
        var land = getLand(req.getId());
        checkUser(land.getUserId());

        var img = req.getImage();
        if (img.isEmpty()) {
            throw new AppException(ERROR_FILE_OR_URL_REQUIRED, List.of(LABEL_FILE));
        }
        var imgDto = mediaService.uploadFile(img);
        land.setImgId(imgDto.getId());

        landRepo.save(land);
        return new LandUpdateImgSdo(land.getId(), land.getImgId());
    }

    private void checkUser(Long userId) {
        if (!commonService.getIdLogin().equals(userId)) {
            throw new AppException(ERROR_AUTH_YOU_ARE_NOT_ACCESS_TO_THIS_CLIENT, LABEL_USER_INFO);
        }
    }

    private Land getLand(Long id) {
        return landRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_LAND_ID, id)));
    }
}
