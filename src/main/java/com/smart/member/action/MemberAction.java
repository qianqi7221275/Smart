package com.smart.member.action;

import com.smart.common.util.OutputJson;
import com.smart.member.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oneapm on 2017/6/14.
 */
@Controller
@RequestMapping("/MemberAction")
public class MemberAction {

    @RequestMapping("login.do")
    public void login(HttpServletRequest req, HttpServletResponse res){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            OutputJson.outputJsonObject(res,OutputJson.getErrorObj("用户名或密码不能为空!",OutputJson.Code.WARNING));
            return;
        }
        try {
            String autoId = MemberService.login(username,password);
            if(StringUtils.isBlank(autoId)){
                OutputJson.outputJsonObject(res,OutputJson.getErrorObj("当前用户不存在",OutputJson.Code.WARNING));
                return;
            }else{
                req.getSession().setAttribute("userId",autoId);
                OutputJson.outputJsonObject(res,OutputJson.getSuccessObj());
            }
        } catch (Exception e) {
            OutputJson.outputJsonObject(res,OutputJson.getErrorObj(e));
        }
    }
}
