package com.ykeshtdar.demoP6.controller;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.service.*;
import jakarta.persistence.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

@Controller
@RequestMapping("transfer")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/pay")
    public String pay(@RequestParam(value = "email",defaultValue = "")String receiverEmail,
                      @RequestParam(value = "amount",defaultValue = "")String amount,
                      @RequestParam(value = "description",defaultValue = "")String description,
                      RedirectAttributes redirectAttributes){
        try {

            transactionService.createTransaction(receiverEmail, amount, description);
            redirectAttributes.addFlashAttribute("success","payment successfully ");
            return "redirect:/user/alltransaction";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error","payment not success ,try again please");
            return "redirect:/user/alltransaction";
        }

    }
}
