package ru.dmitry.seleznev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dmitry.seleznev.dao.UserDAO;
import ru.dmitry.seleznev.model.Role;
import ru.dmitry.seleznev.model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements  UserService, UserDetailsService {

    private final UserDAO userDAO;

    @PostConstruct
    private void adminCreate() {
        Set<Role> roles = new HashSet<>();
        roles.add(getRole("ADMIN"));
        roles.add(getRole("USER"));
        User user = new User("admin", "admin", "admin", "admin", "admin@mail.ru", roles);
        userDAO.saveUser(user);

        Set<Role> rolesuser = new HashSet<>();
        rolesuser.add(getRole("USER"));
        User user1 = new User("user", "user", "user", "user","user@mail.ru", rolesuser);
        userDAO.saveUser(user1);
    }

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void saveUser(User user) {
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
    public Role getRole(String role) {
        return userDAO.getRole(role);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void deleteUser(String login) {
        userDAO.deleteUser(login);
    }

    @Override
    public Set<Role> getRoleSet(String role) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(getRole("USER"));
        if (role.equals("ADMIN")) {
            roleSet.add(getRole("ADMIN"));
        }
        return roleSet;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.getUser(s);
    }

}
