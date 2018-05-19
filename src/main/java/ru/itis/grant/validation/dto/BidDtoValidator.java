package ru.itis.grant.validation.dto;

import ru.itis.grant.dto.request.RequestBidDto;
import ru.itis.grant.dto.request.RequestElementValueDto;
import ru.itis.grant.model.Element;
import ru.itis.grant.model.Pattern;

public class BidDtoValidator {
    private static volatile BidDtoValidator instance;

    public static BidDtoValidator getInstance() {
        BidDtoValidator localInstance = instance;
        if (localInstance == null) {
            synchronized (BidDtoValidator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BidDtoValidator();
                }
            }
        }
        return localInstance;
    }

    public boolean verify(RequestBidDto bidDto, Pattern pattern) {
        next:
        for (Element element : pattern.getElements()) {
            for (RequestElementValueDto value : bidDto.getValues()) {
                if (value.getElementId() == element.getId()) {
                    if (!ElementValueDtoValidator.getInstance().verify(value, element)) {
                        return false;
                    }
                    continue next;
                }
            }
            if (element.isRequired()) {
                return false;
            }
        }
        return true;
    }
}
