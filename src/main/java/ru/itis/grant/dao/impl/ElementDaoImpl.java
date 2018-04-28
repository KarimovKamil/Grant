package ru.itis.grant.dao.impl;

import org.springframework.stereotype.Repository;
import ru.itis.grant.dao.interfaces.ElementDao;
import ru.itis.grant.model.Element;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ElementDaoImpl implements ElementDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addElement(Element element) {

    }

    @Override
    public void deleteElement(Element element) {

    }

    @Override
    public void deleteElement(long id) {

    }

    @Override
    public Element updateElement(Element element) {
        return null;
    }

    @Override
    public Element getElement(long id) {
        return null;
    }
}
