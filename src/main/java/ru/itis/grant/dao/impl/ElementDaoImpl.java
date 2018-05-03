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
        em.persist(element);
    }

    @Override
    public void deleteElement(Element element) {
        em.remove(element);
    }

    @Override
    public void deleteElement(long id) {
        em.createQuery("delete from Element e where e.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Element updateElement(Element element) {
        return em.merge(element);
    }

    @Override
    public Element getElement(long id) {
        return em.find(Element.class, id);
    }
}
