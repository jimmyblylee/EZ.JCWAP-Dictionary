-- drop user
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ANSI';
USE JCWAP_DICT;
DROP PROCEDURE IF EXISTS JCWAP_DICT.DROP_USER_IF_EXISTS;
DELIMITER $$
CREATE PROCEDURE JCWAP_DICT.DROP_USER_IF_EXISTS()
BEGIN
  DECLARE FOO BIGINT DEFAULT 0;
  SELECT COUNT(*)
  INTO FOO
    FROM MYSQL.USER
      WHERE USER = 'DICT_USER' AND  HOST = '%';
   IF FOO > 0 THEN
         DROP USER 'DICT_USER'@'%';
  END IF;
END ;$$
DELIMITER ;
CALL JCWAP_DICT.DROP_USER_IF_EXISTS();
DROP PROCEDURE IF EXISTS JCWAP_DICT.DROP_USERS_IF_EXISTS;
SET SQL_MODE=@OLD_SQL_MODE;

-- create user
CREATE USER 'DICT_USER'@'%' IDENTIFIED BY 'DICT_USER_JCWAP';