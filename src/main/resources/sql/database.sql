-- -----------------------------------------------------
-- Schema ServicesCar
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ServicesCar` DEFAULT CHARACTER SET utf8;
USE `ServicesCar`;
-- -----------------------------------------------------
-- Table `ServicesCar`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicesCar`.`employees`
(
    `id`             INT          NOT NULL AUTO_INCREMENT,
    `first_name`     VARCHAR(245) NULL,
    `last_name`      VARCHAR(245) NULL,
    `email`          VARCHAR(245) NULL UNIQUE,
    `password`       VARCHAR(245) NULL,
    `super_admin`    TINYINT(10)  NULL,
    `address`        VARCHAR(245) NULL,
    `phone_number`   INT(11)      NULL,
    `Notes`          TEXT(1000)   NULL,
    `hourly_rate`    DECIMAL      NULL,
    `quantity_hours` DECIMAL      NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    COMMENT = 'Table containing user data';


-- -----------------------------------------------------
-- Table `ServicesCar`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicesCar`.`clients`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(245) NULL,
    `last_name`  VARCHAR(245) NULL,
    `email`      VARCHAR(245) NULL,
    `password`   VARCHAR(245) NULL,
    `address`    VARCHAR(245) NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    COMMENT = 'Table containing clients data';

-- -----------------------------------------------------
-- Table `ServicesCar`.`vehicles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicesCar`.`vehicles`
(
    `id`                    INT          NOT NULL AUTO_INCREMENT,
    `brand`                 VARCHAR(245) NULL,
    `engine`                VARCHAR(245) NULL,
    `color`                 VARCHAR(245) NULL,
    `production_year`       DATE         NULL,
    `gear_box`              VARCHAR(245) NULL,
    `registration_number`   VARCHAR(245) NULL,
    `model`                 VARCHAR(245) NULL,
    `next_technical_review` DATE         NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    COMMENT = 'Table containing vehicles data';

-- -----------------------------------------------------
-- Table `ServicesCar`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicesCar`.`orders`
(
    `id`                   INT          NOT NULL AUTO_INCREMENT,
    `accepted_date`        DATE         NULL,
    `planted_date`         DATE         NULL,
    `start_date`           DATE         NULL,
    `description_problems` VARCHAR(245) NULL,
    `description_repair`   VARCHAR(245) NULL,
    `status`               TINYINT(10)  NULL,
    `repair_cost`          DECIMAL      NULL,
    `parts_cost`           DECIMAL      NULL,
    `hourly_rate`          INT          NULL,
    `repair_hours`         DECIMAL      NULL,
    `employee_id`          INT          NOT NULL,
    `client_id`            INT          NOT NULL,
    `vehicle_id`           INT          NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_workers_has_clients_clients1_idx` (client_id ASC) VISIBLE,
    INDEX `fk_workers_has_clients_workers1_idx` (employee_id ASC) VISIBLE,
    INDEX `fk_workers_has_clients_hourly_rate1_idx` (`hourly_rate` ASC) VISIBLE,
    INDEX `fk_workers_has_clients_vehicles1_idx` (vehicle_id ASC) VISIBLE,
    CONSTRAINT `fk_workers_has_clients_workers1`
        FOREIGN KEY (employee_id)
            REFERENCES `ServicesCar`.`employees` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_workers_has_clients_hourly_rate1_idx`
        FOREIGN KEY (`hourly_rate`)
            REFERENCES `ServicesCar`.`employees` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_workers_has_clients_clients1`
        FOREIGN KEY (client_id)
            REFERENCES `ServicesCar`.`clients` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_workers_has_clients_vehicles1`
        FOREIGN KEY (vehicle_id)
            REFERENCES `ServicesCar`.`vehicles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    COMMENT = 'Table containing orders';