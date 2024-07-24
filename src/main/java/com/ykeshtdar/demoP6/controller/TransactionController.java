package com.ykeshtdar.demoP6.controller;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.service.*;
import jakarta.persistence.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("transfer")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
//    @GetMapping
//    public String pay(){
//        return "comptpage";
//    }

    @PostMapping("/pay")
    public String pay( @RequestParam("email")String receiverEmail,
                           @RequestParam("amount")float amount, @RequestParam("description")String description){
      transactionService.createTransaction(receiverEmail,amount,description);
      return "redirect:/user/alltransaction";

    }
}
