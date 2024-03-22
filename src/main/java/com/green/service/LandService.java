package com.green.service;

import com.green.dto.land.sdi.*;
import com.green.dto.land.sdo.*;

import java.io.IOException;
import java.util.List;

public interface LandService {
    LandCreateSdo create(LandCreateSdi req) throws IOException;

    LandUpdateSdo update(LandUpdateSdi req) throws IOException;

    List<LandSearchSdo> search(LandSearchSdi req);

    LandDeleteSdo delete(LandDeleteSdi req);

    LandSelfSdo self(LandSelfSdi req);

    LandUpdateImgSdo uploadImg(LandUpdateImgSdi req) throws IOException;
}
