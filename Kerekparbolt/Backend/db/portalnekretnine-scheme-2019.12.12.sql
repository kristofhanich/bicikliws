DROP SCHEMA IF EXISTS `DiakDB`;

CREATE SCHEMA IF NOT EXISTS `DiakDB` DEFAULT CHARACTER SET utf8 ;

USE DiakDB;

CREATE TABLE IF NOT EXISTS User
(
	Id INT8 AUTO_INCREMENT PRIMARY KEY,
	UniqID VARCHAR(128) NOT NULL UNIQUE,
	Email VARCHAR(255) NOT NULL,
	Jelszo VARCHAR(255) NOT NULL,
	Role VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS Diak
(
	Id INT8 AUTO_INCREMENT PRIMARY KEY,
  UniqID VARCHAR(128) NOT NULL UNIQUE,
	Nev VARCHAR(255) NOT NULL,
  Kreditek INT NOT NULL
);

﻿DELIMITER $$
CREATE OR REPLACE PROCEDURE UserCreate(OUT paramId INT, IN paramUniqID VARCHAR(128), IN paramEmail VARCHAR(255), IN paramJelszo VARCHAR(255), IN paramRole VARCHAR(32))
BEGIN
    INSERT INTO 
		  User
	    (UniqID, Email, Jelszo, Role)
    VALUES
		(paramUniqID, paramEmail, paramJelszo, paramRole);


    SELECT LAST_INSERT_ID() as paramId;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE PROCEDURE UserFindByUniqID(IN paramUniqID VARCHAR(255))
BEGIN
    SELECT 
  		User.Id,
      User.UniqID,
      User.Email,
      User.Jelszo,
      User.Role
    FROM
		  User
    WHERE 
		  User.UniqID = paramUniqID;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE PROCEDURE UserFindByCredentials(IN paramEmail VARCHAR(255), IN paramJelszo VARCHAR(255))
BEGIN
    SELECT 
  		User.Id,
      User.UniqID,
      User.Email,
      User.Jelszo,
      User.Role
    FROM
		  User
    WHERE 
		  User.Email = paramEmail
      AND
      User.Jelszo = paramJelszo;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE PROCEDURE UserFindByEmail(IN paramEmail VARCHAR(255))
BEGIN
    SELECT 
  		User.Id,
      User.UniqID,
      User.Email,
      User.Jelszo,
      User.Role
    FROM
		  User
    WHERE 
		  User.Email = paramEmail;
END$$
DELIMITER ;


DELIMITER $$
CREATE OR REPLACE PROCEDURE DiakDelete(IN paramId INT)
BEGIN
    DELETE FROM 
		  Diak
    WHERE 
		  Diak.Id = paramId;
END$$
DELIMITER ;﻿DELIMITER $$
CREATE OR REPLACE PROCEDURE DiakCreate(OUT paramId INT, IN paramName VARCHAR(255), IN paramKreditek INT)
BEGIN
    INSERT INTO 
		  Diak (Nev, Kreditek)
    VALUES
		(paramName, paramKreditek);


    SELECT LAST_INSERT_ID() as paramId;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE PROCEDURE DiakUpdate(IN paramId INT, IN paramName VARCHAR(255), IN paramKreditek INT)
BEGIN
    UPDATE 
		  Diak
    SET
		  Diak.Nev = paramName,
      Diak.Kreditek = paramKreditek
    WHERE
      Diak.Id = paramId;
END$$
DELIMITER ;


DELIMITER $$
CREATE OR REPLACE PROCEDURE DiakFindByName(IN paramName VARCHAR(255))
BEGIN
    SELECT 
  		Diak.Id,
      Diak.UniqID,
      Diak.Nev,
      Diak.Kreditek
    FROM
		  Diak
    WHERE 
		  Diak.Nev = paramName;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE PROCEDURE DiakGetAll()
BEGIN
    SELECT 
		  Diak.Id,
      Diak.UniqID,
      Diak.Nev,
      Diak.Kreditek
    FROM
		Diak;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE PROCEDURE DiakGetByID(IN paramId INT)
BEGIN
    SELECT 
		  Diak.Id,
      Diak.UniqID,
      Diak.Nev,
      Diak.Kreditek
    FROM
		  Diak
    WHERE 
		  Diak.Id = paramId;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE PROCEDURE DiakDelete(IN paramId INT)
BEGIN
    DELETE FROM 
		  Diak
    WHERE 
		  Diak.Id = paramId;
END$$
DELIMITER ;﻿INSERT INTO
	User
	(UniqID, Email, Jelszo, Role)
VALUES
  ('be5d0cd7-c121-4d29-b939-3cadd483be4e', 'wasyster@gmail.com', 'password', 'ROLE_ADMIN'),
  ('1e735fd6-5032-43b8-848c-c1a3de40b8d8', 'elsoegon@test.com', 'password1', 'ROLE_CLIENT'),
  ('da893cb2-f8c7-4a0d-a4b5-8d0543c68867', 'masodikmiksa@test.com', 'password2', 'ROLE_CLIENT'),
  ('a52e9283-793c-41ec-b057-c24b256ed22e', 'harmadikhuba@test.com', 'password3', 'ROLE_CLIENT'),
  ('12785bfc-fbef-4256-8118-8ebd2179d2d3', 'negyediknero@test.com', 'password4', 'ROLE_CLIENT'),
  ('d89ecc28-01a0-47eb-8aea-8d5f830f5eed', 'otodikodon@test.com', 'password5', 'ROLE_CLIENT'),
  ('fa2149af-4662-4089-b8de-63402efeb8c9', 'hatodikhanibal@test.com', 'password6', 'ROLE_CLIENT'),
  ('fa8e4ae6-5f54-4640-8f91-71c49a61b3a7', 'hetedikheraclius@test.com', 'password7', 'ROLE_CLIENT');


INSERT INTO
	Diak
	(UniqID, Nev, Kreditek)
VALUES
  ('1e735fd6-5032-43b8-848c-c1a3de40b8d8', 'Első Egon', 52),
  ('da893cb2-f8c7-4a0d-a4b5-8d0543c68867', 'Második Miksa', 25),
  ('a52e9283-793c-41ec-b057-c24b256ed22e', 'Harmadik Huba', 10),
  ('12785bfc-fbef-4256-8118-8ebd2179d2d3', 'Negyedik Néró', 89),
  ('d89ecc28-01a0-47eb-8aea-8d5f830f5eed', 'Ötödik Ödön', 69),
  ('fa2149af-4662-4089-b8de-63402efeb8c9', 'Hatodik Hanibál', 89),
  ('fa8e4ae6-5f54-4640-8f91-71c49a61b3a7', 'Hetedik Heraclius', 39);
