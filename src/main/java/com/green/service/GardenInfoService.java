package com.green.service;

import com.green.dto.common.pagination.PageInfo;
import com.green.dto.gardeninfo.sdi.*;
import com.green.dto.gardeninfo.sdo.*;
import org.springframework.data.domain.Page;

public interface GardenInfoService {
    GardenInfoCreateSdo create(GardenInfoCreateSdi req);

    GardenInfoUpdateSdo update(GardenInfoUpdateSdi req);

    Page<GardenInfoSearchSdo> search(GardenInfoSearchSdi req, PageInfo pageInfo);

    GardenInfoDeleteSdo delete(GardenInfoDeleteSdi req);

    GardenInfoSelfSdo self(GardenInfoSelfSdi req);

    GardenInfoUpdateCoverSdo uploadCover(GardenInfoUpdateCoverSdi req);
}
