CREATE TABLE `records`.`action` (
  `Name` VARCHAR(5) NOT NULL,
  `Date` VARCHAR(45) NOT NULL,
  `Open` VARCHAR(45) NULL,
  `Close` VARCHAR(45) NULL,
  `High` VARCHAR(45) NULL,
  `Low` VARCHAR(45) NULL,
  `Volume` VARCHAR(45) NULL,
  PRIMARY KEY (`Name`, `Date`));