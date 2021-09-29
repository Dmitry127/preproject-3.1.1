package ru.dmitry.seleznev.dao;

import ru.dmitry.seleznev.model.Role;

public interface RoleDAO {

    void saveRole(Role role);

    Role getRole(String role);

    void deleteRole(String role);
}
