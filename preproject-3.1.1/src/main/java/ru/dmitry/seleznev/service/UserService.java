package ru.dmitry.seleznev.service;

import ru.dmitry.seleznev.model.Role;
import ru.dmitry.seleznev.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void saveUser(User user);

    User getUser(long id);

    User getUser(String login);

    Role getRole(String role);

    List<User> getAllUsers();

    void updateUser(User user, String adminRole);

    void deleteUser(long id);

    Set<Role> getRoleSet(String role);

    void deleteUser(String login);

}
