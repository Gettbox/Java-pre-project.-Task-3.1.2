package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private RoleService roleService;

    public User getUserById(Integer id) {
        return entityManager.find(User.class, id);
    }

    public void save(User user) {
        setEncryptedPassword(user);
        entityManager.persist(user);
    }

    public void update(User updatedUser) {
        if (!updatedUser.getPassword().equals(getUserById(updatedUser.getId()).getPassword())) {
            setEncryptedPassword(updatedUser);
        }
        entityManager.merge(updatedUser);
    }

    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public List<User> getDemandedUsers() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    public User findByUsername(String username) {
        User user = null;
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT user FROM User user WHERE user.username = :userRequest", User.class);
            user = query.setParameter("userRequest", username).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            System.out.printf("User '%s' not found%n", username);
        }
        return user;
    }

    @Override
    public void setEncryptedPassword(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public User setRolesToUser(User user, int[] rolesIdArr) {
        List<Role> userRoles = new ArrayList<>();
        if (rolesIdArr != null) {
            for (int i : rolesIdArr) {
                userRoles.add(roleService.getRoleById(i));
            }
        }
        user.setRoles(userRoles);
        return user;
    }
}