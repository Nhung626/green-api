package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.diary.sdi.*;
import com.green.dto.diary.sdo.*;
import com.green.exception.AppException;
import com.green.model.Diary;
import com.green.repository.DiaryRepo;
import com.green.service.DiaryService;
import com.green.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.green.constants.LabelKey.*;
import static com.green.utils.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepo diaryRepo;
    private final MediaService mediaService;

    @Override
    public DiaryCreateSdo create(DiaryCreateSdi req) throws IOException {
        var img = req.getImages();

        Diary diary = copyProperties(req, Diary.class);
        diary.setMedias((img.stream().map(data-> {
            try {
                return mediaService.getImg(mediaService.uploadFile(data).getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())));

        diaryRepo.save(diary);
        return DiaryCreateSdo.of(diary.getId());
    }

    @Override
    public DiaryUpdateSdo update(DiaryUpdateSdi req) throws IOException {
        Diary diary = getDiary(req.getId());
        var img = req.getImages();

        diary.setMedias((img.stream().map(data-> {
            try {
                return mediaService.getImg(mediaService.uploadFile(data).getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())));

        diaryRepo.save(diary);
        return DiaryUpdateSdo.of(diary.getId());
    }

    @Override
    public List<DiarySearchSdo> search(DiarySearchSdi req) {
        return diaryRepo.search(req);
    }

    @Override
    public DiaryDeleteSdo delete(DiaryDeleteSdi req) {
        var diary = getDiary(req.getId());

        diary.setStatus(Const.GeneralStatus.DELETED);
        diaryRepo.save(diary);
        return DiaryDeleteSdo.of(true);
    }

    @Override
    public DiarySelfSdo self(DiarySelfSdi req) {
        return copyProperties(getDiary(req.getId()), DiarySelfSdo.class);
    }

    private Diary getDiary(Long id) {
        return diaryRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_DIARY_ID, id)));
    }

}
