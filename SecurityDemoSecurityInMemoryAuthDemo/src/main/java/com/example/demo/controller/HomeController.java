package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHome() {
        return "HomePage";
    }

    @GetMapping("/welcome")
    public String showWelcome() {
        return "WelcomePage";
    }

    @GetMapping("/admin")
    public String showAdmin() {
        return "AdminPage";
    }

    @GetMapping("/emp")
    public String showEmp() {
        return "EmployeePage";
    }

    @GetMapping("/std")
    public String showStudent() {
        return "StudentPage";
    }
    
    @GetMapping("/common")
    public String showCommon() {
        return "CommonPage";
    }
    
    @GetMapping("/access-denied")
    public String showDenied() {
        return "DeniedPage";
    }

}
