-- USERS

INSERT INTO g_user (id, email, first_name, hash_password, middle_name, role, second_name, token, birth_date)
  VALUES (1, 'iv@mail.ru', 'Ivan', '$2a$10$ouocv31/TcGI4ph2tlWbW.8E.p.TlhavKRvjpDQjm7B2WAQV43Ynm',
  'Ivanovich', 'USER', 'Ivanov', '1', '2000-05-19 13:46:21.174000');

INSERT INTO g_user (id, email, first_name, hash_password, middle_name, role, second_name, token, birth_date)
  VALUES (2, 'eg@mail.ru', 'Egor', '$2a$10$ouocv31/TcGI4ph2tlWbW.8E.p.TlhavKRvjpDQjm7B2WAQV43Ynm',
  'Ivanovich', 'USER', 'Andreev', '2', '1997-05-19 13:46:21.174000');

INSERT INTO g_user (id, email, first_name, hash_password, middle_name, role, second_name, token, birth_date)
  VALUES (3, 'paveld@mail.ru', 'Pavel', '$2a$10$ouocv31/TcGI4ph2tlWbW.8E.p.TlhavKRvjpDQjm7B2WAQV43Ynm',
  'Igorevich', 'USER', 'Petrov', '3', '1995-05-19 13:46:21.174000');

INSERT INTO g_user (id, email, first_name, hash_password, middle_name, role, second_name, token, birth_date)
  VALUES (4, 'bullet@mail.ru', 'Bulat', '$2a$10$ouocv31/TcGI4ph2tlWbW.8E.p.TlhavKRvjpDQjm7B2WAQV43Ynm',
  'Borisovich', 'USER', 'Oblomov', '4', '1993-05-19 13:46:21.174000');

-- EVENTS

INSERT INTO g_event VALUES (1, 'Event desc 1', 'Event name 1', 'http://localhost:8080/swagger-ui.html', 3);

INSERT INTO g_event VALUES (2, 'Event desc 2', 'Event name 2', 'https://www.google.ru/', 3);

INSERT INTO g_event VALUES (3, 'Event desc 3', 'Event name 3', 'https://steamcommunity.com', 3);

INSERT INTO g_event VALUES (4, 'Event desc 4', 'Event name 4', 'https://vk.com/', 3);

-- EXPERT EVENTS

INSERT INTO g_user_ex_events VALUES (3, 1);

INSERT INTO g_user_ex_events VALUES (3, 2);

INSERT INTO g_user_ex_events VALUES (3, 3);

INSERT INTO g_user_ex_events VALUES (3, 4);

-- PATTERNS

INSERT INTO pattern VALUES (1, 'Bid name 1', 'Pattern desc 1',
 '2226-05-19 13:46:21.174000', '2017-05-19 13:46:21.174000', 1);

INSERT INTO pattern VALUES (2, 'Bid name 2', 'Pattern desc 2',
 '2019-05-19 13:46:21.174000', '2016-05-19 13:46:21.174000', 2);

INSERT INTO pattern VALUES (3, 'Bid name 3', 'Pattern desc 3',
 '2016-05-19 13:46:21.174000', '2015-05-19 13:46:21.174000', 3);

-- ELEMENTS

INSERT INTO element (id, description, layout_x, layout_y, name, required, type, pattern_id)
VALUES (1, 'Element desc 1', 1, 2, 'Element name 1', 'true', 'COMBOBOX', 1);

INSERT INTO element (id, description, layout_x, layout_y, name, required, type, pattern_id)
VALUES (2, 'Element desc 2', 1, 3, 'Element name 2', 'true', 'CHECKBOX', 1);

INSERT INTO element (id, description, layout_x, layout_y, name, required, type, pattern_id)
VALUES (3, 'Element desc 3', 2, 3, 'Element name 3', 'true', 'TEXT', 1);

INSERT INTO element (id, description, layout_x, layout_y, name, required, type, pattern_id)
VALUES (4, 'Element desc 4', 6, 2, 'Element name 4', 'true', 'RADIOBUTTON', 2);

INSERT INTO element (id, description, layout_x, layout_y, name, required, type, pattern_id)
VALUES (5, 'Element desc 5', 4, 3, 'Element name 5', 'true', 'MULTISELECT', 2);

INSERT INTO element (id, description, layout_x, layout_y, name, required, type, pattern_id)
VALUES (6, 'Element desc 6', 3, 3, 'Element name 6', 'true', 'CHECKBOX', 3);

INSERT INTO element (id, description, layout_x, layout_y, name, required, type, pattern_id)
VALUES (7, 'Element desc 7', 3, 2, 'Element name 7', 'true', 'TEXT', 3);

-- BIDS

INSERT INTO bid VALUES (1, '2018-05-19 13:46:21.174000', 'Comment 1', 'ACTIVE', 3, 1);

INSERT INTO el_value (id, bid_id, element_id) VALUES (1, 1, 6);

INSERT INTO el_value (id, bid_id, element_id) VALUES (2, 1, 7);