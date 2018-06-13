package com.teamc.service;

import com.teamc.model.Bank;

public interface BankService  {

    void save(Bank bank);

    Bank findByName(String name);
}
