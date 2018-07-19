package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.response.ResponseApplicationDto;
import ru.itis.grant.dto.response.ResponseEventDto;

import java.util.List;

public interface ExpertService {

    List<ResponseEventDto> getExpertEvents(String token, int from, int count);

    List<ResponseApplicationDto> getExpertApplications(String token, int from, int count);

    List<ResponseApplicationDto> getExpertEventApplications(String token, long eventId, int from, int count);

    ResponseApplicationDto getExpertApplication(String token, long applicationId);

    ResponseApplicationDto validate(String token, long applicationId, ValidateDto validateDto);

//    ResponseBanDto banUser(String token, long applicationId, String comment);
//
//    void unbanUser(String token, long userId);
//
//    List<ResponseBanDto> getBans(String token, long from, long count);
}
