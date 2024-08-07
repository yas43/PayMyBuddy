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
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

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

    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("User",new User());
        return "logIn";
    }
//    @PostMapping("/login")
//    public String addUser(@RequestParam("email") String email,@RequestParam("password")String password){
////        userService.logIn(email);
//        customUserDetailService.loadUserByUsername(email);
//        return"redirect:/user/logIn";

//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email,password);
//        Authentication authentication = authenticationManager.authenticate(authToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return "user logged successfully";
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
var user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(()->new UsernameNotFoundException("user did not found"));
        model.addAttribute("user",user);
        return "modifyinfo";



    }

    @PutMapping("/modify")
    public String update(@ModelAttribute ("User") User user,RedirectAttributes redirectAttributes){
        try {
            userService.updateuser(user);
            redirectAttributes.addFlashAttribute("success","information modify successfully");
            return "redirect:/user/modify";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error","try again ,some things go wrong ");
            return "redirect:/user/modify";
        }

    }



@GetMapping("/alltransaction")
    public String showalltarnsaction(Model model){
    model.addAttribute("transaction", new Transaction());
    model.addAttribute("options",userService.findAllFriends());
    model.addAttribute("transaction",userService.findallTransaction());
        return "comptpage";
}



@GetMapping("addbeneficiary")
public String addbeneficiary(Model model){
        model.addAttribute("friends",userService.findAllFriends());
        return "addBeneficiary";
}





@PutMapping("/addbeneficiary")
    public String addbeneficiary(@RequestParam("email") String email, RedirectAttributes redirectAttributes){

   try {
       userService.addBeneficiary(email);
       redirectAttributes.addFlashAttribute("success","friend added successfuly");
       return "redirect:/user/addbeneficiary";
   }catch (Exception e){
       redirectAttributes.addFlashAttribute("error","friend didi not added ,try again");
       return "redirect:/user/addbeneficiary";
   }

}

}
