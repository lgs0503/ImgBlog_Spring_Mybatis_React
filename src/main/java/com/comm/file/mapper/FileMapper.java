package com.comm.file.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FileMapper {

    Map<String, Object> getFile(Map<String, Object> file);

    void insertFile(Map<String, Object> file);
}
