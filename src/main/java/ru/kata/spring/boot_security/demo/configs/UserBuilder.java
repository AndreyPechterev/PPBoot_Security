package ru.kata.spring.boot_security.demo.configs;

import org.hibernate.mapping.Collection;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class UserBuilder implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Role role_admin = new Role("ROLE_ADMIN");
//        Role role_user = new Role("ROLE_USER");
//
//        User user = new User("andrey", "$2a$12$5PZjnRHaJ/BIF6BGE3KA4ep0DGiFogdkhozNzCYJIlQDbB8HGhnLa", "pechterev", "andrey@mail.ru", 24, Collections.singletonList(role_user));
//        User admin = new User("kostya", "$2a$12$5PZjnRHaJ/BIF6BGE3KA4ep0DGiFogdkhozNzCYJIlQDbB8HGhnLa", "pechterev", "kostya@mail.ru", 24, Collections.singletonList(role_admin));
//        entityManager.persist(user);
//        entityManager.persist(admin);
    }


}
