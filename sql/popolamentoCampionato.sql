INSERT INTO costruttore (ragionesociale,nome,sede,ncomponenti) VALUES
	('Brembo','Brembo','IT',0),
    ('Magneti Marelli','Magneti Marelli:','IT',0),
    ('Hewland Engineering','Hewland Engineering','GB',0),
    ('AP Racing','AP Racing','GB',0),
    ('Xtrac','Xtrac','GB',0);

INSERT INTO scuderia(nome,sede) VALUES
	('Scuderia1', 'ITA'),
    ('Scuderia2', 'GBR'),
    ('Scuderia3', 'FR');

INSERT INTO vettura(ngara, modello, scuderia)
VALUES
    (1, 'ModelloA1', 'Scuderia1'),
    (2, 'ModelloB1', 'Scuderia1'),
    (3, 'ModelloA2', 'Scuderia2'),
    (4, 'ModelloB2', 'Scuderia2'),
    (5, 'ModelloA3', 'Scuderia3'),
    (6, 'ModelloB3', 'Scuderia3');

INSERT INTO pilota (nome, cognome, datanascita, nazionalita, tipopilota, dataprimalicenza, nlicenze, vettura)
VALUES
    -- Piloti Equipaggio 1 Scuderia1
    ('John', 'Smith', '1990-01-01', 'ITA', 'PRO', NULL, 5, 1),
    ('Michael', 'Johnson', '1988-02-02', 'GBR', 'PRO', NULL, 3, 1),
    ('Sophia', 'Wilson', '1993-07-07', 'USA', 'AM', '2018-03-03', NULL, 1),
    ('Matthew', 'White', '1987-08-08', 'GBR', 'AM', '2017-04-04', NULL, 1),

    -- Piloti Equipaggio 2 Scuderia1
    ('William', 'Davis', '1992-01-01', 'ITA', 'PRO', NULL, 4, 2),
    ('Sophie', 'Anderson', '1985-02-02', 'GBR', 'PRO', NULL, 6, 2),
    ('Noah', 'Roberts', '1996-05-05', 'ESP', 'AM', '2016-01-01', NULL, 2),

    -- Piloti Equipaggio 1 Scuderia2
    ('Alexander', 'Brown', '1991-09-09', 'GER', 'PRO', NULL, 2, 3),
    ('Aiden', 'Lewis', '1999-02-02', 'SUI', 'AM', '2015-06-06', NULL, 3),
    ('Emma', 'Martin', '1990-03-03', 'NED', 'AM', '2014-07-07', NULL, 3),
    ('Liam', 'Woods', '1984-04-04', 'USA', 'AM', '2013-08-08', NULL, 3),

    -- Piloti Equipaggio 2 Scuderia2
    ('Olivia', 'Harris', '1993-06-15', 'FRA', 'PRO', NULL, 3, 4),
    ('Ethan', 'Miller', '1989-03-22', 'GER', 'PRO', NULL, 5, 4),
    ('Ava', 'Brown', '1995-08-11', 'USA', 'AM', '2019-02-28', NULL, 4),

    -- Piloti Equipaggio 1 Scuderia3
    ('Daniel', 'Clark', '1992-04-19', 'ITA', 'PRO', NULL, 4, 5),
    ('Mia', 'Thomas', '1986-11-27', 'GBR', 'AM', '2016-09-15', NULL, 5),
    ('David', 'Garcia', '1994-02-08', 'ESP', 'AM', '2015-12-10', NULL, 5),
    ('Sophie', 'Allen', '1988-09-03', 'NED', 'AM', '2014-06-22', NULL, 5),

    -- Piloti Equipaggio 2 Scuderia3
    ('Elijah', 'Ward', '1991-07-02', 'GER', 'PRO', NULL, 3, 6),
    ('Amelia', 'Baker', '1997-05-18', 'SUI', 'PRO', NULL, 4, 6),
    ('Logan', 'Martinez', '1985-12-12', 'USA', 'AM', '2013-10-05', NULL, 6),
    ('Hannah', 'Perez', '1989-08-28', 'FRA', 'AM', '2012-07-17', NULL, 6);


INSERT INTO circuito (nome, sede, lunghezza, ncurve) VALUES
	('Monza', 'ITA', 5.793, 11),
	('Silverstone', 'GBR', 5.891, 18),
	('Red Bull Ring', 'AUT', 4.318, 10),
	('Circuit de Spa-Francorchamps', 'BEL', 7.004, 19),
	('Autodromo Nazionale di Monza', 'ITA', 5.793, 11),
	('Circuit de Barcelona-Catalunya', 'ESP', 4.675, 16),
	('Hungaroring', 'HUN', 4.381, 14),
	('Circuit Paul Ricard', 'FRA', 5.842, 15),
	('Autodromo Enzo e Dino Ferrari', 'ITA', 4.909, 19),
	('Sochi Autodrom', 'RUS', 5.848, 18),
	('Yas Marina Circuit', 'ARE', 5.554, 21),
	('Circuit de Monaco', 'MCO', 3.337, 19);
    
