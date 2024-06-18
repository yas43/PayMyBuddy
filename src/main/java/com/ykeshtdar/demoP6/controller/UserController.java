package com.ykeshtdar.demoP6.controller;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.service.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registry(Model model){
        model.addAttribute("User",new User());
        return "registery";
    }
    @PostMapping("/registry")
    public String addUser(@ModelAttribute("User") User user){
        userService.saveUserInfo(user);
        return"save";
    }
//    @GetMapping("/show")
//    public String showalluser(Model model){
//        model.addAttribute("user",userService.showAllUser());
//        model.addAttribute("user",userService.callyaser_temp());
//        return "showallusertemporary";
//    }

        @GetMapping("/show")
    public String showalluser(Model model){
        model.addAttribute("user",userService.callUserById(id));
        return "showallusertemporary";
    }

    @PutMapping("/update")
    public String update(@ModelAttribute ("User") User user){
        userService.updateuser(user);
        return "save";
    }

}
