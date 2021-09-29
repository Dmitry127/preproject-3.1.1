package ru.dmitry.seleznev.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.dmitry.seleznev.model.User;
import ru.dmitry.seleznev.service.UserService;


@Component
public class PopulateDb {

    private UserService userService;

    @Autowired
    public PopulateDb(UserService userService) {
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        User user = new User("admin", "admin", "admin", "admin", "admin@mail.ru", null);
        userService.saveUser(user, "ADMIN");

        User user1 = new User("user", "user", "user", "user", "user@mail.ru", null);
        userService.saveUser(user1, "USER");
    }
}
