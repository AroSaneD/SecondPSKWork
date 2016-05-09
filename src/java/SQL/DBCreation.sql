/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Aron
 * Created: Apr 26, 2016
 */
DROP TABLE GameMatch;
DROP TABLE PlayerSession;
DROP TABLE Player;


CREATE TABLE Player
(
ID                  INTEGER                 GENERATED ALWAYS AS IDENTITY
                                            (START WITH 1, INCREMENT BY 1),
UID                 VARCHAR(255)            UNIQUE NOT NULL,
PlayerName          VARCHAR(255)            UNIQUE NOT NULL,
PlayerPoints        INTEGER                 NOT NULL DEFAULT 0,
Password            VARCHAR(255)            NOT NULL,
Email               VARCHAR(255)            UNIQUE NOT NULL,

PRIMARY KEY (ID)
);


CREATE TABLE GameMatch
(
ID                  INTEGER                 GENERATED ALWAYS AS IDENTITY
                                            (START WITH 1, INCREMENT BY 1),
PlayerOne           INTEGER                 NOT NULL,
PlayerTwo           INTEGER                 ,
VictoriousPlayer    INTEGER                 ,
PlayerOneScore      DECIMAL                 ,
PlayerTwoScore      DECIMAL                 ,
DatePlayed          DATE                    NOT NULL,

PRIMARY KEY (ID),
FOREIGN KEY (PlayerOne) REFERENCES Player(ID),
FOREIGN KEY (PlayerTwo) REFERENCES Player(ID)
);


CREATE TABLE PlayerSession
(
ID                  INTEGER                 GENERATED ALWAYS AS IDENTITY
                                            (START WITH 1, INCREMENT BY 1),
Player              INTEGER                 NOT NULL,
LoginDate           TIMESTAMP               NOT NULL,

PRIMARY KEY (ID),
FOREIGN KEY (Player) REFERENCES Player(ID)
);