CREATE SCHEMA IF NOT EXISTS `car_fleet` DEFAULT CHARACTER SET utf8 ;
USE `car_fleet` ;

-- -----------------------------------------------------
-- Table `car_fleet`.`BUS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_fleet`.`BUS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `BRAND` VARCHAR(30) NOT NULL,
  `NUMBER_OF_SEATS` INT NOT NULL,
  `ID_CAR_FLEET` INT NOT NULL,
  PRIMARY KEY (`ID`, `ID_CAR_FLEET`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `ID_BUS_UNIQUE` ON `car_fleet`.`BUS` (`ID` ASC);


-- -----------------------------------------------------
-- Table `car_fleet`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_fleet`.`USER` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` VARCHAR(45) NOT NULL,
  `LAST_NAME` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(50) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `ID_USER_UNIQUE` ON `car_fleet`.`USER` (`ID` ASC);


-- -----------------------------------------------------
-- Table `car_fleet`.`ROUTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_fleet`.`ROUTE` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `START_POINT` VARCHAR(120) NOT NULL,
  `END_POINT` VARCHAR(120) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `car_fleet`.`BUS_HAS_ROUTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_fleet`.`BUS_HAS_ROUTE` (
  `ID_BUS` INT NOT NULL,
  `ID_ROUTE` INT NOT NULL,
  PRIMARY KEY (`ID_BUS`, `ID_ROUTE`),
  CONSTRAINT `fk_BUS_has_ROOT_BUS1`
    FOREIGN KEY (`ID_BUS`)
    REFERENCES `car_fleet`.`BUS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BUS_has_ROOT_ROOT1`
    FOREIGN KEY (`ID_ROUTE`)
    REFERENCES `car_fleet`.`ROUTE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_BUS_has_ROOT_ROOT1_idx` ON `car_fleet`.`BUS_HAS_ROUTE` (`ID_ROUTE` ASC);

CREATE INDEX `fk_BUS_has_ROOT_BUS1_idx` ON `car_fleet`.`BUS_HAS_ROUTE` (`ID_BUS` ASC);


-- -----------------------------------------------------
-- Table `car_fleet`.`DRIVER_HAS_BUS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_fleet`.`DRIVER_HAS_BUS` (
  `ID_USER` INT NOT NULL,
  `ID_BUS` INT NOT NULL,
  `THE_DRIVER_CONFIRMED` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`ID_USER`, `ID_BUS`),
  CONSTRAINT `fk_USER_has_BUS_USER1`
    FOREIGN KEY (`ID_USER`)
    REFERENCES `car_fleet`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_has_BUS_BUS1`
    FOREIGN KEY (`ID_BUS`)
    REFERENCES `car_fleet`.`BUS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_USER_has_BUS_BUS1_idx` ON `car_fleet`.`DRIVER_HAS_BUS` (`ID_BUS` ASC);

CREATE INDEX `fk_USER_has_BUS_USER1_idx` ON `car_fleet`.`DRIVER_HAS_BUS` (`ID_USER` ASC);


-- -----------------------------------------------------
-- Table `car_fleet`.`CONFIRMATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_fleet`.`CONFIRMATION` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `EMAIL_CONFIRMED` VARCHAR(45) NOT NULL,
  `ID_USER` INT NOT NULL,
  PRIMARY KEY (`ID`, `ID_USER`),
  CONSTRAINT `fk_CONFIRMATION_USER1`
    FOREIGN KEY (`ID_USER`)
    REFERENCES `car_fleet`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `ID_CONFIRMATION_UNIQUE` ON `car_fleet`.`CONFIRMATION` (`ID` ASC);

CREATE INDEX `fk_CONFIRMATION_USER1_idx` ON `car_fleet`.`CONFIRMATION` (`ID_USER` ASC);


-- -----------------------------------------------------
-- Table `car_fleet`.`ROLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_fleet`.`ROLE` (
  `ID` INT NOT NULL,
  `NAME_OF_ROLE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `car_fleet`.`USER_HAS_ROLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_fleet`.`USER_HAS_ROLE` (
  `ID_USER` INT NOT NULL,
  `ID_ROLE` INT NOT NULL,
  PRIMARY KEY (`ID_USER`, `ID_ROLE`),
  CONSTRAINT `fk_USER_has_ROLE_USER1`
    FOREIGN KEY (`ID_USER`)
    REFERENCES `car_fleet`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_has_ROLE_ROLE1`
    FOREIGN KEY (`ID_ROLE`)
    REFERENCES `car_fleet`.`ROLE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_USER_has_ROLE_ROLE1_idx` ON `car_fleet`.`USER_HAS_ROLE` (`ID_ROLE` ASC);

CREATE INDEX `fk_USER_has_ROLE_USER1_idx` ON `car_fleet`.`USER_HAS_ROLE` (`ID_USER` ASC);