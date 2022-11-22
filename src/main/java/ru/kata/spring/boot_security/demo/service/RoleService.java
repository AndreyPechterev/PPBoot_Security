package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleService {
    public Role getRoleByName(String name);
    List<Role> listRoles();

//    List<Role> listByName(List<String> name);

    Role findByName(String name);
}
