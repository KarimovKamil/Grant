package ru.itis.grant.validation.dto;

import ru.itis.grant.dto.request.RequestElementValueDto;
import ru.itis.grant.model.Element;

import java.util.Objects;

public class ElementValueDtoValidator {
    private static volatile ElementValueDtoValidator instance;

    public static ElementValueDtoValidator getInstance() {
        ElementValueDtoValidator localInstance = instance;
        if (localInstance == null) {
            synchronized (ElementValueDtoValidator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ElementValueDtoValidator();
                }
            }
        }
        return localInstance;
    }

    public boolean verify(RequestElementValueDto elementValueDto, Element element) {
        if (Objects.isNull(elementValueDto.getFilledValue())) {
            return false;
        } else {
            switch (element.getType()) {
                case "TEXT":
                    return true;
                case "COMBOBOX":
                    return verifyRadiobutton(elementValueDto, element);
                case "CHECKBOX":
                    return verifyCheckbox(elementValueDto);
                case "RADIOBUTTON":
                    return verifyRadiobutton(elementValueDto, element);
                case "MULTISELECT":
                    return verifyMultiSelect(elementValueDto, element);
                default:
                    return false;
            }
        }
    }

    private boolean verifyRadiobutton(RequestElementValueDto elementValueDto, Element element) {
        String valueRB = elementValueDto.getFilledValue();
        for (String s : element.getSelectableValue()) {
            if (s.intern() == valueRB.intern()) {
                return true;
            }
        }
        return false;
    }

    private boolean verifyMultiSelect(RequestElementValueDto elementValueDto, Element element) {
        String[] values = elementValueDto.getFilledValue().split(", ");
        next:
        for (String value : values) {
            for (String s : element.getSelectableValue()) {
                if (s.intern() == value.intern()) {
                    continue next;
                }
                return false;
            }
        }
        return true;
    }

    private boolean verifyCheckbox(RequestElementValueDto elementValueDto) {
        String valueCB = elementValueDto.getFilledValue().toLowerCase().intern();
        return "true" == valueCB || "false" == valueCB;
    }
}
