


CREATE TABLE IF NOT EXISTS `wordleApi`.`Team`
(
    `idTeam`    INT         NOT NULL AUTO_INCREMENT,
    `Score`     INT         NULL,
    `Team_name` VARCHAR(45) NULL,
    `Badge`     BLOB        NULL,
    PRIMARY KEY (`idTeam`)
)ENGINE = InnoDB;




INSERT INTO `wordleApi`.`Team` (`Score`, `Team_name`, `Badge`) VALUES
           (100, 'Team 1', NULL),
           (90, 'Team 2', NULL),
           (80, 'Team 3', NULL),
           (70, 'Team 4', NULL),
           (60, 'Team 5', NULL),
           (55, 'Team 6', NULL),
           (50, 'Team 7', NULL),
           (45, 'Team 8', NULL),
           (40, 'Team 9', NULL),
           (35, 'Team 10', NULL),
           (30, 'Team 11', NULL),
           (25, 'Team 12', NULL),
           (20, 'Team 13', NULL),
           (15, 'Team 14', NULL),
           (10, 'Team 15', NULL),
           (5, 'Team 16', NULL),
           (0, 'Team 17', NULL),
           (5, 'Team 18', NULL),
           (10, 'Team 19', NULL),
           (15, 'Team 20', NULL),
           (20, 'Team 21', NULL),
           (25, 'Team 22', NULL),
           (30, 'Team 23', NULL),
           (35, 'Team 24', NULL),
           (40, 'Team 25', NULL),
           (45, 'Team 26', NULL),
           (50, 'Team 27', NULL),
           (55, 'Team 28', NULL),
           (60, 'Team 29', NULL),
           (65, 'Team 30', NULL),
           (70, 'Team 31', NULL),
           (75, 'Team 32', NULL),
           (80, 'Team 33', NULL),
           (85, 'Team 34', NULL),
           (90, 'Team 35', NULL),
           (95, 'Team 36', NULL),
           (100, 'Team 37', NULL),
           (105, 'Team 38', NULL),
           (110, 'Team 39', NULL),
           (115, 'Team 40', NULL);
