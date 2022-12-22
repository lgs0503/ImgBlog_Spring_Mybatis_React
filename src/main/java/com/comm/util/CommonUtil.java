package com.comm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class CommonUtil {

    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);
    
    /**
     *  비어있는지 확인
     */
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

    /**
     *  비어있지 않은지 확인
     */
    public static Boolean notEmpty(Object obj) {
        return !empty(obj);
    }

    /**
     * 비어있으면 value 값으로 변경
     */
    public static Object notValue(Object obj, Object value){
        if (empty(obj))
            obj = value;
        return obj;
    }

    /**
     *  폴더 생성
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

    /**
     *  파일 BASE64 인코딩
     */
    public static String fileToBase64(File file) {

        String encodingFileString = "";

        try {

            byte[] data = new byte[(int) file.length()];
            try (FileInputStream stream = new FileInputStream(file)) {
                stream.read(data, 0, data.length);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            encodingFileString = Base64.getEncoder().encodeToString(data);

            if (encodingFileString.equals("")) {
               throw new IOException();
            }
        } catch(IOException e) {
            log.error("fileToBase64 Exception", e);
        }

        return encodingFileString;
    }
}
