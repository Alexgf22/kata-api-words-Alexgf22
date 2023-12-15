

CREATE TABLE IF NOT EXISTS `wordleapi`.`Word`
(
    `idWord` BIGINT       NOT NULL AUTO_INCREMENT,
    `Word`   VARCHAR(45) NULL,
    PRIMARY KEY (`idWord`)
)ENGINE = InnoDB;



# En la base de datos, tabla Word, click derecho
# import/export import data from file y se importa
# todas las palabras del txt