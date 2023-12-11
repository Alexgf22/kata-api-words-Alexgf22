

CREATE TABLE IF NOT EXISTS `wordleApi`.`Player` (
        `idPlayer` BIGINT NOT NULL AUTO_INCREMENT,
        `User_name` VARCHAR(45) NULL,
        `Score` INT NULL,
        `Avatar_Img` BLOB NULL,
        `Team_idTeam` BIGINT NOT NULL,
        PRIMARY KEY (`idPlayer`, `Team_idTeam`),
        INDEX `fk_Player_Team_idx` (`Team_idTeam` ASC) VISIBLE,
        CONSTRAINT `fk_Player_Team`
            FOREIGN KEY (`Team_idTeam`)
                REFERENCES `wordleApi`.`Team` (`idTeam`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
) ENGINE = InnoDB;




INSERT INTO `wordleApi`.`Player` (`User_name`, `Score`, `Avatar_Img`, `Team_idTeam`) VALUES
         ('Usuario1', 100, NULL, 1),
         ('Usuario2', 120, NULL, 1),
         ('Usuario3', 80, NULL, 2),
         ('Usuario4', 90, NULL, 2),
         ('Usuario5', 110, NULL, 3),
         ('Usuario6', 95, NULL, 3),
         ('Usuario7', 75, NULL, 4),
         ('Usuario8', 85, NULL, 4),
         ('Usuario9', 105, NULL, 5),
         ('Usuario10', 88, NULL, 5),
         ('Usuario11', 78, NULL, 6),
         ('Usuario12', 92, NULL, 6),
         ('Usuario13', 100, NULL, 7),
         ('Usuario14', 60, NULL, 7),
         ('Usuario15', 70, NULL, 8),
         ('Usuario16', 80, NULL, 8),
         ('Usuario17', 85, NULL, 9),
         ('Usuario18', 95, NULL, 9),
         ('Usuario19', 75, NULL, 10),
         ('Usuario20', 105, NULL, 10),
         ('Usuario21', 88, NULL, 11),
         ('Usuario22', 78, NULL, 11),
         ('Usuario23', 92, NULL, 12),
         ('Usuario24', 100, NULL, 12),
         ('Usuario25', 60, NULL, 13),
         ('Usuario26', 70, NULL, 13),
         ('Usuario27', 80, NULL, 14),
         ('Usuario28', 90, NULL, 14),
         ('Usuario29', 85, NULL, 15),
         ('Usuario30', 95, NULL, 15);

