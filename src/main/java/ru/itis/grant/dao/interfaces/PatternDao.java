package ru.itis.grant.dao.interfaces;

import ru.itis.grant.model.Pattern;

import java.util.Date;
import java.util.List;

public interface PatternDao {

    void addPattern(Pattern pattern);

    void deletePattern(long id);

    void deletePattern(Pattern pattern);

    Pattern updatePattern(Pattern pattern);

    Pattern getPattern(long id);

    Pattern getEventPattern(long eventId);

    List<Pattern> getAllPatterns();

    boolean patternExistence(long id);

    boolean patternTimeCorrect(long id, Date date);
}
