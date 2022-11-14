package com.user.service;

import com.user.mapper.UserMapper;
import com.user.vo.UserVo;
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

        UserVo result = new UserVo();
        boolean passwordMatRes = false;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            result = mapper.login(userVo);

            passwordMatRes = passwordEncoder.matches(userVo.getPassword(), result.getPassword());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return passwordMatRes;
    }

    @Override
    public void register(UserVo userVo) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
            mapper.register(userVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
