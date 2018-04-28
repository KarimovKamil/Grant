package ru.itis.grant.dao.interfaces;

import ru.itis.grant.model.Pattern;

import java.util.List;

public interface PatternDao {

    void addPattern(Pattern pattern);

    void deletePattern(long id);

    void deletePattern(Pattern pattern);

    Pattern updatePattern(Pattern pattern);

    Pattern getPattern(long id);

    List<Pattern> getUserPatterns(long userId);

    List<Pattern> getAllPatterns();

    List<Pattern> getActivePattern();
}
