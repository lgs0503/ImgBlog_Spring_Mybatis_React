package com.imgBlog.user.controller;

import com.comm.util.ResponseMessage;
import com.comm.util.ResponseStatus;
import com.imgBlog.user.service.UserService;
import com.imgBlog.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage login(@RequestBody UserVo userVo){

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            responseMessage.putData(userService.login(userVo));
        } catch (Exception e){
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
        }

        return responseMessage;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage register(@RequestBody UserVo userVo){

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            userService.register(userVo);
        } catch (Exception e){
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
        }

        return responseMessage;
    }

    @RequestMapping(value = "/IdDuplicateCheck", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage IdDuplicateCheck(@RequestBody UserVo userVo){

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            responseMessage.putData(userService.IdDuplicateCheck(userVo));
        } catch (Exception e){
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
        }

        return responseMessage;
    }
}
