package com.imgBlog.comm.code.controller;

import com.imgBlog.comm.code.service.CodeService;
import com.imgBlog.comm.code.vo.CodeVO;
import com.imgBlog.comm.util.ResponseStatus;
import com.imgBlog.comm.util.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CodeController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CodeService codeService;

    static final String CONTEXT = "code";

    @RequestMapping(value = "/getCodeList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getCodeList (@RequestBody CodeVO code){

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put(CONTEXT + "List", codeService.getCodeList(code));
            result.put(CONTEXT + "ListCount", codeService.getCodeListCount(code));

            responseMessage.putData(result);
        } catch (Exception e) {
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
            log.error(e.getMessage());
        }

        return responseMessage;
    }

    @RequestMapping(value = "/getCode", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getCode(@RequestBody CodeVO code){

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put(CONTEXT, codeService.getCode(code));

            responseMessage.putData(result);
        } catch (Exception e) {
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
            log.error(e.getMessage());
        }

        return responseMessage;
    }

    @RequestMapping(value = "/insertCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage insertCode(@RequestBody CodeVO code){

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            codeService.insertCode(code);
        } catch (Exception e) {
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
            log.error(e.getMessage());
        }

        return responseMessage;
    }

    @RequestMapping(value = "/updateCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateCode(@RequestBody CodeVO code){

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            codeService.updateCode(code);
        } catch (Exception e) {
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
            log.error(e.getMessage());
        }

        return responseMessage;
    }

    @RequestMapping(value = "/deleteCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteCode(@RequestBody CodeVO code){

        ResponseMessage responseMessage = new ResponseMessage();

        try {
            codeService.deleteCode(code);
        } catch (Exception e) {
            responseMessage.putStatus(ResponseStatus.NOT_FOUND.status());
            responseMessage.putErrorMessage(e.getMessage());
            log.error(e.getMessage());
        }

        return responseMessage;
    }
}
