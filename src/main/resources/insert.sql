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

INSERT INTO g_user_ex_events VALUES (2, 1);

INSERT INTO g_user_ex_events VALUES (2, 2);

INSERT INTO g_user_ex_events VALUES (2, 3);

INSERT INTO g_user_ex_events VALUES (2, 4);