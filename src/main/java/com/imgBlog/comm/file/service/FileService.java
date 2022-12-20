package com.imgBlog.comm.file.service;

import java.util.Map;

public interface FileService {

    Map<String, Object> getFile(Map<String, Object> file);

    void insertFile(Map<String, Object> file);
}
