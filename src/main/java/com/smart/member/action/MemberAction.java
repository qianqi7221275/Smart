package com.smart.member.action;

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
        System.out.println("abcde");
    }
}
