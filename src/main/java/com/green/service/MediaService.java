package com.green.service;

import com.green.dto.media.sdi.MediaDeleteSdi;
import com.green.dto.media.sdi.MediaSelfSdi;
import com.green.dto.media.sdo.MediaDeleteSdo;
import com.green.dto.media.sdo.MediaSelfSdo;
import com.green.dto.media.sdo.MediaUploadSdo;
import com.green.model.Media;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaService {

    MediaUploadSdo uploadFile(MultipartFile req) throws IOException;

    MediaDeleteSdo delete(MediaDeleteSdi req);

    MediaSelfSdo self(MediaSelfSdi req);
    Media getImg(Long id);
}
