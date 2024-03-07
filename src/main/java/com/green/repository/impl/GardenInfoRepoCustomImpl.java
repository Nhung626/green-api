package com.green.repository.impl;

import com.green.repository.GardenInfoRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GardenInfoRepoCustomImpl implements GardenInfoRepoCustom {
    private final QueryRepo queryRepo;
}
