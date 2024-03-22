package com.green.repository;

import com.green.dto.diary.sdi.DiarySearchSdi;
import com.green.dto.diary.sdo.DiarySearchSdo;

import java.util.List;

public interface DiaryRepoCustom {
    List<DiarySearchSdo> search(DiarySearchSdi req);
}
