package com.smart.common.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by oneapm on 2017/6/14.
 */
public class OutputJson {

    public static void outputJsonObject(HttpServletResponse response, JSONObject obj){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.print(obj);
        } catch (Exception e) {
            pw.close();
        }finally{
            pw.close();
        }
    }

    public static JSONObject getErrorObj(Exception e,Code code){
        return getErrorObj(e.getMessage(),code);
    }

    public static JSONObject getSuccessObj(){
       JSONObject obj = new JSONObject();
       obj.put("code",Code.SUCCESS.getCode());
       return obj;
    }

    public static JSONObject getErrorObj(String errorMsg,Code code){
        JSONObject obj = new JSONObject();
        obj.put("errorMsg",errorMsg);
        obj.put("code",code.getCode());
        return obj;
    }
    public static JSONObject getErrorObj(String errorMsg){
        return getErrorObj(errorMsg,Code.ERROR);
    }
    public static JSONObject getErrorObj(Exception e){
        return getErrorObj(e.getMessage());
    }

    public enum  Code{
        SUCCESS("200"),ERROR("500"),SERVICEERROR("700"),WARNING("800");

        private Code(String code){this.code = code;}
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
