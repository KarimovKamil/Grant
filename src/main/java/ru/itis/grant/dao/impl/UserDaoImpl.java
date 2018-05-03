package ru.itis.grant.dao.impl;

import org.springframework.stereotype.Repository;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }

    @Override
    public void deleteUser(long id) {
        em.createQuery("delete from User u where u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public User updateUser(User user) {
        return em.merge(user);
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByToken(String token) {
        User user = (User) em.createQuery("from User u where u.token = :token")
                .setParameter("token", token)
                .getSingleResult();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = em.createQuery("from User")
                .getResultList();
        return users;
    }

    @Override
    public boolean userExistenceByToken(String token) {
        return !em.createQuery("SELECT u.id FROM User u WHERE u.token = :token")
                .setParameter("token", token)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }
}
