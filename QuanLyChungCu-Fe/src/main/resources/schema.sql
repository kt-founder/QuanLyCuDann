CREATE TABLE `baitaplon1`.`apartment` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE `baitaplon1`.`condominum` (
  `id` INT NOT NULL,
  `number` INT NOT NULL,
  `area` VARCHAR(45) NOT NULL,
  `price` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
CREATE TABLE `baitaplon1`.`service` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `unit` VARCHAR(45) NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE `baitaplon1`.`user` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `codominumId` INT NOT NULL,
  `cccd` VARCHAR(45) NOT NULL,
  `service` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `condominumid_idx` (`codominumId` ASC) VISIBLE,
  CONSTRAINT `condominumid`
    FOREIGN KEY (`codominumId`)
    REFERENCES `baitaplon1`.`condominum` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);