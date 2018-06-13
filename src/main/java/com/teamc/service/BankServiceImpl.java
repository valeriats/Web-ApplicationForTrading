package com.teamc.service;

import com.teamc.model.Bank;
import com.teamc.repository.BankRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void save(Bank bank) {
        bankRepository.save(bank);
    }


    @Override
    public Bank findByName(String name) {
        return bankRepository.findByName(name);
    }
}
