package ru.itis.grant.dao.impl;

import org.springframework.stereotype.Repository;
import ru.itis.grant.dao.interfaces.ElementValueDao;
import ru.itis.grant.model.ElementValue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ElementValueDaoImpl implements ElementValueDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addElementValue(ElementValue elementValue) {
        em.persist(elementValue);
    }
}
