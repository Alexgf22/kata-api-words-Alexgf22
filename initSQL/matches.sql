

CREATE TABLE IF NOT EXISTS `wordleapi`.`Matches` (
     `idMatch` BIGINT NOT NULL AUTO_INCREMENT,
     `Word` VARCHAR(45) NULL,
     `Score` INT NULL,
     `N_tries` INT NULL,
     `Date_time` DATETIME NULL,
     `Player_idPlayer` BIGINT NOT NULL,
     `Player_Team_idTeam` BIGINT NOT NULL,
     `Game_idGame` BIGINT NOT NULL,
     PRIMARY KEY (`idMatch`, `Player_idPlayer`, `Player_Team_idTeam`, `Game_idGame`),
     INDEX `fk_Match_Player1_idx` (`Player_idPlayer` ASC, `Player_Team_idTeam` ASC) VISIBLE,
     INDEX `fk_Match_Game1_idx` (`Game_idGame` ASC) VISIBLE,
     CONSTRAINT `fk_Match_Player1`
         FOREIGN KEY (`Player_idPlayer` , `Player_Team_idTeam`)
             REFERENCES `wordleapi`.`Player` (`idPlayer` , `Team_idTeam`)
             ON DELETE NO ACTION
             ON UPDATE NO ACTION,
     CONSTRAINT `fk_Match_Game1`
         FOREIGN KEY (`Game_idGame`)
             REFERENCES `wordleapi`.`Game` (`idGame`)
             ON DELETE NO ACTION
             ON UPDATE NO ACTION
) ENGINE = InnoDB;




# En los valores de word hay que pillar una palabra aleatoria de la lista en cada fila
INSERT INTO `wordleapi`.`Matches` (
    `Word`,
    `Score`,
    `N_tries`,
    `Date_time`,
    `Player_idPlayer`,
    `Player_Team_idTeam`,
    `Game_idGame`
) VALUES
      ('Sun', 100, 3, '2023-01-01 12:00:00', 1, 1, 1),
      ('Moon', 80, 5, '2023-01-02 14:30:00', 2, 1, 2),
      ('Cat', 70, 5, '2023-01-03 10:15:00', 3, 2, 3),
      ('Dog', 80, 4, '2023-01-04 08:45:00', 4, 2, 4),
      ('Table', 60, 7, '2023-01-05 19:30:00', 5, 3, 5),
      ('Chair', 90, 2, '2023-01-06 22:15:00', 6, 3, 6),
      ('House', 80, 5, '2023-01-07 14:00:00', 7, 4, 7),
      ('Tree', 70, 6, '2023-01-08 16:45:00', 8, 4, 8),
      ('Sea', 60, 7, '2023-01-09 11:30:00', 9, 5, 9),
      ('Flower', 70, 5, '2023-01-10 10:00:00', 10, 5, 10),
      ('Blue', 80, 4, '2023-01-11 13:45:00', 11, 6, 11),
      ('Red', 90, 3, '2023-01-12 18:20:00', 12, 6, 12),
      ('Sky', 100, 2, '2023-01-13 08:00:00', 13, 7, 13),
      ('Wind', 60, 7, '2023-01-14 21:15:00', 14, 7, 14),
      ('Water', 70, 6, '2023-01-15 16:30:00', 15, 8, 15),
      ('River', 80, 5, '2023-01-16 12:10:00', 16, 8, 16),
      ('Mountain', 70, 5, '2023-01-17 14:50:00', 17, 9, 17),
      ('Garden', 80, 4, '2023-01-18 10:30:00', 18, 9, 18),
      ('City', 90, 2, '2023-01-19 09:15:00', 19, 10, 19),
      ('Child', 90, 3, '2023-01-20 17:40:00', 20, 10, 20),
      ('Girl', 80, 5, '2023-01-21 22:00:00', 21, 11, 21),
      ('Boy', 70, 5, '2023-01-22 13:20:00', 22, 11, 22),
      ('Mom', 60, 7, '2023-01-23 19:05:00', 23, 12, 23),
      ('Dad', 70, 6, '2023-01-24 10:45:00', 24, 12, 24),
      ('Friend', 70, 5, '2023-01-25 15:30:00', 25, 13, 25),
      ('Sweet', 80, 4, '2023-01-26 11:10:00', 26, 13, 26),
      ('Bread', 90, 2, '2023-01-27 14:55:00', 27, 14, 27),
      ('Milk', 90, 3, '2023-01-28 18:25:00', 28, 14, 28),
      ('Game', 80, 5, '2023-01-29 09:40:00', 29, 15, 29),
      ('Ball', 90, 2, '2023-01-30 22:50:00', 30, 15, 30);

