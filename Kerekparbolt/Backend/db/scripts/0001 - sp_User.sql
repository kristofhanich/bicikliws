DELIMITER $$
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
DELIMITER ;