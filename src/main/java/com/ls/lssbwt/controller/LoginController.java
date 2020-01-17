package com.ls.lssbwt.controller;

import com.ls.lssbwt.repository.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author lostsakura
 * @create 2020-01-07 16:15
 */

@Controller
public class LoginController {

    private static Logger LOGGER =  LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserMapper userMapper;

    @GetMapping("/login")
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            return "redirect:/admin";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "rememberMe", required = false) String rememberMe,
                        Map<String, Object> map,
                        HttpSession session) {
        String msg = "";

        String r = rememberMe;
        // 使用Shiro
        // 获取一个Subject
        Subject subject = SecurityUtils.getSubject();
        // 封装用户登陆的数据信息
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 记住我
        if (rememberMe!=null && "1".equals(rememberMe)) {
            token.setRememberMe(true);
        }
        // 执行登陆方法
        // 任何不成功的登陆行为都将以异常的方式抛出
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            msg = "用户不存在！";
            LOGGER.info("用户不存在！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            msg = "用户名或密码错误！";
            LOGGER.info("用户名或密码错误！");
            return "login";
        } finally {
            map.put("msg", msg);
        }

        session.setAttribute("username",username);
        return "redirect:/admin";
    }

}
