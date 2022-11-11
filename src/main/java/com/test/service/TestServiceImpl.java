package com.test.service;

import com.test.mapper.TestMapper;
import com.test.vo.TestVo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<TestVo> selectTest() {

        List<TestVo> result = new ArrayList<TestVo>();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestMapper mapper = session.getMapper(TestMapper.class);

            result = mapper.selectTest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
