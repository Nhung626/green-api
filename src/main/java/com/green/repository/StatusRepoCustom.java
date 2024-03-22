package com.green.repository;

import com.green.dto.status.sdi.StatusSearchSdi;
import com.green.dto.status.sdi.StatusSelfSdi;
import com.green.dto.status.sdo.StatusSearchSdo;
import com.green.dto.status.sdo.StatusSelfSdo;

import java.util.List;
public interface StatusRepoCustom {
    List<StatusSearchSdo> search (StatusSearchSdi req);

    StatusSelfSdo self(StatusSelfSdi req, Long userId);
}
