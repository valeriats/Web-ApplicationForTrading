package com.teamc.service;

import com.teamc.model.Account;
import com.teamc.model.User;

import java.time.Month;
import java.util.List;

public interface UserService {

    List<User> findAll();

    void save(User user);

    User getUser(String login);

    User findByName(String name);

    User findByUsername(String username);

    User getAuthUser();

    String userRegistration(User user);

    void userUpdate(String username, User user, String role);

    void userChangeAcc(User user, Account diffAccount);

    void deleteUser(String username);

    void setPrizStatusFalse();

    void setPrizStatusTrue();

}
