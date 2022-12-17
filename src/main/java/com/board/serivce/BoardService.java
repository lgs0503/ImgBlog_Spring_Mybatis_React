package com.board.serivce;

import java.util.List;
import java.util.Map;

public interface BoardService
{
    List<Map<String, Object>> getBoardList(Map<String, Object> param);

    void addBoard(Map<String, Object> param);

}
