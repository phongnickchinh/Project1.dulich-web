package com.pweb.dulich.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;



@Controller
@RequestMapping("/admin")
public class TourController {
    @GetMapping("/home")
    public String mainPage() {
        return "admin/index";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }
}
