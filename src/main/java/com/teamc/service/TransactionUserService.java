package com.teamc.service;

import com.teamc.model.TransactionUser;

import java.util.List;

public interface TransactionUserService {

    List<TransactionUser> findAll();

    List<TransactionUser> findByUserId(Long userId);

    void save(TransactionUser transactionUser);

}
