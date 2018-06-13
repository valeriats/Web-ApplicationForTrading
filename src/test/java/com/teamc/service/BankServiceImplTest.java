package com.teamc.service;

import com.teamc.model.Account;
import com.teamc.model.Bank;
import com.teamc.model.Share;
import com.teamc.model.User;
import com.teamc.repository.BankRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankServiceImplTest {

    private List<Share> shares;
    private List<User> users;

    @Autowired
    private BankRepository bankRepository;

    @Before
    public void setUp() {
        List<Account> accountsLena = new LinkedList<>();
        accountsLena.add(new Account("USD", 10000.00));
        User userLena = new User("Elena1", "Pavlova", "Moscow", accountsLena);

        users = new LinkedList<>();
        users.add(userLena);

        shares = new LinkedList<>();
        shares.add(new Share("EUR", "EUR", 100000000.00, 100000000.00));
    }

    @After
    public void tearDown() {
        shares = null;
        users = null;
    }

    @Test
    public void save() {
        Bank bank = new Bank("Raiffeisen1", "1111", "2222", "5555", shares, users);
        bankRepository.save(bank);
        System.out.println("---------------------------------------");
        System.out.println(bankRepository.findByName("Raiffeisen1"));
        System.out.println("---------------------------------------");
        assertEquals(bank, bankRepository.findByName("Raiffeisen1"));
    }

}