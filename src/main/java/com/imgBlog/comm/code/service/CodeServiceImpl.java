package com.imgBlog.comm.code.service;


import com.imgBlog.comm.code.mapper.CodeMapper;
import com.imgBlog.comm.code.vo.CodeVO;
import com.imgBlog.user.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Map<String, Object>> getCodeList(CodeVO code) throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        CodeMapper mapper = session.getMapper(CodeMapper.class);

        return mapper.getCodeList(code);
    }

    @Override
    public String getCodeListCount(CodeVO code) throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        CodeMapper mapper = session.getMapper(CodeMapper.class);

        return mapper.getCodeListCount(code);
    }

    @Override
    public Map<String, Object> getCode(CodeVO code) throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        CodeMapper mapper = session.getMapper(CodeMapper.class);

        return mapper.getCode(code);
    }

    @Override
    public void insertCode(CodeVO code) throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        CodeMapper mapper = session.getMapper(CodeMapper.class);

        mapper.insertCode(code);
    }

    @Override
    public void updateCode(CodeVO code) throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        CodeMapper mapper = session.getMapper(CodeMapper.class);

        mapper.updateCode(code);
    }

    @Override
    public void deleteCode(CodeVO code) throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        CodeMapper mapper = session.getMapper(CodeMapper.class);

        mapper.deleteCode(code);
    }
}
