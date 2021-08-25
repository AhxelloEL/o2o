package com.al.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request , String key){
        try{
            //将key转换为整形
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * 将key值转换为Long类型
     * @param request
     * @param key
     * @return
     */
    public static Long getLong(HttpServletRequest request,String key){
        try{
            return Long.decode(request.getParameter(key));
        }catch (Exception e){
            return -1L;
        }
    }

    public static String getString(HttpServletRequest request,String key){
        try{
            //获取key值
            String result = request.getParameter(key);
            if (result != null){
                result = result.trim();
            }
            if ("".equals(result)){
                result = null;
            }
            return result;
        }catch (Exception e){
            return "转化失败";
        }
    }

    public static Boolean getBoolean(HttpServletRequest request, String key){
        try {
            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }
}
