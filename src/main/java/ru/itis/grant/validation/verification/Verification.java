package ru.itis.grant.validation.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.grant.dao.interfaces.BidDao;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.dao.interfaces.PatternDao;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.dto.request.*;
import ru.itis.grant.model.Element;
import ru.itis.grant.model.Pattern;
import ru.itis.grant.security.exception.IncorrectDataException;
import ru.itis.grant.validation.dto.ElementValueDtoValidator;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class Verification {

    @Autowired
    UserDao userDao;
    @Autowired
    BidDao bidDao;
    @Autowired
    EventDao eventDao;
    @Autowired
    PatternDao patternDao;

    public void verifyTokenExistence(String token) {
        if (!userDao.userExistenceByToken(token)) {
            throw new IncorrectDataException("token", "Неверный токен");
        }
    }

    public void verifyEmailExistence(String email) {
        if (!userDao.userExistenceByEmail(email)) {
            throw new IncorrectDataException("email", "Неверный email");
        }
    }

    public void verifyEmailUnique(String email) {
        if (userDao.userExistenceByEmail(email)) {
            throw new IncorrectDataException("email", "Пользователь с таким email уже существует");
        }
    }

    public void verifyBidExistenceById(long id) {
        if (!bidDao.bidExistenceById(id)) {
            throw new IncorrectDataException("id", "Неверный id заявки");
        }
    }

    public void verifyUserBidExistenceById(String token, long id) {
        if (!bidDao.userBidExistence(token, id)) {
            throw new IncorrectDataException("id", "Неверный id заявки");
        }
    }

    public void verifyPatternExistence(long id) {
        if (!patternDao.patternExistence(id)) {
            throw new IncorrectDataException("id", "Неверный id шаблона");
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

    public void verifyExpertBidExistence(String token, long bidId) {
        if (!bidDao.expertBidExistence(token, bidId)) {
            throw new IncorrectDataException("id", "Заявка с таким id не найдена");
        }
    }

    public void verifyExpertEventExistence(String token, long eventId) {
        if (!eventDao.expertEventExistence(token, eventId)) {
            throw new IncorrectDataException("id", "Конкурс с таким id не найден");
        }
    }

    public void verifyPatternTimeLimit(long patternId, Date date) {
        if (!patternDao.patternTimeCorrect(patternId, date)) {
            throw new IncorrectDataException("date",
                    "Заявка с таким id не существует либо некорректное время подачи заявки");
        }
    }

    public void verifyElementValueDto(RequestElementValueDto elementValueDto, Element element) {
        if (!ElementValueDtoValidator.getInstance().verify(elementValueDto, element)) {
            throw new IncorrectDataException("values", "Неверно введены значения");
        }
    }

    public void verifyBidDto(RequestBidDto bidDto, Pattern pattern) {
        next:
        for (Element element : pattern.getElements()) {
            for (RequestElementValueDto value : bidDto.getValues()) {
                if (value.getElementId() == element.getId()) {
                    verifyElementValueDto(value, element);
                    continue next;
                }
            }
            if (element.isRequired()) {
                throw new IncorrectDataException("values", "Неверно введены значения");
            }
        }
    }

    public void verifyEventDto(RequestEventDto eventDto) {
        if (null != eventDto.getName() && null != eventDto.getDescription()
                && null != eventDto.getSiteUrl()) {
        } else {
            throw new IncorrectDataException("values", "Неверно введены значения");
        }
    }

    public void verifyPatternDto(RequestPatternDto patternDto) {
        if (null != patternDto.getBidName() && null != patternDto.getDescription()
                && null != patternDto.getElements() && null != patternDto.getEndDate()
                && null != patternDto.getStartDate() && 0 != patternDto.getEventId()) {
            List<RequestElementDto> elementDtos = patternDto.getElements();
            for (RequestElementDto elementDto : elementDtos) {
                String type = elementDto.getType();
                List<String> existantTypes = Arrays.asList("TEXT", "COMBOBOX",
                        "CHECKBOX", "RADIOBUTTON", "MULTISELECT");
                if (!existantTypes.contains(type)) {
                    throw new IncorrectDataException("values", "Неверны значения массива элементов");
                } else {
                    List<String> moreThanTwo = Arrays.asList("COMBOBOX",
                            "RADIOBUTTON", "MULTISELECT");
                    if (moreThanTwo.contains(type)) {
                        if (null == elementDto.getSelectableValue() || elementDto.getSelectableValue().length < 2) {
                            throw new IncorrectDataException("values", "Неверны значения массива элементов");
                        }
                    }
                }
            }
        } else {
            throw new IncorrectDataException("values", "Неверно введены значения");
        }
    }

    public void verifyOrganizerEventExistence(long eventId, String token) {
        if (!eventDao.organizerEventExistence(eventId, token)) {
            throw new IncorrectDataException("id", "Пользователь не является собственником события");
        }
    }

    public void verifyUserIdExistence(long id) {
        if (!userDao.userExistenceById(id)) {
            throw new IncorrectDataException("id", "Неверный идентификатор");
        }
    }

    public void verifyEventExpertExistence(long eventId, long expertId) {
        if (!eventDao.eventExpertExistence(eventId, expertId)) {
            throw new IncorrectDataException("id", "Эксперт не принадлежит событию");
        }
    }


}
