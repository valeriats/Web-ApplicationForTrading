package com.teamc.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.teamc.model.Share;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShareService shareService;

    @Autowired
    private TransactionUserService transactionUserService;

    @Before
    public void setUp() throws Exception {
        transactionService = new TransactionService(userService, shareService, transactionUserService);

        Share shareART = new Share("ARTTTT", "Арт", 10000.0, 23.0);
        shareService.save(shareART);
    }

    @After
    public void tearDown() throws Exception {
        shareService.delete("ARTTTT");
    }

    @Test
    public void checkUserMoney() {
        double a = transactionService.checkUserMoney(userService.findByUsername("lena"));
        Assert.assertNotNull(a);
    }

    @Test
    public void possibleToBye() {
        boolean expectTrue = transactionService.possibleToBye(userService.findByUsername("lena"), shareService.findByName("ARTTTT"), 2.0);
        boolean expectFalse = transactionService.possibleToBye(userService.findByUsername("lena"), shareService.findByName("ARTTTT"), 1000.0);
        Assert.assertTrue(expectTrue);
        Assert.assertFalse(expectFalse);
    }

    @Test
    public void makeTransaction() {
        Assert.assertNotNull(shareService.findByName("ARTTTT").getAvailable());
        transactionService.makeTransaction("lena", "ARTTTT", 3.0);
        Assert.assertEquals(10000 - (23 * 3.0), userService.findByUsername("lena").getAccount("USD"), 0);
    }
}