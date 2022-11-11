package com.test.controller;

import com.test.service.TestService;
import com.test.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "/testView")
    public ModelAndView view (){
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "안녕하세용 뭘봐");
        mv.addObject("content", "죄송합니다");
        mv.setViewName("index");

        return mv;
    }


    @RequestMapping(value = "/jsonTest", method = RequestMethod.POST)
    @ResponseBody
    public List<TestVo> jsonTest(){
        return testService.selectTest();
    }
}
