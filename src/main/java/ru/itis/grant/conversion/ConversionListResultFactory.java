package ru.itis.grant.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.grant.dto.request.RequestElementValueDto;
import ru.itis.grant.dto.response.*;
import ru.itis.grant.model.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConversionListResultFactory {

    @Autowired
    ConversionResultFactory conversionResultFactory;

    public List<ResponseApplicationDto> applicationsToResponseApplicationDtos(List<Application> applications) {
        List<ResponseApplicationDto> responseApplicationDtos = new ArrayList<>();
        for (Application application : applications) {
            ResponseApplicationDto responseApplicationDto = conversionResultFactory.applicationToResponseApplicationDto(application);
            responseApplicationDtos.add(responseApplicationDto);
        }
        return responseApplicationDtos;
    }

    public List<ResponseElementDto> elementsToResponseElementDtos(List<Element> elements) {
        List<ResponseElementDto> responseElementDtos = new ArrayList<>();
        for (Element element : elements) {
            ResponseElementDto responseElementDto = conversionResultFactory.elementToResponseElementDto(element);
            responseElementDtos.add(responseElementDto);
        }
        return responseElementDtos;
    }

    public List<ResponseElementValueDto> elementValuesToResponseElementValueDtos(List<ElementValue> elementValues) {
        List<ResponseElementValueDto> responseElementValueDtos = new ArrayList<>();
        for (ElementValue elementValue : elementValues) {
            ResponseElementValueDto responseElementValueDto = conversionResultFactory
                    .elementValueToResponseElementValueDto(elementValue);
            responseElementValueDtos.add(responseElementValueDto);
        }
        return responseElementValueDtos;
    }

    public List<ResponseEventDto> eventsToResponseEventDtos(List<Event> events) {
        List<ResponseEventDto> responseEventDtos = new ArrayList<>();
        for (Event event : events) {
            ResponseEventDto responseEventDto = conversionResultFactory.eventToResponseEventDto(event);
            responseEventDtos.add(responseEventDto);
        }
        return responseEventDtos;
    }

    public List<ResponsePatternDto> patternsToResponsePatternDtos(List<Pattern> patterns) {
        List<ResponsePatternDto> responsePatternDtos = new ArrayList<>();
        for (Pattern pattern : patterns) {
            ResponsePatternDto responsePatternDto = conversionResultFactory.patternToResponsePatternDto(pattern);
            responsePatternDtos.add(responsePatternDto);
        }
        return responsePatternDtos;
    }

    public List<ResponseUserDto> usersToResponseUserDtos(List<User> users) {
        List<ResponseUserDto> responseUserDtos = new ArrayList<>();
        for (User user : users) {
            ResponseUserDto responseUserDto = conversionResultFactory.userToResponseUserDto(user);
            responseUserDtos.add(responseUserDto);
        }
        return responseUserDtos;
    }

    public List<ElementValue> requestElementValueDtosToElementValues(List<RequestElementValueDto> values) {
        List<ElementValue> elementValues = new ArrayList<>();
        for (RequestElementValueDto value : values) {
            ElementValue elementValue = conversionResultFactory.requestElementValueDtoToElementValue(value);
            elementValues.add(elementValue);
        }
        return elementValues;
    }

    public List<ResponseBanDto> bansToResponseBanDtos(List<Ban> bans) {
        List<ResponseBanDto> responseBanDtos = new ArrayList<>();
        for (Ban ban : bans) {
            ResponseBanDto responseBanDto = conversionResultFactory.banToResponseBanDto(ban);
            responseBanDtos.add(responseBanDto);
        }
        return responseBanDtos;
    }
}
