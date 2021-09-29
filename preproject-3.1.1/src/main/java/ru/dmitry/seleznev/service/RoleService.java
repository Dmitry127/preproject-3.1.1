package ru.dmitry.seleznev.service;

import ru.dmitry.seleznev.model.Role;

import java.util.Set;

public interface RoleService {

    Role getRole(String role);

    Set<Role> getRoleSet(String role);
}
