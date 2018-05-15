package ru.itis.grant.conversion;

import org.springframework.stereotype.Component;
import ru.itis.grant.conversion.request.*;
import ru.itis.grant.conversion.response.*;
import ru.itis.grant.dto.request.*;
import ru.itis.grant.dto.response.*;
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

    public ResponseUserDto userToResponseUserDto(User user) {
        ResponseUserDto responseUserDto = UserToResponseUserDtoConverter.getInstance().convert(user);
        return responseUserDto;
    }

    public ResponsePatternDto patternToResponsePatternDto(Pattern pattern) {
        ResponsePatternDto responsePatternDto = PatternToResponsePatternDtoConverter.getInstance().convert(pattern);
        return responsePatternDto;
    }

    public ResponseEventDto eventToResponseEventDto(Event event) {
        ResponseEventDto responseEventDto = EventToResponseEventDtoConverter.getInstance().convert(event);
        return responseEventDto;
    }

    public ResponseElementValueDto elementValueToResponseElementValueDto(ElementValue elementValue) {
        ResponseElementValueDto responseElementValueDto = ElementValueToResponseElementValueDtoConverter.getInstance().convert(elementValue);
        return responseElementValueDto;
    }

    public ResponseElementDto elementToResponseElementDto(Element element) {
        ResponseElementDto responseElementDto = ElementToResponseElementDtoConverter.getInstance().convert(element);
        return responseElementDto;
    }

    public ResponseBidDto bidToResponseBidDto(Bid bid) {
        ResponseBidDto responseBidDto = BidToResponseBidDtoConverter.getInstance().convert(bid);
        return responseBidDto;
    }

    public ResponseBanDto banToResponseBanDto(Ban ban) {
        ResponseBanDto responseBanDto = BanToResponseBanDtoConverter.getInstance().convert(ban);
        return responseBanDto;
    }
}
