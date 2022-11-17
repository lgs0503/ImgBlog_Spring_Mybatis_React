package com.imgBlog.user.controller;

import com.imgBlog.user.service.UserService;
import com.imgBlog.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestBody UserVo userVo){

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("loginResult", userService.login(userVo));

        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(@RequestBody UserVo userVo){

        Map<String, Object> result = new HashMap<String, Object>();

        userService.register(userVo);

        return result;
    }
}
