package ru.itis.grant.dao.impl;

import org.springframework.stereotype.Repository;
import ru.itis.grant.dao.interfaces.PatternDao;
import ru.itis.grant.model.Element;
import ru.itis.grant.model.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PatternDaoImpl implements PatternDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addPattern(Pattern pattern) {

    }

    @Override
    public void deletePattern(long id) {

    }

    @Override
    public void deletePattern(Pattern pattern) {

    }

    @Override
    public Pattern updatePattern(Pattern pattern) {
        return null;
    }

    @Override
    public Pattern getPattern(long id) {
        return null;
    }

    @Override
    public List<Pattern> getUserPatterns(long userId) {
        return null;
    }

    @Override
    public List<Pattern> getAllPatterns() {
        return null;
    }

    @Override
    public List<Pattern> getActivePattern() {
        return null;
    }
}
