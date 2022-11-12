package com.user.service;

import com.user.mapper.UserMapper;
import com.user.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Override
    public int login(UserVo userVo) {

        int result = 0;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            result = mapper.login(userVo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
