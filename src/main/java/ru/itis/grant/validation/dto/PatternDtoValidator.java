package ru.itis.grant.validation.dto;

import ru.itis.grant.dto.request.RequestElementDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.security.exception.IncorrectDataException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PatternDtoValidator {
    private static volatile PatternDtoValidator instance;

    public static PatternDtoValidator getInstance() {
        PatternDtoValidator localInstance = instance;
        if (localInstance == null) {
            synchronized (PatternDtoValidator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PatternDtoValidator();
                }
            }
        }
        return localInstance;
    }

    public void verify(RequestPatternDto patternDto) {
        if (null != patternDto.getApplicationName() && null != patternDto.getElements()
                && patternDto.getElements().size() > 0) {
            for (RequestElementDto elementDto : patternDto.getElements()) {
                String type = elementDto.getType();
                List<String> existantTypes = Arrays.asList("TEXT", "COMBOBOX",
                        "CHECKBOX", "RADIOBUTTON");
                if (!existantTypes.contains(type)) {
                    throw new IncorrectDataException("values", "Неверно введены типы элементов");
                } else {
                    List<String> moreThanTwo = Arrays.asList("COMBOBOX",
                            "RADIOBUTTON");
                    if (moreThanTwo.contains(type)) {
                        if (null == elementDto.getSelectableValue() || elementDto.getSelectableValue().length < 2) {
                            throw new IncorrectDataException("values", "Неверно введены значения массива элементов");
                        }
                    }
                    if (type.intern() == "CHECKBOX" && Objects.nonNull(elementDto.getSelectableValue())
                            && elementDto.getSelectableValue().length < 1) {
                        throw new IncorrectDataException("values", "Неверно введены значения массива элементов");
                    }
                }
            }
        } else {
            throw new IncorrectDataException("values", "Неверно введены значения");
        }
    }
}
