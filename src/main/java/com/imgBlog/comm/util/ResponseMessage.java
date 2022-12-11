package com.imgBlog.comm.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseMessage extends HashMap<String, Object> {

    public ResponseMessage(){
        this.put("status", ResponseStatus.OK.status());
    }

    public void putData(Object value){
        this.put("data", value);
    }

    public Map<String, Object> getData(){
        return (Map<String, Object>) this.get("data");
    }

    public void putStatus(int status){
        this.put("status", status);
    }

    public int getStatus(){
        return (int) this.get("status");
    }

    public void putErrorMessage(String ErrorMessage){
        this.put("errorMessage", ErrorMessage);
    }

    public String getErrorMessage(){
        return (String) this.get("errorMessage");
    }
}
