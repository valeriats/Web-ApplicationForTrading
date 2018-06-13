package com.teamc.controller.rest;

import com.teamc.model.TransactionUser;
import com.teamc.service.TransactionUserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@NoArgsConstructor
public class TrasactionUserControllerRest {

    private TransactionUserService transactionUserService;

    @Autowired
    public TrasactionUserControllerRest(TransactionUserService transactionUserService) {
        this.transactionUserService = transactionUserService;
    }

    @GetMapping(value = "/get/transaction/user/{userId}", produces = "application/json")
    public List<TransactionUser> getTransactionUser(@PathVariable("userId") Long userId) {
        return transactionUserService.findByUserId(userId);
    }
}
