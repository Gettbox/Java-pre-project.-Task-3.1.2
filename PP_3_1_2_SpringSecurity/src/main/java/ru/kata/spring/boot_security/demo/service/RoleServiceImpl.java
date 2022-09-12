package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    @Transactional
    public void update(Role updatedRole) {
        roleDao.update(updatedRole);
    }

    @Override
    @Transactional
    public Role getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        roleDao.delete(id);
    }

    @Override
    @Transactional
    public List<Role> getDemandedRoles() {
        return roleDao.getDemandedRoles();
    }
}