package com.teamc.controller;


import com.teamc.service.TransactionService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@NoArgsConstructor
public class TransactionUserController {

    private TransactionService transactionService;

    @Autowired
    public TransactionUserController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transaction")
    public String transactionGet() {
        return "transaction";
    }


    @PostMapping("/transaction")
    public String transactionPost(@RequestParam String userName, @RequestParam String shareName, @RequestParam Double count) {
        transactionService.makeTransaction(userName, shareName, count);
        return "redirect:/userAccount";
    }
}

