package com.imgBlog.comm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class CommonUtil {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public static Boolean empty(Object obj) {
        Boolean result;

        if (obj instanceof String)
            result = obj == null || "".equals(obj.toString().trim());
        else if (obj instanceof List)
            result =  obj == null || ((List<?>) obj).isEmpty();
        else if (obj instanceof Map)
            result =  obj == null || ((Map<?, ?>) obj).isEmpty();
        else if (obj instanceof Object[])
            result =  obj == null || Array.getLength(obj) == 0;
        else
            result =  obj == null;

        return result;
    }

    public static Boolean notEmpty(Object obj) {
        return !empty(obj);
    }

    public static Object notValue(Object obj, Object value){
        if (empty(obj))
            obj = value;
        return obj;
    }

    /**
     *  폴더 생성
     * @param dirPath 폴더위치
     */
    public static boolean makeDirectories(String dirPath){
        boolean result;
        File f = new File(dirPath);

        // 최 하위 디렉토리에 대해서만 생성을 함.
        // 최 하위 디렉토리의 바루 상위 디렉토리가 존재하지 않을 경우,
        // 디렉토리가 생성되지 못하고, false를 리턴함

        result = f.mkdirs();

        return result;
    }
}
