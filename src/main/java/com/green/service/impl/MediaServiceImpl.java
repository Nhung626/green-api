package com.green.service.impl;

import com.green.dto.media.sdi.MediaDeleteSdi;
import com.green.dto.media.sdi.MediaSelfSdi;
import com.green.dto.media.sdo.MediaDeleteSdo;
import com.green.dto.media.sdo.MediaSelfSdo;
import com.green.dto.media.sdo.MediaUploadSdo;
import com.green.exception.AppException;
import com.green.model.Media;
import com.green.repository.MediaRepo;
import com.green.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import static com.green.constants.LabelKey.*;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepo mediaRepo;

    @Value("${media.img_path}")
    private String uploadedFolder;
    @Value("${media.img_path}")
    private String imgPath;

//    private final MinIoService minIoService;

    private Media getFile(Long id) {
        return mediaRepo
                .findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_FILE, id)));
    }

    @Override
    public MediaUploadSdo uploadFile(MultipartFile req) throws IOException {
        File dir = new File(uploadedFolder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Random rand = new Random();
        int ranNum = rand.nextInt();
        if (!req.isEmpty()) {
            byte[] bytes = req.getBytes();
            Path path = Paths.get(dir + java.io.File.separator + req.getName() + ranNum + getFileExtension(req.getOriginalFilename()));
            Files.write(path, bytes);
            Media media = new Media();
            media.setName(path.getFileName().toString());
            media.setType(req.getContentType());
            media = mediaRepo.save(media);
            return MediaUploadSdo.of(media.getId());
        }
        return null;
    }

    @Override
    public MediaDeleteSdo delete(MediaDeleteSdi req) {
        return null;
    }

    @Override
    public MediaSelfSdo self(MediaSelfSdi req) {
        Media media = mediaRepo.findById(req.getId()).orElse(null);
        String path = "";
        if (media != null) {
            path = imgPath + File.separator + media.getName();
            return new MediaSelfSdo(media.getId(), media.getName(), media.getType(), path);
        }
        return null;
    }

    public String getFileExtension(String fileName) {
        return "." + FilenameUtils.getExtension(fileName);
    }

    public Media getImg(Long id) {
        return mediaRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_FILE_ID, id)));
    }
}
