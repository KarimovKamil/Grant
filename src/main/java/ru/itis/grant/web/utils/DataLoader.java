package ru.itis.grant.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.dao.interfaces.ElementDao;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.dao.interfaces.PatternDao;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.model.Element;
import ru.itis.grant.model.Event;
import ru.itis.grant.model.Pattern;
import ru.itis.grant.model.User;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Transactional
@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    UserDao userDao;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private PatternDao patternDao;
    @Autowired
    private ElementDao elementDao;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        addUsers();
        addEvents();
        addPatterns();
        addElements();
    }

    private void addUsers() {
        userDao.addUser(User.builder()
                .birthDate(new Date(2000 - 11 - 11))
                .firstName("Иван")
                .middleName("Раильевич")
                .secondName("Петров")
                .hashPassword("$2a$10$1kBHC1qLaXQc75vzlOWlwO9hxcFGk6i5AhqqHZqeZfnbyjGzqNT9q")
                .email("string1@mail.ru")
                .token("token1")
                .role("USER")
                .build());
        userDao.addUser(User.builder()
                .birthDate(new Date(2000 - 11 - 11))
                .firstName("Самуил")
                .middleName("Евграфович")
                .secondName("Ядренкин")
                .hashPassword("$2a$10$1kBHC1qLaXQc75vzlOWlwO9hxcFGk6i5AhqqHZqeZfnbyjGzqNT9q")
                .email("string2@mail.ru")
                .token("token2")
                .role("USER")
                .build());
        userDao.addUser(User.builder()
                .birthDate(new Date(2000 - 11 - 11))
                .firstName("Доминика")
                .middleName("Станиславовна")
                .secondName("Косинова")
                .hashPassword("$2a$10$1kBHC1qLaXQc75vzlOWlwO9hxcFGk6i5AhqqHZqeZfnbyjGzqNT9q")
                .email("string3@mail.ru")
                .token("token3")
                .role("USER")
                .build());
    }

    private void addEvents() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            eventDao.addEvent(Event.builder()
                    .description("Задача Конкурса – развитие регионального и международного научного сотрудничества, " +
                            "создание условий молодым российским ученым для обмена результатами исследований, выявление " +
                            "тенденций научных исследований по направлениям, в том числе поддерживаемым Фондом, привлечение " +
                            "молодых ученых к фундаментальным исследованиям по актуальным научным тематикам.")
                    .startDate(dateFormat.parse("15/07/2018 10:00"))
                    .endDate(dateFormat.parse("15/07/2019 10:00"))
                    .name("Конкурс проектов 2018 года организации российских и международных молодежных научных " +
                            "мероприятий, проводимый РФФИ")
                    .siteUrl("http://www.rfbr.ru/rffi/ru/contest/o_2052209")
                    .owner(userDao.getUserById(2))
                    .build());
            eventDao.addEvent(Event.builder()
                    .description("В целях развития и поддержки творческого и научного потенциала учащихся, учителей, " +
                            "воспитателей, педагогов и методистов образовательных организаций автономная некоммерческая " +
                            "организация «Центр научного творчества «Вектор» проводит Всероссийский дистанционный заочный " +
                            "конкурс «ВЕКТОРИАДА-2018».")
                    .startDate(dateFormat.parse("5/5/2018 10:00"))
                    .endDate(dateFormat.parse("10/12/2018 10:00"))
                    .name("Всероссийский дистанционный заочный конкурс «ВЕКТОРИАДА-2018»")
                    .siteUrl("http://prodod.moscow/archives/event/vserossijskij-distantsionnyj-zaochnyj-konkurs-vektoriada-2018")
                    .owner(userDao.getUserById(2))
                    .build());
            eventDao.addEvent(Event.builder()
                    .description("Задача Конкурса – организация взаимодействия российских и зарубежных ученых в формате " +
                            "научных мероприятий, с целью создания условий для долгосрочного сотрудничества по проведению " +
                            "фундаментальных научных исследований в области молекулярной биологии.")
                    .startDate(dateFormat.parse("1/1/2017 10:00"))
                    .endDate(dateFormat.parse("1/1/2018 10:00:00"))
                    .name("Конкурс на лучший проект организации на территории России международных научных мероприятий " +
                            "в области молекулярной биологии")
                    .siteUrl("http://www.rfbr.ru/rffi/ru/contest/o_2074065")
                    .owner(userDao.getUserById(2))
                    .build());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addPatterns() {
        patternDao.addPattern(Pattern.builder()
                .applicationName("Заявка на \"Конкурс проектов 2018 года организации российских и международных " +
                        "молодежных научных мероприятий")
                .event(eventDao.getEvent(1)).build());
        patternDao.addPattern(Pattern.builder()
                .applicationName("Заявка на \"Всероссийский дистанционный заочный конкурс «ВЕКТОРИАДА-2018»\".")
                .event(eventDao.getEvent(2)).build());
        patternDao.addPattern(Pattern.builder()
                .applicationName("Заявка на \"Конкурс на лучший проект организации на территории России международных " +
                        "научных мероприятий в области молекулярной биологии\".")
                .event(eventDao.getEvent(3)).build());
    }

    private void addElements() {
        elementDao.addElement(Element.builder()
                .description("Введите вашу почту")
                .layoutX(1)
                .layoutY(0)
                .name("Email")
                .required(true)
                .type("TEXT")
                .pattern(patternDao.getPattern(1))
                .build());
        elementDao.addElement(Element.builder()
                .layoutX(2)
                .layoutY(0)
                .name("Пол")
                .selectableValue(new String[]{"М", "Ж"})
                .required(true)
                .type("RADIOBUTTON")
                .pattern(patternDao.getPattern(1))
                .build());
        elementDao.addElement(Element.builder()
                .description("Хотите получать уведомления, связанные с конкурсом?")
                .layoutX(3)
                .layoutY(0)
                .name("Уведомления")
                .selectableValue(new String[]{"Хочу"})
                .required(true)
                .type("CHECKBOX")
                .pattern(patternDao.getPattern(1))
                .build());
        elementDao.addElement(Element.builder()
                .description("Город, в котором вы проживаете")
                .layoutX(4)
                .layoutY(0)
                .name("Город")
                .selectableValue(new String[]{"Казань", "Москва", "Санкт-Петербург"})
                .required(true)
                .type("COMBOBOX")
                .pattern(patternDao.getPattern(1))
                .build());
    }
}
