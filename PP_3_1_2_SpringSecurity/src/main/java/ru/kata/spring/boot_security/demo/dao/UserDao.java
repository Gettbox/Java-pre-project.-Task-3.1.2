package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    void update(User updatedUser);

    User getUserById(Integer id);

    void delete(int id);

    List<User> getDemandedUsers();

    User findByUsername(String username);

    void setEncryptedPassword(User user);

    User setRolesToUser(User user, int[] rolesIdArr);
}