SET SQL_SAFE_UPDATES = 0;

INSERT INTO costruttore (ragionesociale,nome,sede,ncomponenti) VALUES
	('Brembo','Brembo','IT',0),
    ('Magneti Marelli','Magneti Marelli:','IT',0),
    ('Hewland Engineering','Hewland Engineering','GB',0),
    ('AP Racing','AP Racing','GB',0),
    ('Xtrac','Xtrac','GB',0);

INSERT INTO scuderia(nome,sede) VALUES
	('Mercedes-AMG',' Brackley'),
    ('Scuderia Ferrari', 'Maranello'),
    ('Red Bull Racing','Milton Keynes'),
    ('McLaren F1 Team', 'Woking'),
    ('Scuderia AlphaTauri', 'Faenza');

INSERT INTO vettura(ngara, modello, scuderia)
VALUES
    (1, 'Mercedes-AMG', 'Mercedes-AMG'),
    (2, 'Ferrari SF21', 'Scuderia Ferrari'),
    (3, 'Red Bull RB16B', 'Red Bull Racing'),
    (4, 'McLaren MCL35M', 'McLaren F1 Team'),
    (5, 'AlphaTauri AT02', 'Scuderia AlphaTauri'),
    (6, 'Mercedes-AMG', 'Mercedes-AMG'),
    (7, 'Ferrari SF21', 'Scuderia Ferrari');
    
INSERT INTO pilota (nome, cognome, datanascita, nazionalita, tipopilota, dataprimalicenza, nlicenze, vettura) VALUES 
    ('Lewis', 'Hamilton', '1985-01-07', 'GBR', 'AM', '2000-03-04', 0, 1),
    ('Max', 'Verstappen', '1997-09-30', 'FIN', 'PRO', NULL, 3, 2),
    ('Valterri', 'Bottas', '1989-08-28', 'FIN', 'AM', '2001-06-21', 0, 3),
    ('Charles', 'Leclerc', '1997-10-16', 'MCO', 'AM', '2010-05-25', 0, 4),
    ('Lando', 'Norris', '1999-11-13', 'GBR', 'AM', '2012-07-27', 0, 5),
    ('Sergio', 'Perez', '1990-01-26', 'MEX', 'PRO', NULL, 4, 6),
    ('Carlo', 'Sainz', '1989-09-01', 'AUS', 'PRO', NULL, 5, 7);


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
    


    

    
-- un pilota può guidare più vettura
INSERT INTO componente (vettura, costruttore, dataCreazione, cilindrata, tipomotore, ncilindri, materiale, nmarce, peso, tipocomponente)
VALUES 
    (1, 'Brembo', '2023-01-01', 2000, 'ASPIRATO', 12, NULL, NULL, NULL, 'MOTORE'),
    (2, 'Magneti Marelli', '2023-02-15', 1800, 'ASPIRATO', 10, NULL, NULL, NULL,  'MOTORE'),
    (3, 'Hewland Engineering', '2023-03-10', NULL, NULL, NULL, NULL, 8, NULL, 'CAMBIO'),
    (4, 'AP Racing', '2023-04-05', NULL, NULL, NULL, 'Carbonio', NULL, 5.5, 'TELAIO'),
    (5, 'Xtrac', '2023-05-20', 1600, 'ASPIRATO', 8, NULL, NULL, NULL,  'MOTORE'),
    (6, 'Brembo', '2023-06-08', 2200, 'ASPIRATO', 12,NULL, NULL, NULL,  'MOTORE'),
    (7, 'Magneti Marelli', '2023-07-22', 1800, 'ASPIRATO',6,NULL, NULL, NULL,  'MOTORE'),
    (1, 'Hewland Engineering', '2023-08-15', NULL, NULL, NULL, NULL, 8, NULL, 'CAMBIO'),
    (2, 'AP Racing', '2023-09-03', NULL, NULL, NULL, 'Acciaio', NULL, 5.8, 'TELAIO'),
    (3, 'Xtrac', '2023-10-12', 1700, 'ASPIRATO', 8, NULL, NULL, NULL,  'MOTORE'),
    (4, 'Brembo', '2023-11-26', 2000, 'ASPIRATO', 12, NULL, NULL, NULL,  'MOTORE'),
    (5, 'Magneti Marelli', '2023-12-10', 1900, 'ASPIRATO', 10, NULL, NULL, NULL,  'MOTORE');


