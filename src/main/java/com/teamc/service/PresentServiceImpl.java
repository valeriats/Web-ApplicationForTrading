package com.teamc.service;


import com.teamc.model.Share;
import com.teamc.model.User;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@NoArgsConstructor
public class PresentServiceImpl implements PresentService {

    private UserService userService;

    private ShareService shareService;

    private TransactionService transactionService;

    @Autowired
    public PresentServiceImpl(UserService userService, ShareService shareService, TransactionService transactionService) {
        this.userService = userService;
        this.shareService = shareService;
        this.transactionService = transactionService;
    }


    private String getAuthUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }


    public String checkoutPriz(int number) {
        String shareName = threePriz().get(number - 1).getName();
        if (number > 0 && number < 4) {
            String name = getAuthUserName();
            User user = userService.findByUsername(name);
            if (user.isPrizStatus()) {
                transactionService.givePriz(name, shareName);
                user.setPrizStatus(false);
                userService.save(user);
            } else {
                log.info("You already have a priz");
            }
        }
        return shareName;
    }


    private List<Share> threePriz() {
        return shareService.threeRandomShare();
    }


    //@Scheduled(cron = "0 1 0 * * *", zone = "Europe/Moscow")
    @Scheduled(fixedRate = 3_000_000)
    private void setStatusTrue() throws InterruptedException {
        Thread.sleep(3_000);
        userService.setPrizStatusTrue();
        log.info("Обновлены статусы именинников");
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void setStatusFalse() throws InterruptedException {
        Thread.sleep(10_000);
        userService.setPrizStatusFalse();
    }
}
