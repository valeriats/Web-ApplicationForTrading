package com.teamc.service;

import com.teamc.model.TransactionUser;
import com.teamc.repository.TransactionUserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class TransactionUserServiceImpl implements TransactionUserService {

    private TransactionUserRepository transactionUserRepository;

    @Autowired
    public TransactionUserServiceImpl(TransactionUserRepository transactionUserRepository) {
        this.transactionUserRepository = transactionUserRepository;
    }

    @Override
    public List<TransactionUser> findAll() {
        return transactionUserRepository.findAll();
    }

    @Override
    public List<TransactionUser> findByUserId(Long userId){
        return transactionUserRepository.findByUserId(userId);
    }

    @Override
    public void save(TransactionUser transactionUser) {
        transactionUserRepository.save(transactionUser);
    }
}
