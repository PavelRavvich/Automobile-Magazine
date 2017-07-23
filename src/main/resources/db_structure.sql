CREATE TABLE IF NOT EXISTS users (
     id SERIAL NOT NULL ,
     login TEXT UNIQUE NOT NULL ,
     password TEXT UNIQUE NOT NULL ,
     PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS propose (
     id SERIAL NOT NULL ,
     id_auhtor INTEGER NOT NULL ,
     sold BOOLEAN NOT NULL ,
     description TEXT NOT NULL ,
     mark TEXT NOT NULL ,
     model TEXT NOT NULL ,
     PRIMARY KEY (id),
     FOREIGN KEY (id_auhtor) REFERENCES users (id)
);

-- Add test user and get id.
INSERT INTO users (id, login, password)
VALUES (DEFAULT ,'test','test') RETURNING id;

-- Select user by pair login password.
SELECT id FROM users WHERE login = 'test' AND password = 'test';

INSERT INTO propose (id, id_auhtor, sold, description, mark, model)
VALUES (DEFAULT, '1', FALSE, 'test_desc', 'audi', 'a8') RETURNING id;