INSERT INTO gara(tipo,durata,dataGara,nome,circuito) VALUES
	('ASCIUTTA', 120.5, '2023-01-01', 'Grand Prix di Monza', 'Monza'),
	('ASCIUTTA', 130.0, '2023-02-15', 'British Grand Prix', 'Silverstone'),
	('ASCIUTTA', 110.0, '2023-03-10', 'Austrian Grand Prix', 'Red Bull Ring'),
	('ASCIUTTA', 120.0, '2023-04-05', 'Belgian Grand Prix', 'Circuit de Spa-Francorchamps'),
	('ASCIUTTA', 125.0, '2023-05-20', 'Italian Grand Prix', 'Autodromo Nazionale di Monza'),
	('ASCIUTTA', 115.0, '2023-06-08', 'Spanish Grand Prix', 'Circuit de Barcelona-Catalunya'),
	('ASCIUTTA', 110.0, '2023-07-22', 'Hungarian Grand Prix', 'Hungaroring'),
	('ASCIUTTA', 125.0, '2023-08-15', 'French Grand Prix', 'Circuit Paul Ricard'),
	('ASCIUTTA', 115.0, '2023-09-03', 'San Marino Grand Prix', 'Autodromo Enzo e Dino Ferrari'),
	('ASCIUTTA', 120.0, '2023-10-12', 'Russian Grand Prix', 'Sochi Autodrom'),
	('ASCIUTTA', 130.0, '2023-11-26', 'Abu Dhabi Grand Prix', 'Yas Marina Circuit'),
	('ASCIUTTA', 140.0, '2023-12-10', 'Monaco Grand Prix', 'Circuit de Monaco');
    
-- Per la Gara 1
INSERT INTO partecipazione (gara, vettura, posizione, esito, punteggio)
VALUES 
    (1, 1, 1, 'TERMINATA', 25),
    (1, 2, -1, 'GUASTO', 0),
    (1, 3, -1, 'INCIDENTE', 0);

-- Per la Gara 2
INSERT INTO partecipazione (gara, vettura, posizione, esito, punteggio)
VALUES 
    (2, 4, 1, 'TERMINATA', 25),
    (2, 5, -1, 'SQUALIFICA', 0),
    (2, 6, 2, 'TERMINATA', 20);

-- Per la Gara 3
INSERT INTO partecipazione (gara, vettura, posizione, esito, punteggio)
VALUES 
    (3, 4, 1, 'TERMINATA', 25),
    (3, 1, 2, 'TERMINATA', 20),
    (3, 2, -1, 'GUASTO', 0);

-- Per la Gara 4
INSERT INTO partecipazione (gara, vettura, posizione, esito, punteggio)
VALUES 
    (4, 5, 1, 'TERMINATA', 25),
    (4, 6, 2, 'TERMINATA', 20),
    (4, 4, -1, 'INCIDENTE', 0);

-- Per la Gara 5
INSERT INTO partecipazione (gara, vettura, posizione, esito, punteggio)
VALUES 
    (5, 1, 1, 'TERMINATA', 25),
    (5, 2, -1, 'GUASTO', 0),
    (5, 4, -1, 'SQUALIFICA', 0);

-- Per la Gara 6
INSERT INTO partecipazione (gara, vettura, posizione, esito, punteggio)
VALUES 
    (6, 4, 1, 'TERMINATA', 25),
    (6, 5, 2, 'TERMINATA', 20),
    (6, 6, -1, 'GUASTO', 0);    
    
INSERT INTO gentleman (codice, quota, scuderia)
VALUES 
    (1, 50000, 'Mercedes-AMG'),
    (3, 60000, 'Red Bull Racing'),
    (5, 55000, 'Scuderia AlphaTauri');    