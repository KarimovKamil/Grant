package ru.itis.grant.validation.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.grant.dao.interfaces.BidDao;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.security.exception.IncorrectDataException;

@Component
public class Verification {

    @Autowired
    UserDao userDao;
    @Autowired
    BidDao bidDao;
    @Autowired
    EventDao eventDao;

    public void verifyTokenExistence(String token) {
        if (!userDao.userExistenceByToken(token)) {
            throw new IncorrectDataException("token", "Неверный токен");
        }
    }

    public void verifyBidExistenceById(long id) {
        if (!bidDao.bidExistenceById(id)) {
            throw new IncorrectDataException("id", "Неверный id заявки");
        }
    }

    public void verifyEventExistenceById(long id) {
        if (!eventDao.eventExistenceById(id)) {
            throw new IncorrectDataException("id", "Неверный id конкурса");
        }
    }

    public void verifyEventPatternExistence(long eventId) {
        if (!eventDao.verifyEventPatternExistence(eventId)) {
            throw new IncorrectDataException("pattern",
                    "Конкурс не существует или у конкурса отсутствует шаблон для заявок");
        }
    }

    public void verifyUserBidExistence(String token, long bidId) {
        if (!bidDao.userBidExistence(token, bidId)) {
            throw new IncorrectDataException("id", "Заявка с таким id не найдена");
        }
    }
}
