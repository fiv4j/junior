-- DATABASE
CREATE DATABASE tracker;

\connect tracker;

-- TABLES
CREATE TABLE item (
	id SERIAL PRIMARY KEY,
	category_id INTEGER REFERENCES category (id),
	state_id INTEGER REFERENCES state (id),
	user_id INTEGER REFERENCES user (id),
	title VARCHAR(250) NOT NULL,
	content text,
	create_date DATE NOT NULL DEFAULT CURRENT_DATE,
	close_date DATE
);

CREATE TABLE category (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE state (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE user (
	id SERIAL PRIMARY KEY,
	name VARCHAR(200),
	role_id INTEGER REFERENCES role (id)
);

CREATE TABLE role (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE role_has_rule (
	role_id INTEGER REFERENCES role (id),
	rule_id INTEGER REFERENCES rule (id),
	PRIMARY KEY (role_id, rule_id)
);

CREATE TABLE rule (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE comment (
	id SERIAL PRIMARY KEY,
	user_id INTEGER REFERENCES user (id),
	item_id INTEGER REFERENCES item (id),
	content TEXT NOT NULL
);

CREATE TABLE attachment (
	id SERIAL PRIMARY KEY,
	user_id INTEGER REFERENCES user (id),
	item_id INTEGER REFERENCES item (id),
	file_path VARCHAR(250) NOT NULL
);

-- DATA
INSERT INTO state (name)
VALUES 	('new'),
		('closed'),
		('in progress');

INSERT INTO category (name)
VALUES 	('electricity'),
		('plumbing'),
		('drain');

INSERT INTO rule (name)
VALUES 	('reading only'),
		('commenting'),
		('delete own msg')
		('delete any msg')
		('correct own msg')
		('correct any msg');

INSERT INTO role (name)
VALUES 	('limited'),
		('simple'),
		('admin');

INSERT INTO role_has_rule (role_id, rule_id)
VALUES 	(1, 1),
		(1, 3),
		(1, 5),
		(2, 1),
		(2, 2),
		(2, 3),
		(2, 5),
		(3, 2),
		(3, 4),
		(3, 6);

INSERT INTO user (name, role_id)
VALUES 	('Ivan Ivanov', 2),
		('Petr petrov', 1),
		('Stepan Andreev', 3);

INSERT INTO item (category_id, state_id, user_id, title, content, create_date)
VALUES 	(1, 1, 2, 'request 1', 'lorem ipsum ...', '2019-12-29'),
		(2, 3, 1, 'request 2', 'ipsum lorem', '2020-01-15');


INSERT INTO comment (user_id, item_id, content)
VALUES (3, 1, 'more lorems');
