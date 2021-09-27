package ru.dmitry.seleznev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.dmitry.seleznev.model.Role;
import ru.dmitry.seleznev.model.User;
import ru.dmitry.seleznev.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class PopulateDb {

    private UserService userService;

    @Autowired
    public PopulateDb(UserService userService) {
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        Set<Role> roles = new HashSet<>();
        roles.add(userService.getRole("ADMIN"));
        roles.add(userService.getRole("USER"));
        User user = new User("admin", "admin", "admin", "admin", "admin@mail.ru", roles);
        userService.saveUser(user);

        Set<Role> rolesUser = new HashSet<>();
        rolesUser.add(userService.getRole("USER"));
        User user1 = new User("user", "user", "user", "user","user@mail.ru", rolesUser);
        userService.saveUser(user1);
    }
}
