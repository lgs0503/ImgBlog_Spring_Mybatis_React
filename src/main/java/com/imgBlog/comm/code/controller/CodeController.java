package com.imgBlog.comm.code.controller;

import com.imgBlog.comm.code.service.CodeService;
import com.imgBlog.comm.code.vo.CodeVO;
import com.imgBlog.comm.util.ResponseStatus;
import com.imgBlog.comm.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CodeController {

    @Autowired
    CodeService codeService;

    static final String KEYWORD = "code";

    @RequestMapping(value = "/getCodeList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getCodeList (@RequestBody CodeVO code){

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put(KEYWORD + "List", codeService.getCodeList(code));
            result.put(KEYWORD + "ListCount", codeService.getCodeListCount(code));

            responseMessage.putData(result);
        } catch (Exception e) {
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
        }

        return responseMessage;
    }

    @RequestMapping(value = "/getCode", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getCode(@RequestBody CodeVO code) throws Exception{

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put(KEYWORD, codeService.getCode(code));

            responseMessage.putData(result);
        } catch (Exception e) {
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
        }

        return responseMessage;
    }

    @RequestMapping(value = "/insertCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertCode(@RequestBody CodeVO code) throws Exception{

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            codeService.insertCode(code);
        } catch (Exception e) {
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
        }

        return responseMessage;
    }

    @RequestMapping(value = "/updateCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateCode(@RequestBody CodeVO code) throws Exception{

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            codeService.updateCode(code);
        } catch (Exception e) {
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
        }

        return responseMessage;
    }

    @RequestMapping(value = "/deleteCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteCode(@RequestBody CodeVO code) throws Exception{

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            codeService.deleteCode(code);
        } catch (Exception e) {
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
        }

        return responseMessage;
    }
}
