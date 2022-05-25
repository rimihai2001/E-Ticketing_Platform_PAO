create database E_Ticketing_Platform;
use E_Ticketing_Platform;

CREATE TABLE `e_ticketing_platform`.`location` (
  `idLocation` INT NOT NULL AUTO_INCREMENT,
  `LocationName` VARCHAR(45) NOT NULL,
  `Country` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `ZIP` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLocation`));

CREATE TABLE `e_ticketing_platform`.`event` (
  `idEvent` INT NOT NULL AUTO_INCREMENT,
  `EventName` VARCHAR(45) NOT NULL,
  `TicketsNumber` INT NOT NULL,
  `Price` DOUBLE NOT NULL,
  PRIMARY KEY (`idEvent`));

CREATE TABLE `e_ticketing_platform`.`card` (
  `idcard` INT NOT NULL AUTO_INCREMENT,
  `CardNumber` VARCHAR(45) NOT NULL,
  `OwnerName` VARCHAR(45) NOT NULL,
  `ExpiryDate` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcard`));

CREATE TABLE `e_ticketing_platform`.`seller` (
  `idseller` INT NOT NULL AUTO_INCREMENT,
  `SellerName` VARCHAR(45) NOT NULL,
  `URL` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idseller`));