package ru.itis.grant.validation.dto;

import ru.itis.grant.dto.request.RequestApplicationDto;
import ru.itis.grant.dto.request.RequestElementValueDto;
import ru.itis.grant.model.Element;
import ru.itis.grant.model.Pattern;
import ru.itis.grant.security.exception.IncorrectDataException;

public class ApplicationDtoValidator {
    private static volatile ApplicationDtoValidator instance;

    public static ApplicationDtoValidator getInstance() {
        ApplicationDtoValidator localInstance = instance;
        if (localInstance == null) {
            synchronized (ApplicationDtoValidator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ApplicationDtoValidator();
                }
            }
        }
        return localInstance;
    }

    public void verify(RequestApplicationDto applicationDto, Pattern pattern) {
        next:
        for (Element element : pattern.getElements()) {
            for (RequestElementValueDto value : applicationDto.getValues()) {
                if (value.getElementId() == element.getId()) {
                    if (!ElementValueDtoValidator.getInstance().verify(value, element)) {
                        throw new IncorrectDataException(element.getName(), "Неверно введено значение");
                    }
                    continue next;
                }
            }
            if (element.isRequired() && element.getType().intern() != "CHECKBOX") {
                throw new IncorrectDataException("values", "Обязательные поля не заполнены");
            }
        }
    }
}
