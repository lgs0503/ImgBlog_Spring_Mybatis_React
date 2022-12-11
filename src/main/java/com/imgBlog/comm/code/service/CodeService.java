package com.imgBlog.comm.code.service;

import com.imgBlog.comm.code.vo.CodeVO;

import java.util.List;
import java.util.Map;

public interface CodeService {

    List<Map<String, Object>> getCodeList(CodeVO code) throws Exception;

    String getCodeListCount(CodeVO code) throws  Exception;

    Map<String, Object> getCode(CodeVO code) throws Exception;

    void insertCode(CodeVO code) throws Exception;

    void updateCode(CodeVO code) throws Exception;

    void deleteCode(CodeVO code) throws Exception;

}
