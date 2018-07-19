package ru.itis.grant.dao.interfaces;

import ru.itis.grant.model.Application;

import java.util.List;

public interface ApplicationDao {

    void addApplication(Application application);

    void deleteApplication(long id);

    void deleteApplication(Application application);

    Application updateApplication(Application application);

    Application getApplicationById(long id);

    Application getApplicationByEventId(String token, long eventId);

    List<Application> getUserApplications(long userId);

    List<Application> getUserApplications(String token);

    List<Application> getUserApplications(String token, int from, int count);

    List<Application> getEventApplications(long eventId);

    List<Application> getAllApplications();

    List<Application> getExpertApplications(String token, int from, int count);

    List<Application> getExpertEventApplications(String token, long eventId, int from, int count);

    boolean applicationExistenceById(long id);

    boolean userApplicationExistence(String token, long applicationId);

    boolean userPatternApplicationExistence(String token, long patternId);

    boolean userEventApplicationExistence(String token, long eventId);

    boolean expertApplicationExistence(String token, long applicationId);

    Application getApplicationByEventUser(long eventId, long userId);
}
