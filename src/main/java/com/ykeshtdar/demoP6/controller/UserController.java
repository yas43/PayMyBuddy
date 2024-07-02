package com.ykeshtdar.demoP6.controller;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.repository.*;
import com.ykeshtdar.demoP6.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final CustomUserDetailService customUserDetailService;


    @Autowired
    public UserController(UserService userService, UserRepository userRepository, CustomUserDetailService customUserDetailService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.customUserDetailService = customUserDetailService;
    }

    @GetMapping("/signUp")
    public String registerUser(Model model){
        model.addAttribute("User",new User());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String connectUser(@ModelAttribute("User") User user){
        userService.saveUserInfo(user);
        return"save";
    }

    @GetMapping("/login")
    public String login(){
//        model.addAttribute("User",new User());
        return "logIn";
    }
    @PostMapping("/login")
    public String addUser(@RequestParam("email") String email,@RequestParam("password")String password){
//        userService.logIn(email);
        customUserDetailService.loadUserByUsername(email);
        return"save";
    }

    @GetMapping("/welcome")
    public  String welcome(){
        return "welcome";
    }


        @GetMapping("/modify")
    public String showcurrentuserinfo(Model model){
        int id = 1;
        model.addAttribute("user",userRepository.findById(id) );
        return "modifyinfo";
    }

    @PutMapping("/modify")
    public String update(@ModelAttribute ("User") User user){
            userService.updateuser(user);
            return "save";
    }



@GetMapping("/alltransaction")
    public String showalltarnsaction(){
    return "comptpage";
}

@PostMapping("/alltransaction")
    public String displayalltransaction(Model model,@RequestParam("senderId") int senderId,@RequestParam("receiverId") int reciverId){
    System.out.println("senderId is :"+senderId);
    System.out.println("receiverId is "+reciverId);
        model.addAttribute("transaction",userService.findalltransaction(senderId,reciverId));
        return "comptpage";
}

}
