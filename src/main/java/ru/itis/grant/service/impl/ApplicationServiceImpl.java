package ru.itis.grant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.dao.interfaces.ApplicationDao;
import ru.itis.grant.model.Application;
import ru.itis.grant.service.interfaces.ApplicationService;
import ru.itis.grant.validation.verification.Verification;

@Transactional
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationDao applicationDao;

    @Autowired
    Verification verification;

    @Override
    public String getApplicationInString(long applicationId) {
        verification.verifyApplicationExistenceById(applicationId);
        Application application = applicationDao.getApplicationById(applicationId);
        return application.toString();
    }
}
