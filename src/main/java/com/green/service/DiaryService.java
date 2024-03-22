package com.green.service;

import com.green.dto.diary.sdi.*;
import com.green.dto.diary.sdo.*;

import java.io.IOException;
import java.util.List;

public interface DiaryService {
    DiaryCreateSdo create(DiaryCreateSdi req) throws IOException;

    DiaryUpdateSdo update(DiaryUpdateSdi req) throws IOException;

    List<DiarySearchSdo> search(DiarySearchSdi req);

    DiaryDeleteSdo delete(DiaryDeleteSdi req);

    DiarySelfSdo self(DiarySelfSdi req);

}
