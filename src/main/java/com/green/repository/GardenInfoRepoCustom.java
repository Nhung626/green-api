package com.green.repository;

import com.green.dto.common.pagination.PageInfo;
import com.green.dto.gardeninfo.sdi.GardenInfoSearchSdi;
import com.green.dto.gardeninfo.sdo.GardenInfoSearchSdo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface GardenInfoRepoCustom {
    Page<GardenInfoSearchSdo> search(GardenInfoSearchSdi request, PageInfo pageInfo);
}
