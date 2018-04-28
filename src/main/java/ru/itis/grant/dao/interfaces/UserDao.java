package ru.itis.grant.dao.interfaces;

import ru.itis.grant.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void deleteUser(User user);

    void deleteUser(long id);

    User updateUser(User user);

    User getUser(long id);

    List<User> getAllUsers();
}