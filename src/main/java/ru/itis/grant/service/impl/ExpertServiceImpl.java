package ru.itis.grant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.conversion.ConversionListResultFactory;
import ru.itis.grant.conversion.ConversionResultFactory;
import ru.itis.grant.dao.interfaces.ApplicationDao;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.response.ResponseBanDto;
import ru.itis.grant.dto.response.ResponseApplicationDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.model.Ban;
import ru.itis.grant.model.Application;
import ru.itis.grant.model.Event;
import ru.itis.grant.model.User;
import ru.itis.grant.service.interfaces.ExpertService;
import ru.itis.grant.validation.verification.Verification;

import java.util.List;

@Transactional
@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    UserDao userDao;
    @Autowired
    ApplicationDao applicationDao;
    @Autowired
    EventDao eventDao;
    @Autowired
    ConversionResultFactory conversionResultFactory;
    @Autowired
    ConversionListResultFactory conversionListResultFactory;
    @Autowired
    Verification verification;

    @Override
    public List<ResponseEventDto> getExpertEvents(String token, int from, int count) {
        verification.verifyTokenExistence(token);
        verification.verifyFromCount(from, count);
        List<Event> events = eventDao.getExpertEvents(token, from, count);
        List<ResponseEventDto> responseEventDtos = conversionListResultFactory.eventsToResponseEventDtos(events);
        return responseEventDtos;
    }

    @Override
    public List<ResponseApplicationDto> getExpertApplications(String token, int from, int count) {
        verification.verifyTokenExistence(token);
        verification.verifyFromCount(from, count);
        List<Application> applications = applicationDao.getExpertApplications(token, from, count);
        List<ResponseApplicationDto> responseApplicationDtos = conversionListResultFactory.applicationsToResponseApplicationDtos(applications);
        return responseApplicationDtos;
    }

    @Override
    public List<ResponseApplicationDto> getExpertEventApplications(String token, long eventId, int from, int count) {
        verification.verifyTokenExistence(token);
        verification.verifyFromCount(from, count);
        verification.verifyExpertEventExistence(token, eventId);
        List<Application> applications = applicationDao.getExpertEventApplications(token, eventId, from, count);
        List<ResponseApplicationDto> responseApplicationDtos = conversionListResultFactory.applicationsToResponseApplicationDtos(applications);
        return responseApplicationDtos;
    }

    @Override
    public ResponseApplicationDto getExpertApplication(String token, long applicationId) {
        verification.verifyTokenExistence(token);
        verification.verifyExpertApplicationExistence(token, applicationId);
        Application application = applicationDao.getApplicationById(applicationId);
        ResponseApplicationDto responseApplicationDto = conversionResultFactory.applicationToResponseApplicationDto(application);
        return responseApplicationDto;
    }

    @Override
    public ResponseApplicationDto validate(String token, long applicationId, ValidateDto validateDto) {
        verification.verifyTokenExistence(token);
        verification.verifyExpertApplicationExistence(token, applicationId);
        Application application = applicationDao.getApplicationById(applicationId);
        application.setStatus(validateDto.getStatus());
        application.setComment(validateDto.getComment());
        Application updatedApplication = applicationDao.updateApplication(application);
        ResponseApplicationDto responseApplicationDto = conversionResultFactory.applicationToResponseApplicationDto(updatedApplication);
        return responseApplicationDto;
    }

//    @Override
//    public ResponseBanDto banUser(String token, long applicationId, String comment) {
//        verification.verifyTokenExistence(token);
//        verification.verifyExpertApplicationExistence(token, applicationId);
//        Event event = eventDao.getEventByApplicationId(applicationId);
//        User expert = userDao.getUserByToken(token);
//        User user = userDao.getUserByApplicationId(applicationId);
//        Ban ban = Ban.builder()
//                .event(event)
//                .expert(expert)
//                .user(user)
//                .comment(comment)
//                .build();
//        Ban addedBan = userDao.banUser(ban);
//        ResponseBanDto responseBanDto = conversionResultFactory.banToResponseBanDto(addedBan);
//        Application application = applicationDao.getApplicationById(applicationId);
//        application.setStatus("BANNED");
//        applicationDao.updateApplication(application);
//        return responseBanDto;
//    }

//    @Override
//    public void unbanUser(String token, long banId) {
//        verification.verifyTokenExistence(token);
//        verification.verifyExpertBanExistence(token, banId);
//        Ban ban = userDao.getBanById(banId);
//        Application application = applicationDao.getApplicationByEventUser(ban.getEvent().getId(), ban.getUser().getId());
//        application.setStatus("ACTIVE");
//        applicationDao.updateApplication(application);
//        userDao.unbanUser(ban);
//    }

//    @Override
//    public List<ResponseBanDto> getBans(String token, int from, int count) {
//        verification.verifyTokenExistence(token);
//        List<Ban> bans = userDao.getBans(token, from, count);
//        List<ResponseBanDto> responseBanDtos = conversionListResultFactory.bansToResponseBanDtos(bans);
//        return responseBanDtos;
//    }
}
