package com.comm.code.mapper;

import com.comm.code.vo.CodeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CodeMapper {

    List<Map<String, Object>> getCodeList(CodeVO code);

    String getCodeListCount(CodeVO code);

    Map<String, Object> getCode(CodeVO code);

    void insertCode(CodeVO code);

    void updateCode(CodeVO code);

    void deleteCode(CodeVO code);
}
