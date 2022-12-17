package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    List<Map<String, Object>> getBoardList(Map<String, Object> param);

    void addBoard(Map<String, Object> param);
}
