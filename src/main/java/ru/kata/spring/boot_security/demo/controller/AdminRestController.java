package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.exceptiom_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.exceptiom_handling.UserIncorrectData;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;


    public AdminRestController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers(){
        List<User> list = userService.getAllUsers();
        return list;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id){
        User user = userService.findUserById(id);

        if (user == null) {
            throw new NoSuchUserException("There is no user with id = " + id);
        }
        return user;
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;

    }

    @PutMapping("users")
    public User editUser(@RequestBody User user) {
        userService.changeUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
//        User user = userService.findUserById(id);
//        if (user == null) {
//            throw new NoSuchUserException("There is no employee with id = " + id);
//        }
        userService.deleteUser(id);
        return "User with id = " + id + " was deleted";
    }


}
