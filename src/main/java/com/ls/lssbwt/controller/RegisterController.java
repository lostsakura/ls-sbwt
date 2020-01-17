package com.ls.lssbwt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ls.lssbwt.domain.User;
import com.ls.lssbwt.repository.UserMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author lostsakura
 * @create 2020-01-09 12:08
 */
@Controller
public class RegisterController {

    @Resource
    private UserMapper userMapper;

    private String msg = "";

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Map<String, Object> map) {
        if (!username.isEmpty() && !password.isEmpty()) {

            // 保证用户唯一性
            User query = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
            if (query != null) {
                msg = "用户名已被注册！";
                map.put("msg", msg);
                return "register";
            }
            User user = new User();

            ByteSource credentialsSalt = ByteSource.Util.bytes(username);
            // 将用户的密码加盐MD5处理存入数据库
            String md5Password = new Md5Hash(password, credentialsSalt,777).toString();

            // 存储用户信息
            user.setUsername(username);
            user.setPassword(md5Password);
            user.setPerms("user:default");
            int insert = userMapper.insert(user);

            if (insert == 1) {
                msg = "注册成功";
                map.put("msg", msg);
                return "success";
            }
        }
        msg = "请正确输入用户名和密码";
        map.put("msg", msg);
        return "register";
    }

}
