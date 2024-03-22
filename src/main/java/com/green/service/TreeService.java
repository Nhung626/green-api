package com.green.service;

import com.green.dto.tree.sdi.*;
import com.green.dto.tree.sdo.*;

import java.io.IOException;
import java.util.List;

public interface TreeService {
    TreeCreateSdo create(TreeCreateSdi req) throws IOException;

    TreeUpdateSdo update(TreeUpdateSdi req) throws IOException;

    List<TreeSearchSdo> search(TreeSearchSdi req);

    TreeDeleteSdo delete(TreeDeleteSdi req);

    TreeSelfSdo self(TreeSelfSdi req);

//    TreeUpdateImgSdo uploadImg(TreeUpdateImgSdi req) throws IOException;
}
