package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {

    User findByUsername(String username);
    User getUserById(Long id);

    void deleteUser(Long id);

    void changeUser(Long id, User user);

    void saveUser(User user);
    List<User> getAllUsers();
    User getAuthUser();

    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles);




}
