package ru.itis.grant.conversion;

import org.springframework.stereotype.Component;
import ru.itis.grant.conversion.request.*;
import ru.itis.grant.dto.request.*;
import ru.itis.grant.model.*;

@Component
public class ConversionResultFactory {
    public User requestUserDtoToUser(String token, String hashPassword, RequestUserDto requestUserDto) {
        User user = RequestUserDtoToUserConverter.getInstance().convert(token, hashPassword, requestUserDto);
        return user;
    }

    public Pattern requestPatternDtoToPattern(RequestPatternDto requestPatternDto) {
        Pattern pattern = RequestPatternDtoToPatternConverter.getInstance().convert(requestPatternDto);
        return pattern;
    }

    public Event requestEventDtoToEvent(RequestEventDto requestEventDto) {
        Event event = RequestEventDtoToEventConverter.getInstance().convert(requestEventDto);
        return event;
    }

    public ElementValue requestElementValueDtoToElementValue(RequestElementValueDto requestElementValueDto) {
        ElementValue elementValue = RequestElementValueDtoToElementValueConverter.getInstance()
                .convert(requestElementValueDto);
        return elementValue;
    }

    public Element requestElementDtoToElement(RequestElementDto requestElementDto) {
        Element element = RequestElementDtoToElementConverter.getInstance().convert(requestElementDto);
        return element;
    }

    public Bid requestBidDtoToBid(RequestBidDto requestBidDto) {
        Bid bid = RequestBidDtoToBidConverter.getInstance().convert(requestBidDto);
        return bid;
    }
}
