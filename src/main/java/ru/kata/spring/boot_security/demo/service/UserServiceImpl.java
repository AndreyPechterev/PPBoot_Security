package ru.kata.spring.boot_security.demo.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found",username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList());
    }

    public  User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDao.findByUsername(auth.getName());
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public void saveUser(User user) {
//        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }



    public void changeUser(Long id, User user) {
        User newUser = userDao.findById(id).get();
        newUser.setAge(user.getAge());
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setSurname(user.getSurname());
        newUser.setRoles(user.getRoles());
        if(!newUser.getPassword().equals(user.getPassword())) {
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            newUser.setPassword(user.getPassword());
        }

    }


    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    public User getUserById(Long id) {
        return userDao.getById(id);
    }
}
