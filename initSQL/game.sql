CREATE TABLE IF NOT EXISTS `wordleApi`.`Game` (
  `idGame` INT NOT NULL AUTO_INCREMENT,
  `Max_tries` INT NULL,
  `Description` VARCHAR(80) NULL,
  `Difficulty` ENUM('easy', 'normal', 'hard') NOT NULL,
  PRIMARY KEY (`idGame`)
    ) ENGINE = InnoDB;




INSERT INTO `wordleApi`.`Game` (`Max_tries`, `Description`, `Difficulty`) VALUES
      (6, 'A game with words that are all related to a specific time period.', 'normal'),
      (7, 'A game with words that are all related to a specific event.', 'hard'),
      (5, 'A game with words that are all related to a specific person.', 'easy'),
      (6, 'A game with words that are all related to a specific animal.', 'normal'),
      (7, 'A game with words that are all related to a specific plant.', 'hard'),
      (5, 'A game with words that are all related to a specific object.', 'easy'),
      (6, 'A game with words that are all related to a specific action.', 'normal'),
      (7, 'A game with words that are all related to a specific feeling.', 'hard'),
      (5, 'A game with words that are all related to a specific place.', 'easy'),
      (6, 'A game with words that are all related to a specific time period.', 'normal'),
      (7, 'A game with words that are all related to a specific event.', 'hard'),
      (5, 'A game with words that are all related to a specific person.', 'easy'),
      (6, 'A game with words that are all related to a specific animal.', 'normal'),
      (7, 'A game with words that are all related to a specific plant.', 'hard'),
      (5, 'A game with words that are all related to a specific object.', 'easy'),
      (6, 'A game with words that are all related to a specific action.', 'normal'),
      (7, 'A game with words that are all related to a specific feeling.', 'hard'),
      (5, 'A game with words that are all related to a specific place.', 'easy'),
      (6, 'A game with words that are all related to a specific time period.', 'normal'),
      (7, 'A game with words that are all related to a specific event.', 'hard'),
      (5, 'A game with words that are all related to a specific person.', 'easy'),
      (6, 'A game with words that are all related to a specific animal.', 'normal'),
      (7, 'A game with words that are all related to a specific plant.', 'hard'),
      (5, 'A game with words that are all related to a specific object.', 'easy'),
      (6, 'A game with words that are all related to a specific action.', 'normal'),
      (7, 'A game with words that are all related to a specific feeling.', 'hard'),
      (5, 'A game with words that are all related to a specific place.', 'easy'),
      (6, 'A game with words that are all related to a specific time period.', 'normal'),
      (7, 'A game with words that are all related to a specific event.', 'hard'),
      (5, 'A game with words that are all related to a specific person.', 'easy'),
      (6, 'A game with words that are all related to a specific animal.', 'normal'),
      (7, 'A game with words that are all related to a specific plant.', 'hard');
