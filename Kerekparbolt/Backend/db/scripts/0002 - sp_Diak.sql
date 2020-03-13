DELIMITER $$
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
DELIMITER ;