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
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserById(Long id){
        return userDao.findById(id).get();
    }


    public User findByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList());
    }

    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDao.getUserByUsername(auth.getName());
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    public void saveUser(User user) {
        User newUser = userDao.getUserByUsername(user.getUsername());
        if (newUser == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
        } else {
        }
        
    }


//    public void changeUser(Long id, User user) {
        @Transactional
        public void changeUser( User user) {
//        User newUser = userDao.getUserByUsername(user.getUsername());
//        newUser.setAge(user.getAge());
//        newUser.setEmail(user.getEmail());
//        newUser.setUsername(user.getUsername());
//        newUser.setSurname(user.getSurname());
//        newUser.setRoles(user.getRoles());
//        if (!newUser.getPassword().equals(user.getPassword())) {
//            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//        } else {
//            newUser.setPassword(user.getPassword());
//        }
            User user2 = userDao.getById(user.getId());
            if(!user2.getPassword().equals(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userDao.saveAndFlush(user);
        }

    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

}
