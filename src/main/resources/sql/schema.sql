DROP TABLE IF EXISTS g_event CASCADE;
DROP TABLE IF EXISTS g_user CASCADE;
DROP TABLE IF EXISTS pattern CASCADE;
DROP TABLE IF EXISTS pattern_element CASCADE;
DROP TABLE IF EXISTS text_value CASCADE;
DROP TABLE IF EXISTS checkbox_value CASCADE;
DROP TABLE IF EXISTS combobox_element CASCADE;
DROP TABLE IF EXISTS combobox_value CASCADE;
DROP TABLE IF EXISTS radiobutton_value CASCADE;

CREATE TABLE g_event (
  event_id SERIAL,
  event_name VARCHAR,
  event_site_url VARCHAR,
  event_owner BIGINT,
  PRIMARY KEY (event_id)
);

CREATE TABLE g_user (
  user_id       SERIAL,
  first_name    VARCHAR(50),
  second_name   VARCHAR(50),
  middle_name   VARCHAR(50),
  token         VARCHAR(30),
  hash_password VARCHAR(70),
  birth_date    DATE,
  user_role     VARCHAR(20),
  PRIMARY KEY (user_id)
);

CREATE TABLE pattern (
  pattern_id      SERIAL,
  bid_name        VARCHAR(50),
  bid_event       BIGINT,
  bid_description VARCHAR(255),
  end_date        DATE,
  PRIMARY KEY (pattern_id)
);

CREATE TABLE pattern_element (
  element_id          SERIAL,
  pattern_id          BIGINT,
  element_name        VARCHAR(50),
  element_description VARCHAR(255),
  element_type        VARCHAR(20),
  layout_x            INT,
  layout_y            INT,
  PRIMARY KEY (element_id)
);

CREATE TABLE text_el (
  LIKE pattern_element INCLUDING ALL
)
  INHERITS (pattern_element);

CREATE TABLE checkbox_el (
  LIKE pattern_element INCLUDING ALL
)
  INHERITS (pattern_element);

CREATE TABLE combobox_el (
  LIKE pattern_element INCLUDING ALL,
  choosing_value VARCHAR ARRAY
)
  INHERITS (pattern_element);

CREATE TABLE radiobutton_el (
  LIKE pattern_element INCLUDING ALL
)
  INHERITS (pattern_element);

CREATE TABLE text_value (
  text_value_id SERIAL,
  element_id    BIGINT,
  user_id       BIGINT,
  text_value    VARCHAR(255),
  PRIMARY KEY (text_value_id)
);

CREATE TABLE radiobutton_value (
  radiobutton_value_id SERIAL,
  element_id           BIGINT,
  user_id              BIGINT,
  radiobutton_value    INT,
  PRIMARY KEY (radiobutton_value_id)
);

CREATE TABLE checkbox_value (
  checkbox_value_id SERIAL,
  element_id        BIGINT,
  user_id           BIGINT,
  checkbox_value    BOOLEAN,
  PRIMARY KEY (checkbox_value_id)
);

CREATE TABLE combobox_value (
  combobox_value_id SERIAL,
  element_id        BIGINT,
  user_id           BIGINT,
  combobox_value    INT,
  PRIMARY KEY (combobox_value_id)
);

ALTER TABLE pattern
  ADD CONSTRAINT pattern_fk_1 FOREIGN KEY (bid_event) REFERENCES g_event (event_id);
ALTER TABLE g_event
  ADD CONSTRAINT event_fk_1 FOREIGN KEY (event_owner) REFERENCES g_user (user_id);
ALTER TABLE pattern_element
  ADD CONSTRAINT element_fk_1 FOREIGN KEY (element_id) REFERENCES pattern (pattern_id);
ALTER TABLE combobox_value
  ADD CONSTRAINT combo_value_fk_1 FOREIGN KEY (element_id) REFERENCES combobox_el (element_id);
ALTER TABLE text_value
  ADD CONSTRAINT text_fk_1 FOREIGN KEY (element_id) REFERENCES text_el (element_id);
ALTER TABLE checkbox_value
  ADD CONSTRAINT checkbox_fk_1 FOREIGN KEY (element_id) REFERENCES checkbox_el (element_id);
ALTER TABLE radiobutton_value
  ADD CONSTRAINT checkbox_fk_1 FOREIGN KEY (element_id) REFERENCES radiobutton_el (element_id);
