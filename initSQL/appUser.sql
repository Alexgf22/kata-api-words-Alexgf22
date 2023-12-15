
CREATE TABLE IF NOT EXISTS `wordleapi`. `AppUser` (
     id BIGINT NOT NULL AUTO_INCREMENT,
     password VARCHAR(255),
     username VARCHAR(255),
     PRIMARY KEY (id)
) ENGINE=InnoDB;