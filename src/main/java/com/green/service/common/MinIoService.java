package com.green.service.common;

import java.io.InputStream;
import java.util.List;

public interface MinIoService {

    String upload(InputStream data, String objectName, String contentType);

    void delete(String objectName);

    void delete(List<String> objectNames);
}
