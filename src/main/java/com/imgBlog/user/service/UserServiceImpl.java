package com.imgBlog.user.service;

import com.imgBlog.user.mapper.UserMapper;
import com.imgBlog.user.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean login(UserVo userVo) {
        UserVo result;
        boolean passwordMatchResult = false;

        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        result = mapper.login(userVo);
        passwordMatchResult = passwordEncoder.matches(userVo.getPassword(), result.getPassword());

        return passwordMatchResult;
    }

    @Override
    public void register(UserVo userVo) {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
        mapper.register(userVo);
    }

    @Override
    public boolean IdDuplicateCheck(UserVo userVo) {
        boolean passwordMatResult = false;

        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        passwordMatResult = mapper.IdDuplicateCheck(userVo);

        return passwordMatResult;
    }
}
