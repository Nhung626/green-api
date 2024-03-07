package com.green.service;

import com.green.dto.file.sdi.FileDeleteSdi;
import com.green.dto.file.sdi.FileSearchSdi;
import com.green.dto.file.sdi.FileSelfSdi;
import com.green.dto.file.sdo.FileDeleteSdo;
import com.green.dto.file.sdo.FileSearchSdo;
import com.green.dto.file.sdo.FileSelfSdo;
import com.green.dto.file.sdo.FileUploadSdo;
import com.green.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService {

    FileUploadSdo uploadFile(MultipartFile req);

    FileUploadSdo uploadFile(MultipartFile req, Boolean isTemp);

    List<FileSearchSdo> search(FileSearchSdi req);

    FileDeleteSdo delete(FileDeleteSdi req);

    void deleteById(List<Long> fileIds);

    void delete(List<File> files);

    FileSelfSdo self(FileSelfSdi req);

    Map<Long, File> getByIdIn(List<Long> req);

    FileUploadSdo useFile(Long id);
}
