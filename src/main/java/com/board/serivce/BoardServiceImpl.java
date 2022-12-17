package com.board.serivce;

import com.board.mapper.BoardMapper;
import com.imgBlog.comm.code.mapper.CodeMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Map<String, Object>> getBoardList(Map<String, Object> param) {
        SqlSession session = sqlSessionFactory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);

        return mapper.getBoardList(param);
    }

    @Override
    public void addBoard(Map<String, Object> param) {
        SqlSession session = sqlSessionFactory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);

        mapper.addBoard(param);

    }

}
