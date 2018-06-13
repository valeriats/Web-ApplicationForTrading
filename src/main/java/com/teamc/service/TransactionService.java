package com.teamc.service;

import com.teamc.mail.Mail;
import com.teamc.model.Account;
import com.teamc.model.Share;
import com.teamc.model.TransactionUser;
import com.teamc.model.User;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@NoArgsConstructor
public class TransactionService {

    private UserService userService;

    private ShareService shareService;

    private TransactionUserService transactionUserService;

    @Autowired
    public TransactionService(UserService userService, ShareService shareService, TransactionUserService transactionUserService) {
        this.userService = userService;
        this.shareService = shareService;
        this.transactionUserService = transactionUserService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public void makeTransaction(String userName, String shareName, Double shareAmount) {
        User userdb = userService.findByUsername(userName);
        Share share = shareService.findByName(shareName);
        Share shareUSD = shareService.findByName("USD");

        if (share.getAvailable() >= shareAmount) {
            if (possibleToBye(userdb, share, shareAmount)) {
                userService.userChangeAcc(userdb, new Account("USD", -share.getPrice() * shareAmount));
                userService.userChangeAcc(userdb, new Account(share.getName(), shareAmount));
                shareService.shareAvailable(share, share.getAvailable() - shareAmount);
                shareService.shareAvailable(shareUSD, shareUSD.getAvailable() + share.getPrice() * shareAmount);

                transactionUserService.save(new TransactionUser(userdb.getUserID(), shareName, shareAmount, share.getPrice() * shareAmount, new Date()));
                Mail.sendMail(userdb.getMail(), "Новая транзакция", String.format("%s, вы успешно совершили транзакцию. Вы купили %s %s за %s $", userdb.getFirstName(), shareAmount, shareName, share.getPrice() * shareAmount));

                log.info(String.format("Transaction will be comleted successful, UserName: %s, ShareName: %s, ShareAmount: %s", userdb.getUsername(), shareName, shareAmount));
            } else {
                log.info(String.format("Sorry, user has not money, UserName: %s, ShareName: %s, ShareAmount: %s", userdb.getUsername(), shareName, shareAmount));
            }
        } else {
            log.info(String.format("Bank has not money, UserName: %s, ShareName: %s, ShareAmount: %s", userdb.getUsername(), shareName, shareAmount));
        }
    }

    public boolean possibleToBye(User user, Share share, Double amount) {
        return (checkUserMoney(user) != null) && (share.getPrice() * amount <= checkUserMoney(user));
    }

    public Double checkUserMoney(User user) {
        List<Account> accountList = user.getAccountList();

        Account accountUsd = accountList.stream()
                .filter(x -> "USD".equals(x.getName()))
                .findAny().orElse(null);

        if (accountUsd != null) {
            return accountUsd.getAmount();
        } else {
            return null;
        }
    }


    public void givePriz(String userName, String shareName) {
        Share share = shareService.findByName(shareName);
        User user = userService.findByUsername(userName);
        if (share.getAvailable() >= 1) {
            userService.userChangeAcc(user, new Account(share.getName(), 1.0));
            shareService.shareAvailable(share, share.getAvailable() - 1);
        } else {
            log.info("User checkout empty Priz(((");
        }
    }
}