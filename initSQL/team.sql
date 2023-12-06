


CREATE TABLE IF NOT EXISTS `wordleApi`.`Team` (
  `idTeam` INT NOT NULL,
  `Score` INT NULL,
  `Team_name` VARCHAR(45) NULL,
  `Badge` BLOB NULL,
  PRIMARY KEY (`idTeam`))
    ENGINE = InnoDB