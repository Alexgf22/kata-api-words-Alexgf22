

CREATE TABLE IF NOT EXISTS `wordleApi`.`Match` (
       `idMatch` INT NOT NULL,
       `Word` VARCHAR(45) NULL,
       `Score` INT NULL,
       `N_tries` INT NULL,
       `Date_time` DATETIME NULL,
       `Player_idPlayer` INT NOT NULL,
       `Player_Team_idTeam` INT NOT NULL,
       `Game_idGame` INT NOT NULL,
       PRIMARY KEY (`idMatch`, `Player_idPlayer`, `Player_Team_idTeam`, `Game_idGame`),
       INDEX `fk_Match_Player1_idx` (`Player_idPlayer` ASC, `Player_Team_idTeam` ASC) VISIBLE,
       INDEX `fk_Match_Game1_idx` (`Game_idGame` ASC) VISIBLE,
       CONSTRAINT `fk_Match_Player1`
           FOREIGN KEY (`Player_idPlayer` , `Player_Team_idTeam`)
               REFERENCES `wordleApi`.`Player` (`idPlayer` , `Team_idTeam`)
               ON DELETE NO ACTION
               ON UPDATE NO ACTION,
       CONSTRAINT `fk_Match_Game1`
           FOREIGN KEY (`Game_idGame`)
               REFERENCES `wordleApi`.`Game` (`idGame`)
               ON DELETE NO ACTION
               ON UPDATE NO ACTION)
    ENGINE = InnoDB
