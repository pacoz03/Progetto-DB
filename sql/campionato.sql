DROP SCHEMA campionato;
CREATE DATABASE campionato;

USE campionato;

CREATE TABLE costruttore (
    ragionesociale VARCHAR(40),
    nome VARCHAR(30) NOT NULL,
    sede VARCHAR(30) NOT NULL,
    ncomponenti INT NOT NULL DEFAULT 0,
    PRIMARY KEY (ragionesociale)
);

CREATE TABLE circuito (
    nome VARCHAR(30),
    sede VARCHAR(30) NOT NULL,
    lunghezza DECIMAL(7 , 3 ) NOT NULL,
    ncurve INT NOT NULL,
    PRIMARY KEY (nome),
    CHECK ( 
		ncurve > 0 AND lunghezza > 0.0
    )
);

CREATE TABLE scuderia (
    nome VARCHAR(30),
    sede VARCHAR(30) NOT NULL,
    PRIMARY KEY (nome)
);

CREATE TABLE vettura (
    ngara INT,
    modello VARCHAR(20) NOT NULL,
    scuderia VARCHAR(30) NOT NULL,
    punteggiototale INT DEFAULT 0,
    PRIMARY KEY (ngara),
    FOREIGN KEY (scuderia)
        REFERENCES scuderia (nome)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE pilota (
    codice INT AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    cognome VARCHAR(30) NOT NULL,
    datanascita DATE NOT NULL,
    nazionalita VARCHAR(30) NOT NULL,
    tipopilota ENUM('PRO','AM') NOT NULL,
    dataprimalicenza DATE,
    nlicenze INT DEFAULT 0,
    vettura INT,
    PRIMARY KEY (codice),
	FOREIGN KEY (vettura) 
		REFERENCES vettura(ngara)
		ON UPDATE CASCADE
        ON DELETE SET NULL,
	CHECK (
        (tipopilota = 'PRO' AND dataprimalicenza IS NULL AND nlicenze > 0) OR
        (tipopilota = 'AM' AND dataprimalicenza IS NOT NULL AND (nlicenze = 0 OR nlicenze IS NULL) AND dataprimalicenza > datanascita)
    )
);

CREATE TABLE gara (
    codice INT AUTO_INCREMENT,
    tipo ENUM('BAGNATA', 'ASCIUTTA') NOT NULL,
    durata DECIMAL(6 , 2 ) NOT NULL,
    dataGara DATE NOT NULL,
    nome VARCHAR(30) NOT NULL,
    circuito VARCHAR(30) NOT NULL,
    FOREIGN KEY (circuito)
        REFERENCES circuito (nome)
        ON UPDATE CASCADE 
        ON DELETE NO ACTION,
    PRIMARY KEY (codice)
);
CREATE TABLE componente (
    codice INT AUTO_INCREMENT,
    vettura INT NOT NULL,
    costruttore VARCHAR(40) NOT NULL,
    dataCreazione DATE NOT NULL,
    cilindrata INT,
    tipomotore ENUM('TURBO','ASPIRATO'),
    ncilindri INT,
    materiale VARCHAR(20),
    nmarce TINYINT,
    peso DECIMAL(6 , 2 ),
    tipocomponente ENUM('MOTORE', 'TELAIO', 'CAMBIO'),
    FOREIGN KEY (costruttore)
        REFERENCES costruttore (ragionesociale)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    FOREIGN KEY (vettura)
        REFERENCES vettura (ngara)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY (codice),
    CHECK (
		(tipocomponente= 'MOTORE' AND cilindrata> 0 AND tipomotore IS NOT NULL AND ncilindri > 0 AND nmarce IS NULL AND materiale IS NULL AND peso IS NULL) OR
        (tipocomponente= 'TELAIO' AND materiale IS NOT NULL AND peso > 0.0 AND cilindrata IS NULL AND tipomotore IS NULL AND ncilindri IS NULL AND nmarce IS NULL) OR
        (tipocomponente= 'CAMBIO' AND nmarce in (7,8) AND cilindrata IS NULL AND tipomotore IS NULL AND ncilindri IS NULL AND materiale IS NULL AND peso IS NULL)
    )
);


CREATE TABLE partecipazione (
    gara INT NOT NULL,
    vettura INT NOT NULL,
    posizione INT NOT NULL DEFAULT 0,
    esito ENUM('ISCRITTA', 'TERMINATA','SQUALIFICA','GUASTO','INCIDENTE') NOT NULL DEFAULT 'ISCRITTA',
    punteggio INT DEFAULT 0,
    FOREIGN KEY (vettura)
        REFERENCES vettura (ngara)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY (gara, vettura),
    CHECK((esito != 'TERMINATA' AND punteggio = 0 AND posizione = -1) OR (esito = 'TERMINATA' AND posizione > 0))
);

CREATE TABLE gentleman (
    codice INT,
    quota INT NOT NULL,
    scuderia VARCHAR(30),
    PRIMARY KEY (codice),
    FOREIGN KEY (codice)
        REFERENCES pilota (codice)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (scuderia)
        REFERENCES scuderia (nome)
        ON UPDATE CASCADE ON DELETE NO ACTION,
    CHECK (quota > 0)
);

-- Trigger per assicurarsi che solo i piloti di tipo 'AM' possano essere gentleman driver e che
-- possano finanziare solo la scuderia per cui corrono
DELIMITER //
CREATE TRIGGER check_gd_am_before_insert
BEFORE INSERT ON gentleman
FOR EACH ROW
BEGIN
    DECLARE tipo_pilota VARCHAR(3);
    DECLARE pilota_scuderia VARCHAR(30);
    SELECT tipopilota INTO tipo_pilota FROM pilota WHERE codice = NEW.codice;
    IF tipo_pilota <> 'AM' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Solo i piloti di tipo ''AM'' possono essere gentleman driver';
    END IF;
    SELECT scuderia INTO pilota_scuderia FROM vettura JOIN pilota ON vettura.ngara = pilota.vettura WHERE codice = NEW.codice;
    IF pilota_scuderia <> NEW.scuderia THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'I piloti AM possono finanziare solo la scuderia a cui appartengono';
    END IF;
END;
// DELIMITER ;

-- Trigger per l'incremento di ncomponenti quando viene aggiunto un componente
DELIMITER //
CREATE TRIGGER incrementa_ncomponenti
AFTER INSERT ON componente
FOR EACH ROW
BEGIN
    UPDATE costruttore
    SET ncomponenti = ncomponenti + 1
    WHERE ragionesociale = NEW.costruttore;
END;
// DELIMITER ;

-- Trigger per la diminuzione di ncomponenti quando viene tolto un componente
DELIMITER //
CREATE TRIGGER diminuisci_ncomponenti
AFTER DELETE ON componente
FOR EACH ROW
BEGIN
    UPDATE costruttore
    SET ncomponenti = ncomponenti - 1
    WHERE ragionesociale = OLD.costruttore;
END;
// DELIMITER ;

-- Trigger per l'aggiornamento di ncomponenti quando viene modificato il costruttore di un componente
DELIMITER //
CREATE TRIGGER aggiorna_ncomponenti
AFTER UPDATE ON componente
FOR EACH ROW
BEGIN
    IF NEW.costruttore <> OLD.costruttore THEN
        UPDATE costruttore
        SET ncomponenti = ncomponenti - 1
        WHERE ragionesociale = OLD.costruttore;
        
        UPDATE costruttore
        SET ncomponenti = ncomponenti + 1
        WHERE ragionesociale = NEW.costruttore;
    END IF;
END;
// DELIMITER ;

-- Trigger per l'aggiornamento del punteggioTotale di una vettura dopo la partecipazione ad una partecipazione
DELIMITER //
CREATE TRIGGER aggiorna_punti
AFTER INSERT ON partecipazione
FOR EACH ROW
BEGIN
	UPDATE vettura
    SET punteggioTotale = punteggioTotale + NEW.punteggio
    WHERE vettura.ngara = NEW.vettura;
END
// DELIMITER ;

-- Trigger per la cancellazione della partecipazione a una gara
DELIMITER //
CREATE TRIGGER aggiorna_punteggio_after_delete
AFTER DELETE ON partecipazione
FOR EACH ROW
BEGIN
    -- Ripristina il punteggio nell'auto partecipante
    UPDATE vettura
    SET punteggiototale = punteggiototale - OLD.punteggio
    WHERE ngara = OLD.vettura;
END;
// DELIMITER ;

-- Trigger per l'aggiornamento del punteggio dopo la modifica della partecipazione a una gara
DELIMITER //
CREATE TRIGGER aggiorna_punteggio_after_update
AFTER UPDATE ON partecipazione
FOR EACH ROW
BEGIN
    -- Verifica se sono stati variati parametri critici
    IF OLD.vettura <> NEW.vettura THEN
        -- Aggiorna il punteggio nell'auto precedente
        UPDATE vettura
        SET punteggiototale = punteggiototale - OLD.punteggio
        WHERE ngara = OLD.vettura;

        -- Aggiorna il punteggio nell'auto nuova
        UPDATE vettura
        SET punteggiototale = punteggiototale + NEW.punteggio
        WHERE ngara = NEW.vettura;
    ELSE 
		-- Aggiorna il punteggio totale dell'auto
        UPDATE vettura
        SET punteggiototale = punteggiototale - OLD.punteggio + NEW.punteggio
        WHERE ngara = NEW.vettura;
    END IF;
END;
// DELIMITER ;