package com.ls.lssbwt.controller;

import com.ls.lssbwt.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author lostsakura
 * @create 2020-01-09 10:50
 */
@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public String logout(Map<String, Object> map) {
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        map.put("username", currentUser.getUsername());
        subject.logout();
        return "logout";
    }
}
