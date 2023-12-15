CREATE TABLE IF NOT EXISTS `wordleapi`.`Game` (
  `idGame` BIGINT  NOT NULL AUTO_INCREMENT,
  `Max_tries` INT NULL,
  `Description` VARCHAR(80) NULL,
  `Difficulty` ENUM('EASY', 'NORMAL', 'HARD') NOT NULL,
  PRIMARY KEY (`idGame`)
    ) ENGINE = InnoDB;




INSERT INTO `wordleapi`.`Game` (`Max_tries`, `Description`, `Difficulty`) VALUES
        (6, 'A game with words that are all related to a specific time period.', 'NORMAL'),
        (7, 'A game with words that are all related to a specific event.', 'HARD'),
        (5, 'A game with words that are all related to a specific person.', 'EASY'),
        (6, 'A game with words that are all related to a specific animal.', 'NORMAL'),
        (7, 'A game with words that are all related to a specific plant.', 'HARD'),
        (5, 'A game with words that are all related to a specific object.', 'EASY'),
        (6, 'A game with words that are all related to a specific action.', 'NORMAL'),
        (7, 'A game with words that are all related to a specific feeling.', 'HARD'),
        (5, 'A game with words that are all related to a specific place.', 'EASY'),
        (6, 'A game with words that are all related to a specific time period.', 'NORMAL'),
        (7, 'A game with words that are all related to a specific event.', 'HARD'),
        (5, 'A game with words that are all related to a specific person.', 'EASY'),
        (6, 'A game with words that are all related to a specific animal.', 'NORMAL'),
        (7, 'A game with words that are all related to a specific plant.', 'HARD'),
        (5, 'A game with words that are all related to a specific object.', 'EASY'),
        (6, 'A game with words that are all related to a specific action.', 'NORMAL'),
        (7, 'A game with words that are all related to a specific feeling.', 'HARD'),
        (5, 'A game with words that are all related to a specific place.', 'EASY'),
        (6, 'A game with words that are all related to a specific time period.', 'NORMAL'),
        (7, 'A game with words that are all related to a specific event.', 'HARD'),
        (5, 'A game with words that are all related to a specific person.', 'EASY'),
        (6, 'A game with words that are all related to a specific animal.', 'NORMAL'),
        (7, 'A game with words that are all related to a specific plant.', 'HARD'),
        (5, 'A game with words that are all related to a specific object.', 'EASY'),
        (6, 'A game with words that are all related to a specific action.', 'NORMAL'),
        (7, 'A game with words that are all related to a specific feeling.', 'HARD'),
        (5, 'A game with words that are all related to a specific place.', 'EASY'),
        (6, 'A game with words that are all related to a specific time period.', 'NORMAL'),
        (7, 'A game with words that are all related to a specific event.', 'HARD'),
        (5, 'A game with words that are all related to a specific person.', 'EASY');

