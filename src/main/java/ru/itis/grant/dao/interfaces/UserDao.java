package ru.itis.grant.dao.interfaces;

import ru.itis.grant.model.Ban;
import ru.itis.grant.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void deleteUser(User user);

    void deleteUser(long id);

    User updateUser(User user);

    User getUserById(long id);

    User getUserByToken(String token);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    List<User> getAllUsersFromCount(int from, int count);

    boolean userExistenceByToken(String token);

    boolean userExistenceByEmail(String email);

    boolean userExistenceById(long id);

    User getUserByApplicationId(long applicationId);

    Ban banUser(Ban ban);

    void unbanUser(Ban ban);

    Ban getBanById(long id);

    List<Ban> getBans(String token, long from, long count);

    boolean expertBanExistence(String token, long banId);
}
