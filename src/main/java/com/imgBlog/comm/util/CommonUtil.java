package com.imgBlog.comm.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class CommonUtil {

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
}
