package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class UserInitialization {

    private final UserServiceImpl userServiceImpl;


    public UserInitialization(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostConstruct
    public void init() {
        Role role_admin = new Role("ROLE_ADMIN");
        Role role_user = new Role("ROLE_USER");

        User user = new User("andrey", "100", "pechterev", "andrey@mail.ru", 24, Collections.singletonList(role_user));
        User admin = new User("kostya", "100", "pechterev", "kostya@mail.ru", 24, Collections.singletonList(role_admin));
        userServiceImpl.saveUser(user);
        userServiceImpl.saveUser(admin);
    }
}
