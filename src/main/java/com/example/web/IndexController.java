package com.example.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String homePage() {
        return "home";
    }

    @RequestMapping("/index")
    public String index() {
        return "home";
    }

}
