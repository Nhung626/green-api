package com.green.repository;

import com.green.dto.gardeninfo.sdi.GardenInfoSearchSdi;
import com.green.dto.land.sdi.LandSearchSdi;
import com.green.dto.land.sdo.LandSearchSdo;

import java.util.List;

public interface LandRepoCustom {
    List<LandSearchSdo> search(LandSearchSdi request);

}
