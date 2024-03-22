//package com.green.service.common.impl;
//
//import com.green.service.common.MinIoService;
//import io.minio.MinioClient;
//import io.minio.PutObjectArgs;
//import io.minio.RemoveObjectArgs;
//import io.minio.RemoveObjectsArgs;
//import io.minio.messages.DeleteObject;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.io.InputStream;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class MinIoServiceImpl implements MinIoService {
//    private final MinioClient minioClient;
//
//    @Value("${minio.bucket.name}")
//    private String bucketName;
//
//    @Value("${minio.endpoint}")
//    private String minioEndpoint;
//
//
//    @Override
//    @SneakyThrows
//    public String upload(InputStream data, String objectName, String contentType) {
//        minioClient.putObject(PutObjectArgs.builder()
//                .bucket(bucketName)
//                .object(objectName)
//                .stream(data, data.available(), -1)
//                .contentType(contentType)
//                .build());
//
//        return String.format("%s/%s/%s", minioEndpoint, bucketName, objectName);
//    }
//
//    @Override
//    @SneakyThrows
//    @Async
//    public void delete(String objectName) {
//        minioClient.removeObject(
//                RemoveObjectArgs.builder()
//                        .bucket(bucketName)
//                        .object(objectName).build()
//        );
//    }
//
//    @Override
//    public void delete(List<String> objectNames) {
//        minioClient.removeObjects(
//                RemoveObjectsArgs.builder()
//                        .bucket(bucketName)
//                        .objects(
//                                objectNames.stream().map(DeleteObject::new).collect(Collectors.toList())
//                        ).build());
//    }
//}
