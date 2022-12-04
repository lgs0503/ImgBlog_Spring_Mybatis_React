package com.test.service;

import com.test.mapper.TestMapper;
import com.test.vo.TestVo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> oracleStudy1() {

        Map<String, Object> result = new HashMap<String, Object>();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestMapper mapper = session.getMapper(TestMapper.class);

            result = mapper.oracleStudy1();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Map<String, Object> oracleStudy2(Map<String, Object> map) {

        Map<String, Object> result = new HashMap<String, Object>();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestMapper mapper = session.getMapper(TestMapper.class);

            result = mapper.oracleStudy2(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Map<String, Object>> oracleStudy3(Map<String, Object> map) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestMapper mapper = session.getMapper(TestMapper.class);

            result = mapper.oracleStudy3(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
