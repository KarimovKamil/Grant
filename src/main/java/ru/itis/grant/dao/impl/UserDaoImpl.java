package ru.itis.grant.dao.impl;

import org.springframework.stereotype.Repository;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.model.Ban;
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
    public User getUserByEmail(String email) {
        User user = (User) em.createQuery("from User u where u.email = :email")
                .setParameter("email", email)
                .getSingleResult();
        return user;
    }

    @Override
    public List<User> getAllUsersFromCount(int from, int count) {
        List<User> users = em.createQuery("from User")
                .setFirstResult(from)
                .setMaxResults(count)
                .getResultList();
        return users;
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

    @Override
    public boolean userExistenceByEmail(String email) {
        return !em.createQuery("SELECT u.id FROM User u WHERE u.email = :email")
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public boolean userExistenceById(long id) {
        return !em.createQuery("SELECT u.id FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public User getUserByBidId(long bidId) {
        User user = (User) em.createNativeQuery("SELECT u.* FROM g_user u " +
                "INNER JOIN (SELECT * FROM bid WHERE id = :bidId) b ON b.user_id = u.id", User.class)
                .setParameter("bidId", bidId)
                .getSingleResult();
        return user;
    }

    @Override
    public Ban banUser(Ban ban) {
        em.persist(ban);
        return ban;
    }

    @Override
    public void unbanUser(Ban ban) {
        em.remove(ban);
    }

    @Override
    public Ban getBanById(long id) {
        return em.find(Ban.class, id);
    }

    @Override
    public List<Ban> getBans(String token, long from, long count) {
        List<Ban> bans = em.createQuery("from Ban b where b.expert.token = :token")
                .setParameter("token", token)
                .setFirstResult((int) from)
                .setMaxResults((int) count)
                .getResultList();
        return bans;
    }

    @Override
    public boolean expertBanExistence(String token, long banId) {
        return !em.createQuery("select b.id from Ban b where b.id = :banId and b.expert.token = :token")
                .setParameter("banId", banId)
                .setParameter("token", token)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }
}
