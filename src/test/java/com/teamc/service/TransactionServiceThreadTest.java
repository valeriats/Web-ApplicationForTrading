package com.teamc.service;

import com.teamc.model.Account;
import com.teamc.model.Share;
import com.teamc.model.User;
import org.hibernate.exception.LockAcquisitionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceThreadTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ShareService shareService;


    @Autowired
    private TransactionService transactionService;

    @Before
    public void setUp() {
        shareService.save(new Share("MYSHARE", "MYSHARE", 10, 1));
    }

    @Test
    public void makeTransaction() throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for(int i = 0; i < 20; i++) {
            Runnable r  = () -> {
                for (int j = 0; j < 20; j++) {
                    try {
                        transactionService.makeTransaction("roma", "MYSHARE", 1.00);
                    } catch (LockAcquisitionException e) {

                    }
                    System.out.println("Thread" + j + ":" + shareService.findByName("MYSHARE"));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
            };
            executor.execute(r);
        }

        executor.shutdown();

        while (!executor.isTerminated()){}

        Share sharedb = shareService.findByName("MYSHARE");
        User userdb = userService.findByUsername("roma");
        List<Account> accountList = userdb.getAccountList();

        Account accountUser = accountList.stream()
                .filter(x -> "MYSHARE".equals(x.getName()))
                .findAny().orElse(null);


        System.out.println("-------------------------------------------");
        System.out.println(userdb);
        System.out.println(sharedb);
        System.out.println("-------------------------------------------");

        Assert.assertEquals(2, accountList.size());
        Assert.assertEquals(new Double(0), sharedb.getAvailable());
        Assert.assertEquals(new Double(10), accountUser.getAmount());
    }
}
