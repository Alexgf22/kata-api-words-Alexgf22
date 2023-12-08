

CREATE TABLE IF NOT EXISTS `wordleApi`.`Player` (
    `idPlayer` INT NOT NULL AUTO_INCREMENT,
    `User_name` VARCHAR(45) NULL,
    `Score` INT NULL,
    `Avatar_Img` BLOB NULL,
    `Team_idTeam` INT NOT NULL,
    PRIMARY KEY (`idPlayer`, `Team_idTeam`),
    INDEX `fk_Player_Team_idx` (`Team_idTeam` ASC) VISIBLE,
    CONSTRAINT `fk_Player_Team`
        FOREIGN KEY (`Team_idTeam`)
            REFERENCES `wordleApi`.`Team` (`idTeam`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            ) ENGINE = InnoDB;