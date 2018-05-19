INSERT INTO g_user (id, email, first_name, hash_password, middle_name, role, second_name, token)
  VALUES (1, '1@', 'a', 'ha', 'a', 'USER', 'a', '1t');

INSERT INTO g_user (id, email, first_name, hash_password, middle_name, role, second_name, token)
  VALUES (2, '2@', 'b', 'hb', 'b', 'USER', 'b', '2t');

INSERT INTO g_user (id, email, first_name, hash_password, middle_name, role, second_name, token)
  VALUES (3, '3@', 'c', 'hc', 'c', 'EXPERT', 'c', '3t');

INSERT INTO g_user (id, email, first_name, hash_password, middle_name, role, second_name, token)
  VALUES (4, '4@', 'd', 'hd', 'd', 'CREATOR', 'd', '4t');

INSERT INTO g_user (id, email, first_name, hash_password, middle_name, role, second_name, token)
  VALUES (5, '5@', 'e', 'he', 'e', 'EXPERT', 'e', '5t');

INSERT INTO g_event (id, description, owner_id) VALUES (1, 'event1', 4);

INSERT INTO g_event (id, description, owner_id) VALUES (2, 'event2', 4);

INSERT INTO g_event (id, description, owner_id) VALUES (3, 'event3', 4);

INSERT INTO pattern (id, bid_name, event_id) VALUES (1, 'bid1', 1);

INSERT INTO pattern (id, bid_name, event_id) VALUES (2, 'bid2', 2);

INSERT INTO pattern (id, bid_name, event_id) VALUES (3, 'bid2', 3);

INSERT INTO g_user_ex_events (experts_id, ex_events_id) VALUES (3, 1);

INSERT INTO g_user_ex_events (experts_id, ex_events_id) VALUES (5, 2);

INSERT INTO g_user_ex_events (experts_id, ex_events_id) VALUES (5, 3);

INSERT INTO bid (id, status, pattern_id, user_id) VALUES (1, 'ACTIVE', 1, 1);

INSERT INTO bid (id, status, pattern_id, user_id) VALUES (2, 'ACTIVE', 1, 2);

INSERT INTO bid (id, status, pattern_id, user_id) VALUES (3, 'ACTIVE', 2, 1);

INSERT INTO bid (id, status, pattern_id, user_id) VALUES (4, 'ACTIVE', 2, 2);
