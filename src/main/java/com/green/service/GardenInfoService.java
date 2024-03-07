package com.green.service;

import com.green.dto.gardeninfo.sdi.*;
import com.green.dto.gardeninfo.sdo.*;

public interface GardenInfoService {
    GardenInfoCreateSdo create(GardenInfoCreateSdi req);
    GardenInfoUpdateSdo update(GardenInfoUpdateSdi req);
    GardenInfoSearchSdo search(GardenInfoSearchSdi req);
    GardenInfoDeleteSdo delete(GardenInfoDeleteSdi req);
    GardenInfoSelfSdo self(GardenInfoSelfSdi req);
    GardenInfoUpdateCoverSdo uploadCover(GardenInfoUpdateCoverSdi req);
}
