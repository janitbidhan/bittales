CREATE TABLE IF NOT EXISTS intData (
                                       id BIGSERIAL PRIMARY KEY,
                                       lkey VARCHAR ( 200 ) NOT null,
    lvalue VARCHAR ( 200 ) NOT null,
    lang VARCHAR ( 2 ) NOT null
    );
select * from intData;

INSERT INTO intData(lkey, lvalue, lang)
VALUES
    ('K1','Hello Class','EN'),
    ('K2','Welcome to SWE 642','EN'),
    ('K3','This is a demo for Spring Boot','EN'),
    ('K4','We are going to learn something new','EN'),
    ('K5','Ask questions','EN'),
    ('K6','Thank you','EN');

INSERT INTO intData(lkey, lvalue, lang)
VALUES
    ('K1','Hola clase','ES'),
    ('K2','Bienvenido a SWE 642','ES'),
    ('K3','Esta es una demostración de Spring Boot','ES'),
    ('K4','Nosotros vamos a aprender algo nuevo','ES'),
    ('K5','Hacer preguntas','ES'),
    ('K6','Gracias','ES');




--CREATE TABLE IF NOT EXISTS intDataES ( id BIGSERIAL PRIMARY KEY,value VARCHAR ( 200 ) NOT NULL);

select * from intData;




DROP TABLE IF EXISTS intdata;
