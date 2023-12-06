CREATE TABLE IF NOT EXISTS `wordleApi`.`Game` (
  `idGame` INT NOT NULL,
  `Max_tries` INT NULL,
  `Description` VARCHAR(80) NULL,
  `Difficulty` ENUM('easy', 'normal', 'hard') NULL,
  PRIMARY KEY (`idGame`))
    ENGINE = InnoDB