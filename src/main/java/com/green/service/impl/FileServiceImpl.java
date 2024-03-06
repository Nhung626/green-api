package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.file.sdi.FileDeleteSdi;
import com.green.dto.file.sdi.FileSearchSdi;
import com.green.dto.file.sdi.FileSelfSdi;
import com.green.dto.file.sdo.FileDeleteSdo;
import com.green.dto.file.sdo.FileSearchSdo;
import com.green.dto.file.sdo.FileSelfSdo;
import com.green.dto.file.sdo.FileUploadSdo;
import com.green.exception.AppException;
import com.green.model.File;
import com.green.repository.FileRepo;
import com.green.service.FileService;
import com.green.service.common.MinIoService;
import com.green.utils.DataUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.green.constants.LabelKey.ERROR_NOT_EXIST;
import static com.green.constants.LabelKey.LABEL_FILE;
import static com.green.utils.DataUtil.*;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepo fileRepo;
    
    private final MinIoService minIoService;

    @Override
    public FileUploadSdo uploadFile(MultipartFile req) {
        return uploadFile(req, false);
    }

    @Override
    @SneakyThrows
    public FileUploadSdo uploadFile(MultipartFile req, Boolean isTemp) {
        InputStream inputStream = req.getInputStream();
        String fileName = safeToString(req.getOriginalFilename());
        var fileExtension = getExtension(fileName);
        String uniqueFileName = generateUniqueFileName(removeExtension(fileName));
        String objectName = String.format("%s.%s", uniqueFileName, fileExtension);

        String path = minIoService.upload(inputStream, objectName, req.getContentType());

        File file = new File();
        file.setKey(UUID.randomUUID().toString());
        file.setName(objectName);
        file.setSize(req.getSize());
        file.setExtension(fileExtension);
        file.setPath(path);
        file.setIsTemp(safeToBoolean(isTemp, Boolean.TRUE));
        fileRepo.save(file);

        inputStream.close();

        return FileUploadSdo.of(file.getId());
    }

    @Override
    public List<FileSearchSdo> search(FileSearchSdi req) {
        return fileRepo
                .findAllByName(req.getName())
                .stream()
                .map(data -> DataUtil.copyProperties(data, FileSearchSdo.class))
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public FileDeleteSdo delete(FileDeleteSdi req) {
        var file = getFile(req.getId());

        minIoService.delete(file.getName());

        file.setStatus(Const.GeneralStatus.DELETED);
        fileRepo.save(file);

        return FileDeleteSdo.of(Boolean.TRUE);
    }

    @Override
    public void deleteById(List<Long> fileIds) {
        var files = fileRepo.findByIdIn(fileIds);
        if (!files.isEmpty())
            delete(files);
    }

    @Override
    public void delete(List<File> files) {
        var objectNameList = files.stream().map(File::getName).collect(Collectors.toList());

        minIoService.delete(objectNameList);

        files.parallelStream().forEach(file -> file.setStatus(Const.GeneralStatus.DELETED));
        fileRepo.saveAll(files);
    }

    @Override
    public FileSelfSdo self(FileSelfSdi req) {
        var file = getFile(req.getId());
        return copyProperties(file, FileSelfSdo.class);
    }

    @Override
    public Map<Long, File> getByIdIn(List<Long> req) {
        Map<Long, File> fileMap = new HashMap<>();
        fileRepo.findByIdIn(req).forEach(file -> {
            fileMap.put(file.getId(), file);
        });

        return fileMap;
    }

    @Override
    public FileUploadSdo useFile(Long id) {
        var file = getFile(id);
        file.setIsTemp(Boolean.FALSE);
        fileRepo.save(file);
        return FileUploadSdo.of(id);
    }

    private File getFile(Long id) {
        return fileRepo
                .findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_FILE, id)));
    }
}
