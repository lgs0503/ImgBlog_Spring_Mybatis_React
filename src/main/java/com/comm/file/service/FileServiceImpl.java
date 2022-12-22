package com.comm.file.service;

import com.comm.file.mapper.FileMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Map<String, Object> getFile(Map<String, Object> file) {
        SqlSession session = sqlSessionFactory.openSession();
        FileMapper mapper = session.getMapper(FileMapper.class);

        return mapper.getFile(file);
    }

    @Override
    public void insertFile(Map<String, Object> file) {
        SqlSession session = sqlSessionFactory.openSession();
        FileMapper mapper = session.getMapper(FileMapper.class);

        mapper.insertFile(file);
    }
}
