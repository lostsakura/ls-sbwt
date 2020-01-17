package com.ls.lssbwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lostsakura
 * @create 2020-01-06 16:57
 */
// @EnableWebMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 注册一个ViewController来处理视图绑定问题
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /**
         * 通用页面
         */
        // 首页
        registry.addViewController("/").setViewName("login");
        // 错误警告
        registry.addViewController("/error").setViewName("error");
        // 登录
        registry.addViewController("/login").setViewName("login");
        // 注册
        registry.addViewController("/register").setViewName("register");
        // 注销
        registry.addViewController("/logout").setViewName("logout");
        // 无授权警告页面
        registry.addViewController("/admin/unauthorized").setViewName("unauthorized");


        /**
         * 管理员页面
         */
        // 管理员首页
        registry.addViewController("/admin").setViewName("admin");
        // 添加用户
        registry.addViewController("/admin/addUser").setViewName("addUser");
        // 修改用户
        registry.addViewController("/admin/updateUser").setViewName("updateUser");



    }
}
