package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.tree.sdi.*;
import com.green.dto.tree.sdo.*;
import com.green.exception.AppException;
import com.green.model.Tree;
import com.green.repository.TreeRepo;
import com.green.service.MediaService;
import com.green.service.TreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.green.constants.LabelKey.*;
import static com.green.utils.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {
    private final TreeRepo treeRepo;
    private final MediaService mediaService;

    @Override
    public TreeCreateSdo create(TreeCreateSdi req) throws IOException {
        var img = req.getImg();

        if (img.isEmpty()) {
            throw new AppException(ERROR_FILE_OR_URL_REQUIRED, List.of(LABEL_USER_INFO_AVATA));
        }

        var newTree = copyProperties(req, Tree.class);
        var imgDto = mediaService.uploadFile(img);
        newTree.setImgId(imgDto.getId());

        treeRepo.save(newTree);
        return TreeCreateSdo.of(newTree.getId());
    }

    @Override
    public TreeUpdateSdo update(TreeUpdateSdi req) throws IOException {
        Tree tree = getTree(req.getId());

        var img = req.getImg();
        if (img.isEmpty()) {
            throw new AppException(ERROR_FILE_OR_URL_REQUIRED, List.of(LABEL_USER_INFO_AVATA));
        }

        BeanUtils.copyProperties(req, tree);
        var imgDto = mediaService.uploadFile(img);
        tree.setImgId(imgDto.getId());

        treeRepo.save(tree);
        return TreeUpdateSdo.of(tree.getId());
    }

    @Override
    public List<TreeSearchSdo> search(TreeSearchSdi req) {
        return treeRepo.search(req);
    }

    @Override
    public TreeDeleteSdo delete(TreeDeleteSdi req) {
        var tree = getTree(req.getId());

        tree.setStatus(Const.GeneralStatus.DELETED);
        treeRepo.save(tree);
        return TreeDeleteSdo.of(true);
    }

    @Override
    public TreeSelfSdo self(TreeSelfSdi req) {
        var tree = getTree(req.getId());
        return copyProperties(tree, TreeSelfSdo.class);
    }

    private Tree getTree(Long id) {
        return treeRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_GARDEN_INFO_ID, id)));
    }
}
