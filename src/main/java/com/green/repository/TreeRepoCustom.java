package com.green.repository;

import com.green.dto.tree.sdi.TreeSearchSdi;
import com.green.dto.tree.sdo.TreeSearchSdo;

import java.util.List;

public interface TreeRepoCustom {
    List<TreeSearchSdo> search(TreeSearchSdi req);
}
