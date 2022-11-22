package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @PersistenceContext
    private EntityManager entityManager;
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

//    public List<Role> listByName(List<String> name) {
//        return entityManager.createQuery("select u from Role u where u.name in (:name)", Role.class)
//                .setParameter("name", name)
//                .getResultList();
//    }


    public Role getRoleByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public List<Role> listRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    public Role findByName(String name) {
        return entityManager.createQuery("select u FROM Role u WHERe u.name = :id", Role.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }

}
