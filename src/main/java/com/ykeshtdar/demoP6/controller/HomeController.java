package com.ykeshtdar.demoP6.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {
@GetMapping
    public String home(){
        return "home";
    }
}
