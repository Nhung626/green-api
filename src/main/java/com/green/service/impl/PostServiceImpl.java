package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.post.sdi.*;
import com.green.dto.post.sdo.*;
import com.green.exception.AppException;
import com.green.model.Like;
import com.green.model.LikePost;
import com.green.model.Post;
import com.green.model.SavePost;
import com.green.repository.LikePostRepo;
import com.green.repository.PostRepo;
import com.green.repository.SavePostRepo;
import com.green.service.MediaService;
import com.green.service.PostService;
import com.green.service.common.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.green.constants.LabelKey.*;
import static com.green.utils.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final CommonService commonService;
    private final MediaService mediaService;
    private final LikePostRepo likePostRepo;
    private final SavePostRepo savePostRepo;

    @Override
    public PostCreateSdo create(PostCreateSdi req) throws IOException {
        var img = req.getImages();

        Post post = copyProperties(req, Post.class);
        post.setMedias((img.stream().map(data -> {
            try {
                return mediaService.getImg(mediaService.uploadFile(data).getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())));

        postRepo.save(post);
        return PostCreateSdo.of(post.getId());
    }

    @Override
    public PostUpdateSdo update(PostUpdateSdi req) throws IOException {
        var img = req.getImages();

        Post post = getPost(req.getId());
        post.setMedias((img.stream().map(data -> {
            try {
                return mediaService.getImg(mediaService.uploadFile(data).getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())));

        post.setAuth(req.getAuth());
        post.setTitle(req.getTitle());
        post.setTypeTree(req.getTypeTree());
        post.setGeneral(req.getGeneral());
        post.setDescription(req.getDescription());
        post.setTakeCare(req.getTakeCare());

        postRepo.save(post);

        return PostUpdateSdo.of(post.getId());
    }

    @Override
    public List<PostSearchSdo> search(PostSearchSdi req) {
        return null;
    }

    @Override
    public PostDeleteSdo delete(PostDeleteSdi req) {
        var post = getPost(req.getId());

        post.setStatus(Const.GeneralStatus.DELETED);
        postRepo.save(post);
        return PostDeleteSdo.of(true);
    }

    public PostSelfSdo self(PostSelfSdi req) {
        Long userId = commonService.getIdLogin();
        return postRepo.self(req, userId);
    }

    public PostLikeSdo like(PostLikeSdi req) {
        Long userId = commonService.getIdLogin();
        Long postId = req.getPostId();

        Optional<Like> existingLike = likePostRepo.findByUserIdAndPostId(userId, postId);
        if (existingLike.isPresent()) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_POST_LIKE));
        }

        var newLike = new LikePost();
        newLike.setUserId(userId);
        newLike.setUserId(postId);

        likePostRepo.save(newLike);
        return PostLikeSdo.of(true);
    }

    @Override
    public PostUnlikeSdo unlike(PostUnlikeSdi req) {
        Long userId = commonService.getIdLogin();
        Long postId = req.getPostId();

        Optional<Like> existingUnLike = likePostRepo.findByUserIdAndPostId(userId, postId);
        if (existingUnLike.isPresent()) {
            likePostRepo.deleteLike(userId, postId);
            return PostUnlikeSdo.of(true);
        }
        throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_POST_UNLIKE));
    }

    @Override
    public PostSaveSdo save(PostSaveSdi req) {
        Long userId = commonService.getIdLogin();
        Long postId = req.getPostId();

        Optional<SavePost> existingSave = savePostRepo.findByUserIdAndPostId(userId, postId);
        if (existingSave.isPresent()) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_POST_SAVE));
        }

        var newSave = new SavePost(userId, postId);

        savePostRepo.save(newSave);
        return PostSaveSdo.of(true);
    }

    @Override
    public PostUnSaveSdo unSave(PostUnsaveSdi req) {
        Long userId = commonService.getIdLogin();
        Long postId = req.getPostId();

        Optional<SavePost> existingUnSave = savePostRepo.findByUserIdAndPostId(userId, postId);
        if (existingUnSave.isPresent()) {
            savePostRepo.deleteSave(userId, postId);
            return PostUnSaveSdo.of(true);
        }
        throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_POST_UNSAVE));
    }

    @Override
    public List<PostSelfSdo> getAllSave() {
        Long userId = commonService.getIdLogin();
        List<SavePost> savePosts = savePostRepo.findByUserId(userId);
        List<PostSelfSdo> res = savePosts.stream().map((data) -> {
            return postRepo.self(new PostSelfSdi(data.getPostId()), userId);
        }).toList();

        return res;
    }

    public Post getPost(Long id) {
        return postRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_POST_ID, id)));
    }
}
