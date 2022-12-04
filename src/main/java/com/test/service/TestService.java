package com.test.service;

import com.test.vo.TestVo;

import java.util.List;
import java.util.Map;

public interface TestService {
    List<TestVo> selectTest();

    Map<String, Object> oracleStudy1();

    Map<String, Object> oracleStudy2(Map<String, Object> map);

    List<Map<String, Object>> oracleStudy3(Map<String, Object> map);

}
