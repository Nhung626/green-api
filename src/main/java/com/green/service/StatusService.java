package com.green.service;

import com.green.dto.status.sdi.*;
import com.green.dto.status.sdo.*;

import java.io.IOException;
import java.util.List;

public interface StatusService {
    StatusCreateSdo create(StatusCreateSdi req) throws IOException;

    StatusUpdateSdo update(StatusUpdateSdi req) throws IOException;

    List<StatusSearchSdo> search(StatusSearchSdi req);

    StatusDeleteSdo delete(StatusDeleteSdi req);

    StatusSelfSdo self(StatusSelfSdi req, Long userId);

    StatusLikeSdo like(StatusLikeSdi req);

    StatusUnlikeSdo unlike(StatusUnlikeSdi req);

}
