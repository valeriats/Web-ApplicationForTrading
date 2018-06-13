package com.teamc.service;

import com.teamc.model.Account;
import com.teamc.model.Role;
import com.teamc.model.User;
import com.teamc.repository.RoleRepository;
import com.teamc.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@NoArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


    @Override
    public User getUser(String login) {
        return userRepository.findByUsername(login);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByFirstName(name);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return userRepository.findByUsername(name);
    }

    @Override
    @Transactional
    public String userRegistration(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return "registration";
        }

        user.setActive(true);

        Account acc = new Account("USD", user.getFirstAcc());
        List<Account> list = new ArrayList<>();
        list.add(acc);
        user.setAccountList(list);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roleList = new HashSet<>();
        roleList.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roleList);
        userRepository.save(user);

        return "redirect:/login";
    }

    public void userUpdate(String username, User user, String roleName) {
        Role roleDb = roleRepository.findByName(roleName);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleDb);
        User entity = userRepository.findByUsername(username);
        System.out.println(entity);
        entity.setFirstName(user.getFirstName());
        entity.setSecondName(user.getSecondName());
        entity.setAddress(user.getAddress());
        entity.setBirthday(user.getBirthday());
        entity.setFirstAcc(user.getFirstAcc());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setRoles(roleSet);
        userRepository.save(entity);

        log.info(user.getUsername() + " - update user");
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }

    @Override
    public void userChangeAcc(User user, Account diffAccount) {

        Integer ind = 0;
        boolean flag = false;

        for (Account userAccount : user.getAccountList()) {
            if (userAccount.getName().equals(diffAccount.getName())) {
                userAccount.setAmount(userAccount.getAmount() + diffAccount.getAmount());
                user.getAccountList().set(ind, userAccount);
                flag = true;
                break;
            }
            ind++;
        }
        if (!flag) {
            user.getAccountList().add(diffAccount);
        }

        userRepository.save(user);
    }


    public void setPrizStatusTrue() {
        userRepository.setStatusTrue();
    }

    @Override
    public void setPrizStatusFalse() {
        userRepository.setStatusFalse();
    }

}

