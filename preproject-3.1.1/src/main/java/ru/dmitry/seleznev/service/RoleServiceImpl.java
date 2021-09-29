package ru.dmitry.seleznev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitry.seleznev.dao.RoleDAO;
import ru.dmitry.seleznev.model.Role;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRole(String role) {
        return roleDAO.getRole(role);
    }

    @Override
    @Transactional
    public Set<Role> getRoleSet(String role) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(getRole("USER"));
        if (role.equals("ADMIN")) {
            roleSet.add(getRole("ADMIN"));
        }
        return roleSet;
    }
}
