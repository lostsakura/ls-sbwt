package com.ls.lssbwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lostsakura
 * @create 2020-01-08 15:29
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/")
    public String adminLogin() {
        return "admin";
    }

    @GetMapping("/unauthorized")
    public String adminUnauthorized() {
        return "unauthorized";
    }

    @GetMapping("/addUser")
    public String addUser() {
        return "addUser";
    }

    @GetMapping("/updateUser")
    public String updateUser() {
        return "updateUser";
    }
}
