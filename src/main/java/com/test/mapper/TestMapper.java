package com.test.mapper;

import com.test.vo.TestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {

    public List<TestVo> selectTest();

    public Map<String, Object> oracleStudy1();

    public Map<String, Object> oracleStudy2(Map<String, Object> map);

    public List<Map<String, Object>> oracleStudy3(Map<String, Object> map);
}
