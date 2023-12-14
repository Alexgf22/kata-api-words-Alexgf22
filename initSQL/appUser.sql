
CREATE TABLE IF NOT EXISTS `wordleApi`. `AppUser` (
     id BIGINT NOT NULL AUTO_INCREMENT,
     password VARCHAR(255),
     username VARCHAR(255),
     PRIMARY KEY (id)
) ENGINE=InnoDB;