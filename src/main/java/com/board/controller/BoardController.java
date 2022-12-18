package com.board.controller;

import com.board.serivce.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping(value = {"/boardView"}, method = RequestMethod.GET)
    public ModelAndView boardView (HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> map = new HashMap<String, Object>();
        String title = request.getParameter("title") != null ? request.getParameter("title") : "";
        String userName = request.getParameter("userName") != null ? request.getParameter("userName") : "";

        map.put("title", title);
        map.put("userName", userName);

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/board/index");
        mv.addObject("boardList", boardService.getBoardList(map));
        mv.addObject("title", title);
        mv.addObject("userName", userName);
        return mv;
    }

    @RequestMapping(value = {"/addBoardView"}, method = RequestMethod.GET)
    public ModelAndView addBoardView (HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/addBoard");
        return mv;
    }

    @RequestMapping(value = {"/addBoard"}, method = RequestMethod.POST)
    public ModelAndView addBoard (HttpServletRequest request, HttpServletResponse response){

        String title = request.getParameter("title") != null ? request.getParameter("title") : "";
        String content = request.getParameter("content") != null ? request.getParameter("content") : "";
        String userName = request.getParameter("userName") != null ? request.getParameter("userName") : "";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("title", title);
        param.put("content", content);
        param.put("userName", userName);

        boardService.addBoard(param);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/boardView");
        return mv;
    }

    @RequestMapping(value = {"/detailBoardView"}, method = RequestMethod.GET)
    public ModelAndView detailBoardView (HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> map = new HashMap<String, Object>();

        String index = request.getParameter("index") != null ? request.getParameter("index") : "";
        map.put("index", index);

        ModelAndView mv = new ModelAndView();
        mv.addObject("board", boardService.getBoard(map));
        mv.setViewName("/board/detailBoard");
        return mv;
    }

    @RequestMapping(value = {"/deleteBoard"}, method = RequestMethod.GET)
    public ModelAndView deleteBoard (HttpServletRequest request, HttpServletResponse response){

        String postId = request.getParameter("postId") != null ? request.getParameter("postId") : "";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("postId", postId);

        boardService.deleteBoard(param);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/boardView");
        return mv;
    }
}
