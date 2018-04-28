package ru.itis.grant.dao.interfaces;

import ru.itis.grant.model.Element;

public interface ElementDao {

    void addElement(Element element);

    void deleteElement(Element element);

    void deleteElement(long id);

    Element updateElement(Element element);

    Element getElement(long id);
}
