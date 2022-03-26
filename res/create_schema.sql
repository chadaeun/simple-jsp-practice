-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema practice
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema practice
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `practice` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `practice` ;

-- -----------------------------------------------------
-- Table `practice`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `practice`.`users` ;

CREATE TABLE IF NOT EXISTS `practice`.`users` (
  `id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `practice`.`article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `practice`.`article` ;

CREATE TABLE IF NOT EXISTS `practice`.`article` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(45) NOT NULL,
  `subject` VARCHAR(45) NULL,
  `content` LONGTEXT NULL,
  PRIMARY KEY (`no`),
  INDEX `article_userid_users_id_fk_idx` (`userid` ASC) VISIBLE,
  CONSTRAINT `article_userid_users_id_fk`
    FOREIGN KEY (`userid`)
    REFERENCES `practice`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
