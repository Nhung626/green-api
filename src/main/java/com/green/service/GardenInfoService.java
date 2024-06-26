package com.green.service;

import com.green.dto.gardeninfo.sdi.*;
import com.green.dto.gardeninfo.sdo.*;

import java.io.IOException;
import java.util.List;

public interface GardenInfoService {
    GardenInfoCreateSdo create(GardenInfoCreateSdi req) throws IOException;

    GardenInfoUpdateSdo update(GardenInfoUpdateSdi req) throws IOException;

    List<GardenInfoSearchSdo> search(GardenInfoSearchSdi req);
    List<GardenInfoSearchSdo> getFollow();

    GardenInfoDeleteSdo delete(GardenInfoDeleteSdi req);

    GardenInfoSelfSdo self(GardenInfoSelfSdi req);

    GardenInfoUpdateCoverSdo uploadCover(GardenInfoUpdateCoverSdi req) throws IOException;
}
