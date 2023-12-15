

CREATE TABLE IF NOT EXISTS `wordleapi`.`Game_has_Word` (
   `Game_idGame` BIGINT NOT NULL,
   `Word_idWord` BIGINT NOT NULL,
   `Difficulty` ENUM('EASY', 'NORMAL', 'HARD') NOT NULL,
   PRIMARY KEY (`Game_idGame`, `Word_idWord`),
   INDEX `fk_Game_has_Word1_Word1_idx` (`Word_idWord` ASC) VISIBLE,
   INDEX `fk_Game_has_Word1_Game1_idx` (`Game_idGame` ASC) VISIBLE,
   CONSTRAINT `fk_Game_has_Word1_Game1`
       FOREIGN KEY (`Game_idGame`)
           REFERENCES `wordleapi`.`Game` (`idGame`)
           ON DELETE NO ACTION
           ON UPDATE NO ACTION,
   CONSTRAINT `fk_Game_has_Word1_Word1`
       FOREIGN KEY (`Word_idWord`)
           REFERENCES `wordleapi`.`Word` (`idWord`)
           ON DELETE NO ACTION
           ON UPDATE NO ACTION)
    ENGINE = InnoDB;


