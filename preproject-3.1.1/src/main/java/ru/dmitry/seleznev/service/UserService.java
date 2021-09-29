package ru.dmitry.seleznev.service;

import ru.dmitry.seleznev.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user, String adminRole);

    User getUser(long id);

    User getUser(String login);

    List<User> getAllUsers();

    void updateUser(User user, String adminRole);

    void deleteUser(long id);

    void deleteUser(String login);

}
