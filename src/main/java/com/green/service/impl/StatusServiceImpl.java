package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.status.sdi.*;
import com.green.dto.status.sdo.*;
import com.green.exception.AppException;
import com.green.model.AbstractAudit;
import com.green.model.Like;
import com.green.model.Status;
import com.green.repository.LikeRepo;
import com.green.repository.StatusRepo;
import com.green.service.MediaService;
import com.green.service.StatusService;
import com.green.service.common.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.green.constants.LabelKey.*;
import static com.green.utils.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepo statusRepo;
    private final MediaService mediaService;
    private final LikeRepo likeRepo;
    private final CommonService commonService;

    @Override
    public StatusCreateSdo create(StatusCreateSdi req) throws IOException {
        Long userId = req.getUserId();
        var img = req.getImg();

        checkUser(userId);
        Status status = copyProperties(req, Status.class);
        if (img!= null) {
            status.setMedias((img.stream().map(data -> {
                try {
                    return mediaService.getImg(mediaService.uploadFile(data).getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList())));
        }
        statusRepo.save(status);

        return StatusCreateSdo.of(status.getId());
    }

    @Override
    public StatusUpdateSdo update(StatusUpdateSdi req) throws IOException {
        Long userId = req.getUserId();
        checkUser(userId);

        Status status = getStatus(req.getId());
        status.setContent(req.getContent());

        var img = req.getImg();
        if (!img.isEmpty()) {
            status.setMedias((img.stream().map(data -> {
                try {
                    return mediaService.getImg(mediaService.uploadFile(data).getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList())));
        }
        statusRepo.save(status);

        return StatusUpdateSdo.of(status.getId());
    }

    @Override
    public List<StatusSearchSdo> search(StatusSearchSdi req) {
        var myId = commonService.getIdLogin();
        List<StatusSearchSdo> res = statusRepo.search(req, myId);
        res.forEach((data) -> {
            Status status = statusRepo.findById(data.getId()).get();
            List<Long> imgIds = status.getMedias().stream().map(AbstractAudit::getId).toList();
            data.setImgIds(imgIds);
        });
        return res;
    }

    @Override
    public StatusLikeSdo like(StatusLikeSdi req) {
        var userId = commonService.getIdLogin();

        Optional<Like> existingLike = likeRepo.findByUserIdAndStatusId(userId, req.getStatusId());
        if (existingLike.isPresent()) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT_LIKE));
        }
        Like newLike = new Like(userId, req.getStatusId());
        likeRepo.save(newLike);
        return StatusLikeSdo.of(true);
    }

    @Override
    public StatusUnlikeSdo unlike(StatusUnlikeSdi req) {
        var userId = commonService.getIdLogin();
        Optional<Like> existingUnLike = likeRepo.findByUserIdAndStatusId(userId, req.getStatusId());
        if (existingUnLike.isPresent()) {
            likeRepo.deleteLike(userId, req.getStatusId());
            return StatusUnlikeSdo.of(true);
        }
        throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT_UNLIKE));
    }

    @Override
    public StatusDeleteSdo delete(StatusDeleteSdi req) {
        var status = getStatus(req.getId());
        checkUser(status.getUserId());

        status.setStatus(Const.GeneralStatus.DELETED);
        statusRepo.save(status);
        return StatusDeleteSdo.of(true);
    }

    @Override
    public StatusSelfSdo self(StatusSelfSdi req) {
        var userId = commonService.getIdLogin();
        var res = statusRepo.self(req, userId);
        var status = statusRepo.findById(res.getId()).get();
        List<Long> imgIds = status.getMedias().stream().map(AbstractAudit::getId).toList();
        res.setImgIds(imgIds);
        return res;
    }

    private void checkUser(Long userId) {
        if (!commonService.getIdLogin().equals(userId)) {
            throw new AppException(ERROR_AUTH_YOU_ARE_NOT_ACCESS_TO_THIS_CLIENT, LABEL_USER_INFO);
        }
    }

    private Status getStatus(Long id) {
        return statusRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_STATUS_ID, id)));
    }
}
