

CREATE TABLE IF NOT EXISTS `wordleApi`.`Game_has_Word` (
   `Game_idGame` INT NOT NULL,
   `Word_idWord` INT NOT NULL,
   `Difficulty` ENUM('easy', 'normal', 'hard') NOT NULL,
   PRIMARY KEY (`Game_idGame`, `Word_idWord`),
   INDEX `fk_Game_has_Word1_Word1_idx` (`Word_idWord` ASC) VISIBLE,
   INDEX `fk_Game_has_Word1_Game1_idx` (`Game_idGame` ASC) VISIBLE,
   CONSTRAINT `fk_Game_has_Word1_Game1`
       FOREIGN KEY (`Game_idGame`)
           REFERENCES `wordleApi`.`Game` (`idGame`)
           ON DELETE NO ACTION
           ON UPDATE NO ACTION,
   CONSTRAINT `fk_Game_has_Word1_Word1`
       FOREIGN KEY (`Word_idWord`)
           REFERENCES `wordleApi`.`Word` (`idWord`)
           ON DELETE NO ACTION
           ON UPDATE NO ACTION)
    ENGINE = InnoDB;