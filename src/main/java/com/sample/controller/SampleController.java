package com.sample.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j
public class SampleController {


    @RequestMapping(value = "/jsonDataTest", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> jsonDataTest(@RequestBody Map<String, Object> param){

        log.info("index controller start!!");
        List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
        map.add(param);
        map.add(param);
        map.add(param);
        map.add(param);
        map.add(param);
        map.add(param);
        map.add(param);
        map.add(param);
        map.add(param);
        map.add(param);
        map.add(param);
        map.add(param);
        map.add(param);
        return map;
    }


    @RequestMapping(value = "/jsonDataTestMap", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> jsonDataTestMap(@RequestBody Map<String, Object> param){

        log.info("index controller start!!");
        Map<String, Object> map = new HashMap<String,Object>();

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for(int i = 0 ; i < 10 ; i++) {
            Map<String, Object> mapTemp = new HashMap<String,Object>();
            mapTemp.put("no", i);
            mapTemp.put("id", "id"+ i);
            mapTemp.put("pw", "pw"+ i);
            list.add(mapTemp);
        }

        map.put("status", "200");
        map.put("list", list);
        map.put("count", list.size());

        return map;
    }

    @RequestMapping("/")
    public ModelAndView index(){
        log.info("index controller start!!");

        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "Spring Test");
        mav.addObject("content", "Hello World!!");
        mav.setViewName("index");

        return mav;
    }


    @RequestMapping("/test")
    public ModelAndView test(){
        log.info("index controller start!!");

        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "Spring Test page");
        mav.addObject("content", "TestPage");
        mav.setViewName("test");

        return mav;
    }
}