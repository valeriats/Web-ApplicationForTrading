package com.teamc.service;

import com.teamc.model.Account;
import com.teamc.model.Role;
import com.teamc.model.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private User testUser;
    private Role roleUser;
    private Role roleAdmin;
    private Set<Role> testUserRole;

    @Before
    public void init() {
        roleUser = new Role("ROLE_USER");
        roleAdmin = new Role("ROLE_ADMIN");
        //roleService.save(roleUser);
        //roleService.save(roleAdmin);

        testUserRole = new HashSet<>();
        testUserRole.add(roleAdmin);
        List<Account> accountsUserTest = new LinkedList<>();
        accountsUserTest.add(new Account("USD", 10000.00));
        testUser = new User("Roman", "Polovintsev", "Moscow", "roma", "1", true, testUserRole, LocalDate.of(1992, 5, 26), null, "-");

        //userService.save(testUser);
    }

    @After
    public void tearDown() {
        userService = null;
        roleUser = null;
    }

    @Test
    public void readByName() {
        User actualUser = userService.findByName("Roman");
        System.out.println(actualUser);
        User expectedUser = testUser;
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void userUpdate() {
        User newUser = new User("Roman2", "Polovintsev2", "Moscow1", "roma", "1", true, testUserRole, LocalDate.of(1992, 5, 26), null, "-");
        userService.userUpdate("roma", newUser, "ROLE_ADMIN");
        User expectedUser = userService.findByUsername("roma");
        assertEquals(newUser, expectedUser);
    }
}