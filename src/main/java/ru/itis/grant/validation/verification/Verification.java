package ru.itis.grant.validation.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.grant.dao.interfaces.BidDao;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.security.exception.IncorrectDataException;

@Component
public class Verification {

    @Autowired
    UserDao userDao;
    @Autowired
    BidDao bidDao;

    public void verifyTokenExistence(String token) {
        if (!userDao.userExistenceByToken(token)) {
            throw new IncorrectDataException("token", "Incorrect data");
        }
    }

    public void verifyBidExistenceById(long id) {
        if (!bidDao.bidExistenceById(id)) {
            throw new IncorrectDataException("id", "Incorrect bid id");
        }
    }
}
