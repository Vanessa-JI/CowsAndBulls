DROP DATABASE IF EXISTS cowsandbullsDB;
CREATE DATABASE cowsandbullsDB;

USE cowsandbullsDB;

CREATE TABLE games (
gameID INT PRIMARY KEY AUTO_INCREMENT,
answer CHAR(4) NOT NULL,
inprogress BOOLEAN DEFAULT false, 
win BOOLEAN);

INSERT INTO games (answer, inprogress, win)
VALUES 
	ROW ('1234', false, true), 
    ROW ('4519', false, true),
    ROW ('9021', false, false);
    

SELECT * from Games;