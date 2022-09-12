package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialDataRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) {

        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleService.save(role1);
        roleService.save(role2);

        List<Role> u1roles = new ArrayList<>();
        u1roles.add(role1);
        List<Role> u2roles = new ArrayList<>();
        u2roles.add(role2);

        User user1 = new User("admin", "admin@mail.ru", 20, "admin", u1roles);
        User user2 = new User("user", "user@mail.ru", 30, "user", u2roles);

        userService.save(user1);
        userService.save(user2);
    }
}