-- Componenti
INSERT INTO componente (vettura, costruttore, dataCreazione, cilindrata, tipomotore, ncilindri, materiale, nmarce, peso, tipocomponente)
VALUES
    (1, 'Brembo', '2022-01-11', 2000, 'TURBO', 6, NULL, NULL, NULL, 'MOTORE'),
    (1, 'Magneti Marelli', '2022-01-12', NULL, NULL, NULL, 'Alluminio', NULL, 750, 'TELAIO'),
    (1, 'Hewland Engineering', '2022-01-13', NULL, NULL, NULL, NULL, 7, NULL, 'CAMBIO'),
    (2, 'AP Racing', '2022-01-14', 1800, 'ASPIRATO', 4, NULL, NULL, NULL, 'MOTORE'),
    (2, 'Xtrac', '2022-01-15', NULL, NULL, NULL, 'Carbonio', NULL, 720, 'TELAIO'),
    (2, 'Brembo', '2022-01-16', NULL, NULL, NULL, NULL, 8, NULL, 'CAMBIO'),
    (3, 'Magneti Marelli', '2022-01-17', 2200, 'TURBO', 8, NULL, NULL, NULL, 'MOTORE'),
    (3, 'Hewland Engineering', '2022-01-18', NULL, NULL, NULL, 'Alluminio', NULL, 680, 'TELAIO'),
    (3, 'AP Racing', '2022-01-19', NULL, NULL, NULL, NULL, 7, NULL, 'CAMBIO'),
    (4, 'Xtrac', '2022-01-20', 1900, 'TURBO', 6, NULL, NULL, NULL, 'MOTORE'),
    (4, 'Brembo', '2022-01-21', NULL, NULL, NULL, 'Alluminio', NULL, 710, 'TELAIO'),
    (4, 'Magneti Marelli', '2022-01-22', NULL, NULL, NULL, NULL, 8, NULL, 'CAMBIO'),
    (5, 'AP Racing', '2022-01-23', 2100, 'ASPIRATO', 8, NULL, NULL, NULL, 'MOTORE'),
    (5, 'Xtrac', '2022-01-24', NULL, NULL, NULL, 'Carbonio', NULL, 740, 'TELAIO'),
    (5, 'Hewland Engineering', '2022-01-25', NULL, NULL, NULL, NULL, 7, NULL, 'CAMBIO'),
    (6, 'Magneti Marelli', '2022-01-26', 2000, 'TURBO', 6, NULL, NULL, NULL, 'MOTORE'),
    (6, 'Brembo', '2022-01-27', NULL, NULL, NULL, 'Alluminio', NULL, 730, 'TELAIO'),
    (6, 'AP Racing', '2022-01-28', NULL, NULL, NULL, NULL, 8, NULL, 'CAMBIO');

INSERT INTO gara(tipo,durata,dataGara,nome,circuito) VALUES
	('ASCIUTTA', 120.5, '2023-01-01', 'Gara1', 'Monza'),
	('ASCIUTTA', 130.0, '2023-02-15', 'Gara2', 'Silverstone'),
	('ASCIUTTA', 110.0, '2023-03-10', 'Gara3', 'Red Bull Ring'),
	('ASCIUTTA', 120.0, '2023-04-05', 'Gara4', 'Circuit de Spa-Francorchamps'),
	('ASCIUTTA', 125.0, '2023-05-20', 'Gara5', 'Autodromo Nazionale di Monza'),
	('BAGNATA', 115.0, '2023-06-08', 'Gara6', 'Circuit de Barcelona-Catalunya');
    
-- Partecipazioni per la gara 1
INSERT INTO partecipazione (gara, vettura, posizione, esito, punteggio)
VALUES
    -- Gara 1
    (1, 1, 1, 'TERMINATA', 25),
    (1, 2, 2, 'TERMINATA', 20),
    (1, 3, 3, 'TERMINATA', 16),
    (1, 4, 4, 'TERMINATA', 15),
    (1, 5, 5, 'TERMINATA', 14),
    (1, 6, 6, 'TERMINATA', 13),
    -- Gara 2
    (2, 4, 1, 'TERMINATA', 25),
    (2, 1, 2, 'TERMINATA', 20),
    (2, 6, 3, 'TERMINATA', 16),
    (2, 2, 4, 'TERMINATA', 15),
    (2, 5, 5, 'TERMINATA', 14),
    (2, 3, -1, 'SQUALIFICA', 0),

    -- Gara 3
    (3, 6, 1, 'TERMINATA', 25),
    (3, 3, 2, 'TERMINATA', 20),
    (3, 1, 3, 'TERMINATA', 16),
    (3, 5, 4, 'TERMINATA', 15),
    (3, 2, 5, 'TERMINATA', 14),
    (3, 4, 6, 'TERMINATA', 13),

    -- Gara 4
    (4, 3, 1, 'TERMINATA', 25),
    (4, 2, 2, 'TERMINATA', 20),
    (4, 1, 3, 'TERMINATA', 16),
    (4, 6, 4, 'TERMINATA', 15),
    (4, 5, 5, 'TERMINATA', 14),
    (4, 4, -1, 'INCIDENTE', 0),

    -- Gara 5
    (5, 5, 1, 'TERMINATA', 25),
    (5, 1, 2, 'TERMINATA', 20),
    (5, 3, 3, 'TERMINATA', 16),
    (5, 6, 4, 'TERMINATA', 15),
    (5, 4, 5, 'TERMINATA', 14),
    (5, 2, -1, 'GUASTO', 0),

    -- Gara 6
    (6, 1, -1, 'ISCRITTA', 0),
    (6, 2, -1, 'ISCRITTA', 0),
    (6, 3, -1, 'ISCRITTA', 0),
    (6, 4, -1, 'ISCRITTA', 0),
    (6, 5, -1, 'ISCRITTA', 0),
    (6, 6, -1, 'ISCRITTA', 0);

    
INSERT INTO gentleman (codice, quota, scuderia)
VALUES 
    (3, 50000, 'Scuderia1'),
    (7, 60000, 'Scuderia1'),
    (9, 55000, 'Scuderia2'),
    (10, 40000, 'Scuderia2'),
    (11, 70000, 'Scuderia2'),
    (17, 50000, 'Scuderia3'),
    (22, 62000, 'Scuderia3');