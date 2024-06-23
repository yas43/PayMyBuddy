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


//
//
    @Autowired
    public UserController(UserService userService, TransactionService transactionService) {
        this.userService = userService;


    }

    @GetMapping("/signUp")
    public String registry(Model model){
        model.addAttribute("User",new User());
        return "signUp";
    }
//
//    @GetMapping("/logIn")
//    public String login(Model model){
//        model.addAttribute("User",new User());
//        return "logIn";
//    }
    @PostMapping("/registry")
    public String addUser(@ModelAttribute("User") User user){
        userService.saveUserInfo(user);
        return"save";
    }


//    @GetMapping("/display")
//    public String display(){
//        model.addAttribute("transactionHistory",userService.displayCurrentUserTransactionHistory(senderId,receiverId));
//        return "comptpage";
//    }

//    @PostMapping("/display/user")
//    public String displayhistory(Model model,@RequestParam("senderId")int senderId,@RequestParam("receiverId")int receiverId){
//        model.addAttribute("transactionHistory",userService.displayCurrentUserTransactionHistory(senderId,receiverId));
//        return "comptpage";
//    }

//
//    @PostMapping("/connection")
//    public String connectUser(@ModelAttribute("User") User user){
//        userService.saveUserInfo(user);
//        return"save";
//    }
//    @GetMapping("/show")
//    public String showalluser(Model model){
//        model.addAttribute("user",userService.showAllUser());
//        model.addAttribute("user",userService.callyaser_temp());
//        return "showallusertemporary";
//    }

//        @GetMapping("/modify")
//    public String showalluser(Model model){
//        int id = userService.loginuser("yaser","123456").getId();
//        model.addAttribute("user",userService.callUserById(id));
//        return "modifyinfo";
//    }

//    @PutMapping("/update")
//    public String update(@ModelAttribute ("User") User user){
//        int id = userService.loginuser("yaser","123456").getId();
//        if (user.getEmail() != null && user.getUsername() != null && user.getPassword() != null) {
//             userService.findUserByUsername(user.getUsername()).getId();
//            userService.callUserById();
//            int id = userService.findUserByUsername(user.getUsername()).getId();
//            userService.updateuser(user,id);
//
//
//            return "save";
//        }
//        else {
//            throw new RuntimeException("fill all the fields please");
//        }
//    }

    @GetMapping("/find")
    public String findByUsername(){
//        model.addAttribute("user",new User());
//        User user = userService.findUserByUsername(username);
//        model.addAttribute("user",user);
        return "findusertemporary";

    }

//    @PostMapping("/find/user")
//    public String findByUsernameshow(@RequestParam("username") String username,Model model){
//        User user = userService.findUserByUsername(username);
//        model.addAttribute("user",user);
//        return "resulttemporary";
//
//    }

//    @GetMapping("/display")
//    public String displayinfo(@RequestParam int receiver_id,int sender_id,Model model){
//        model.addAttribute("info",transactionService.displayTransaction(receiver_id,sender_id));
//        return "comptpage";
//    }

        @GetMapping("/query")
    public String showallusersuingquerttest(Model model){
        model.addAttribute("user",userService.showalluserusingquery());
        return "resulttemporary";
    }



//    @GetMapping("/query")
//    public String showallusersuingquerttest(Model model){
//        model.addAttribute("transaction",userService.showalltransaction(1));
//        return "showalluser_tempo";
//    }

//        @GetMapping("/query")
//    public String showallusersuingquerttest(Model model){
//        model.addAttribute("transaction",userService.showalltransaction());
//        return "showalltransaction_temporary";
//    }

@GetMapping("/transaction")
    public String showtransaction(Model model){
        model.addAttribute("transaction",userService.showalltransaction());
        return "showalltransaction_temporary";
}

@GetMapping("/transactionhistory")
    public String showtransactionhistory(Model model){
        model.addAttribute("transaction",userService.showalltransaction());
        return "transactionhistory_temporary";
}

}
