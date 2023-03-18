DROP DATABASE IF EXISTS cowsandbullsDB;
CREATE DATABASE cowsandbullsDB;

USE cowsandbullsDB;

CREATE TABLE games (
gameID INT PRIMARY KEY AUTO_INCREMENT,
answer CHAR(10) NOT NULL,
inprogress BOOLEAN DEFAULT false, 
win BOOLEAN);

INSERT INTO games (answer, inprogress, win)
VALUES 
	ROW ('1, 2, 3, 4', false, true), 
    ROW ('4, 5, 1, 9', false, true),
    ROW ('9, 0, 2, 1', false, false);
    
    