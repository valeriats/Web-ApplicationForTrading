//package com.teamc.init;
//
//import com.teamc.model.*;
//import com.teamc.service.BankService;
//import com.teamc.service.RoleService;
//import com.teamc.service.ShareService;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Set;
//
//
//@Slf4j
//@Component
//@NoArgsConstructor
//public class DbDataGenerator implements ApplicationListener<ContextRefreshedEvent> {
//
//
//    private BankService bankService;
//
//    private ShareService shareService;
//
//    private RoleService roleService;
//
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public DbDataGenerator(BankService bankService, ShareService shareService, RoleService roleService, PasswordEncoder passwordEncoder) {
//        this.bankService = bankService;
//        this.shareService = shareService;
//        this.roleService = roleService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//
//        Role roleUser = new Role("ROLE_USER");
//        Role roleAdmin = new Role("ROLE_ADMIN");
//        roleService.save(roleUser);
//        roleService.save(roleAdmin);
//
//        shareService.save(new Share("USD", "USD", 10000000.00, 1.00));
//
//        Set<Role> roleRoman = new HashSet<>();
//        roleRoman.add(roleAdmin);
//        List<Account> accountsRoman = new LinkedList<>();
//        accountsRoman.add(new Account("USD", 10000.00));
//        User userRoman = new User("Roman", "Polovintsev", "Moscow", "roma", "1", true, roleRoman, LocalDate.of(1992, 5, 26), accountsRoman, "Roman.POLOVINTSEV@raiffeisen.ru");
//        userRoman.setPassword(passwordEncoder.encode(userRoman.getPassword()));
//
//        Set<Role> roleLena = new HashSet<>();
//        roleLena.add(roleAdmin);
//        List<Account> accountsLena = new LinkedList<>();
//        accountsLena.add(new Account("USD", 10000.00));
//        User userLena = new User("Elena", "Pavlova", "Moscow", "lena", "1", true, roleLena, LocalDate.of(1995, 5, 26), accountsLena, "Elena.Vl.PAVLOVA@raiffeisen.ru");
//        userLena.setPassword(passwordEncoder.encode(userLena.getPassword()));
//
//        Set<Role> roleLuna = new HashSet<>();
//        roleLuna.add(roleAdmin);
//        List<Account> accountsLuna = new LinkedList<>();
//        accountsLuna.add(new Account("USD", 10000.00));
//        User userLuna = new User("Luna", "Pavlova", "Moscow", "luna", "1", true, roleLuna, LocalDate.of(1995, 5, 28), accountsLuna, "Elena.Vl.PAVLOVA@raiffeisen.ru");
//        userLuna.setPassword(passwordEncoder.encode(userLuna.getPassword()));
//
//        Set<Role> roleLera = new HashSet<>();
//        roleLera.add(roleUser);
//        List<Account> accountsLera = new LinkedList<>();
//        accountsLera.add(new Account("USD", 10000.00));
//        User userLera = new User("Valeria", "Tsygankova", "Moscow", "lera", "1", true, roleLera, LocalDate.of(1996, 1, 8), accountsLera, "Valeria.TSYGANKOVA@raiffeisen.ru");
//        userLera.setPassword(passwordEncoder.encode(userLera.getPassword()));
//
//        List<User> users = new LinkedList<>();
//        users.add(userRoman);
//        users.add(userLena);
//        users.add(userLera);
//        users.add(userLuna);
//
//        List<Share> shares = new LinkedList<>();
//
//        Bank bank = new Bank("Raiffeisen", "1234", "2345", "5678", shares, users);
//        bankService.save(bank);
//
//        log.info("INIT END");
//    }
//}
