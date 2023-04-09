CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(100) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);


CREATE TABLE stories
(
    id          SERIAL PRIMARY KEY,
    name        STRING,
    genre       STRING,
    audioLink   STRING,
    userCreated BOOLEAN,
    linkBook    STRING
);

select *
from stories
ALTER TABLE stories
    ADD COLUMN GID VARCHAR(255);



CREATE TABLE features
(
    id                  SERIAL PRIMARY KEY,
    persons             JSONB,
    audioInrich         BOOLEAN,
    isMute              BOOLEAN,
    currentTime         FLOAT,
    genres              INTEGER[],
    learnFromOldEnabled BOOLEAN,
    story_id            INTEGER REFERENCES stories (id)
);
select *
from features
    INSERT
INTO users (username, email, password, created_at)
VALUES ('' john_doe '', '' john@example.com '', '' password123 '', NOW());

INSERT INTO users (username, email, password, created_at)
VALUES (''jane_doe'', ''jane@example.com'', ''secret_password'', NOW());

INSERT INTO users (username, email, password, created_at)
VALUES (''bob_smith'', ''bob@example.com'', ''qwerty123'', NOW());

-- Insert a story created by a user
INSERT INTO stories (name, genre, audioLink, userCreated, linkBook)
VALUES (''My First Story'',
        ''Fantasy'',
        '' / audio / myfirststory.wav '',
        true,
        '' / books / myfirststory.pdf '');

-- Insert a story not created by a user
INSERT INTO stories (name, genre, audioLink, userCreated, linkBook)
VALUES (''Alice in Wonderland'',
        ''Children'',
        '' / audio / aliceinwonderland.wav '',
        false,
        '' / books / aliceinwonderland.pdf '');


-- Insert a feature with one person
INSERT INTO features (persons, audioInrich, isMute, currentTime, genres, learnFromOldEnabled, story_id)
VALUES (''{"persons": [{"name": "John Doe", "image": "/images/johndoe.png", "audioSample": "/audio/johndoe.wav"}]}'',
        true,
        false,
        0.0,
        ''{1, 2}'',
        true,
        1);

-- Insert a feature with multiple persons
INSERT INTO features (persons, audioInrich, isMute, currentTime, genres, learnFromOldEnabled, story_id)
VALUES (''{"persons": [{"name": "Jane Doe", "image": "/images/janedoe.png", "audioSample": "/audio/janedoe.wav"}, {"name"
        : "Bob Smith", "image": "/images/bobsmith.png", "audioSample": "/audio/bobsmith.wav"}]}'',
        false,
        true,
        10.0,
        ''{3, 4}'',
        false,
        2);


-- Create genres table
CREATE TABLE genres
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

SELECT *
FROM features;

-- Insert some data into genres table
INSERT INTO genres (name)
VALUES (''Comedy'');
INSERT INTO genres (name)
VALUES (''Science Fiction'');
INSERT INTO genres (name)
VALUES (''Action'');
INSERT INTO genres (name)
VALUES (''Fiction'');


select *
from features

