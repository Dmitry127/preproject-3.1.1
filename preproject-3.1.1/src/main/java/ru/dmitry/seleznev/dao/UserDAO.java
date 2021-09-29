package ru.dmitry.seleznev.dao;

import ru.dmitry.seleznev.model.User;

import java.util.List;

public interface UserDAO {

    void saveUser(User user);

    User getUser(long id);

    User getUser(String login);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(long id);

    void deleteUser(String login);
}
