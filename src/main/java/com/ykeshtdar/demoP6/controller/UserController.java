package com.ykeshtdar.demoP6.controller;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.User;
import com.ykeshtdar.demoP6.repository.*;
import com.ykeshtdar.demoP6.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
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
    public String registerUser(@ModelAttribute("User") User user){
        try {
            userService.createUser(user);
            return "redirect:/user/login";
        }catch (Exception e){
            return "signUp";
        }
//        userService.createUser(user);
//        return"save";
    }

    @GetMapping("/login")
    public String login(){
//        model.addAttribute("User",new User());
        return "logIn";
    }
//    @PostMapping("/login")
//    public String addUser(@RequestParam("email") String email,@RequestParam("password")String password){
////        userService.logIn(email);
//        customUserDetailService.loadUserByUsername(email);
//        return"save";
//    }

    @GetMapping("/welcome")
    public  String welcome(Model model){
        model.addAttribute("username",new User());
        User currentUser = userService.getconnectedUser();
        model.addAttribute("username",currentUser.getUsername());
        return "welcome";
    }


    @GetMapping("/modify")
    public String showcurrentuserinfo(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
            UserDetails userDetails = (UserDetails) principal;
            System.out.println(userDetails.getUsername());

//        System.out.println(userRepository.findByEmail(userDetails.getUsername()));
var user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(()->new UsernameNotFoundException("user did not found"));
        model.addAttribute("user",user);
        return "modifyinfo";


//        int id = 2;
//        model.addAttribute("user",userRepository.findById(id) );
//        return "modifyinfo";
    }

    @PutMapping("/modify")
    public String update(@ModelAttribute ("User") User user){
            userService.updateuser(user);
            return "redirect:/user/modify";
    }



@GetMapping("/alltransaction")
    public String showalltarnsaction(){
    return "comptpage";
}

@PostMapping("/alltransaction")
    public String displayalltransaction(Model model,@RequestParam("email") String email){
//    System.out.println("senderId is :"+senderId);
    System.out.println("receiverId is "+email);
        model.addAttribute("transaction",userService.findallTransaction(email));
        return "comptpage";
}

@GetMapping("addbeneficiary")
public String addbeneficiary(){
        return "addBeneficiary";
}





@PutMapping("/addbeneficiary")
    public String addbeneficiary(@RequestParam("email") String email){
//        User beneficiary = userRepository.findByEmail(email)
//                .orElseThrow(()->new UsernameNotFoundException("this email dose not exist"));
//    System.out.println("beneficiary email is"+beneficiary.getEmail());
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principle = authentication.getPrincipal();
//        UserDetails userDetails = (UserDetails) principle;
//       User user = userRepository.findByEmail(userDetails.getUsername())
//               .orElseThrow(()->new UsernameNotFoundException("user did not find"));
//    System.out.println("user email is "+user.getEmail());
//       user.getUserSet().add(beneficiary);
//    System.out.println(user.getUserSet());
//       userRepository.save(user);
    userService.addBeneficiary(email);
      return "redirect:/user/addbeneficiary";


}

}
