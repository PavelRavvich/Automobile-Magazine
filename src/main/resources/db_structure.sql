CREATE DATABASE auto_mag ENCODING 'UTF8';

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
     image BYTEA,
     PRIMARY KEY (id),
     FOREIGN KEY (id_auhtor) REFERENCES users (id)
);

-- Add image byte array.
UPDATE propose SET image = (?) WHERE id = 1;

-- Add test user and get id.
INSERT INTO users (id, login, password)
VALUES (DEFAULT ,'test','test') RETURNING id;

-- Select user by pair login password.
SELECT id FROM users WHERE login = 'test' AND password = 'test';

-- Add test propose and get id.
INSERT INTO propose (id, id_auhtor, sold, description, mark, model)
VALUES (DEFAULT, '1', FALSE, 'test_desc', 'audi', 'a8') RETURNING id;

-- Select propose by propose id.
SELECT p.id_auhtor, p.sold, p.description, p.mark, p.model
FROM propose AS p WHERE id = '1';

-- For convenient view propose in bash select with restriction length values.
select id, id_auhtor, sold, description, mark, model,
    substring(image from 0 for 5)

    AS cuted_img from propose
    where id = '?';