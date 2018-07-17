package ru.itis.grant.validation.dto;

import ru.itis.grant.dto.request.RequestEventDto;
import ru.itis.grant.security.exception.IncorrectDataException;

import java.util.Date;
import java.util.Objects;

public class EventDtoValidator {
    private static volatile EventDtoValidator instance;

    public static EventDtoValidator getInstance() {
        EventDtoValidator localInstance = instance;
        if (localInstance == null) {
            synchronized (EventDtoValidator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EventDtoValidator();
                }
            }
        }
        return localInstance;
    }

    public void verify(RequestEventDto eventDto) {
        if (Objects.nonNull(eventDto.getName()) || Objects.nonNull(eventDto.getDescription())) {
            throw new IncorrectDataException("values", "Неверно введены значения");
        }
        if (Objects.nonNull(eventDto.getEndDate()) || Objects.nonNull(eventDto.getStartDate())
                || !eventDto.getEndDate().after(eventDto.getStartDate()) || !eventDto.getEndDate().after(new Date())) {
            throw new IncorrectDataException("dates", "Неверно введены даты начала и окончания подачи заявок");
        }
    }
}
