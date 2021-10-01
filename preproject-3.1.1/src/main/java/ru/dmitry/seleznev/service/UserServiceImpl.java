package ru.dmitry.seleznev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitry.seleznev.dao.UserDAO;
import ru.dmitry.seleznev.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAO userDAO;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void saveUser(User user, String adminRole) {
        user.setRoles(roleService.getRoleSet(adminRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Override
    public User getUser(long id) {
        return userDAO.getUser(id);
    }

    @Override
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void updateUser(User user, String adminRole) {
        User persistentUser = getUser(user.getLogin());
        persistentUser.setName(user.getName());
        persistentUser.setLastName(user.getLastName());
        persistentUser.setEmail(user.getEmail());
        persistentUser.setRoles(roleService.getRoleSet(adminRole));
        if (!user.getPassword().equals(persistentUser.getPassword())) {
            persistentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(String login) {
        userDAO.deleteUser(login);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.getUser(s);
    }

}
