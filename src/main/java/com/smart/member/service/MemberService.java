package com.smart.member.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smart.common.util.DBUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by oneapm on 2017/6/14.
 */
public class MemberService {

    public static String login(String username,String password) throws Exception {
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new Exception("用户名或密码不能为空!");
        }
        String sql = "select * from member where username=?";
        JSONArray result = DBUtil.query(sql,new Object[]{username});
        if(null != result && result.size() > 0){
            JSONObject  obj;
            for(int i = 0,size = result.size();i<size;i++){
                obj = result.getJSONObject(i);
                if(password.equals(obj.getString("PASSWORD"))){
                    return obj.getString("AUTO_ID");
                }
            }
            return null;
        }else{
            return null;
        }
    }

}
