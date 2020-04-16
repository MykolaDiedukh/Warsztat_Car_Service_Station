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
    `id`             INT          NOT NULL AUTO_INCREMENT COMMENT 'The primary key of the table.',
    `first_name`     VARCHAR(245) NULL COMMENT 'First name of employee',
    `last_name`      VARCHAR(245) NULL COMMENT 'Last name of employee',
    `email`          VARCHAR(245) NULL UNIQUE COMMENT 'Email address',
    `password`       VARCHAR(245) NULL COMMENT 'Password',
    `super_admin`    TINYINT(10)  NULL COMMENT 'Status 1 mean user is administrator',
    `address`        VARCHAR(245) NULL COMMENT 'Address employee',
    `phone_number`   INT(11)      NULL COMMENT 'Phone number of employee',
    `Notes`          TEXT(1000)   NULL COMMENT 'Notes about employee',
    `hourly_rate`    DECIMAL      NULL COMMENT 'Payment for hour for employee',
    `quantity_hours` DECIMAL      NULL COMMENT 'Quantity of hour of employee',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    COMMENT = 'Table containing user data';


-- -----------------------------------------------------
-- Table `ServicesCar`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicesCar`.`clients`
(
    `id`         INT          NOT NULL AUTO_INCREMENT COMMENT 'The primary key of the table.',
    `first_name` VARCHAR(245) NULL COMMENT 'First name of client',
    `last_name`  VARCHAR(245) NULL COMMENT 'Last name of client',
    `email`      VARCHAR(245) NULL UNIQUE COMMENT 'Email address',
    `password`   VARCHAR(245) NULL COMMENT 'Password',
    `address`    VARCHAR(245) NULL COMMENT 'Address client',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    COMMENT = 'Table containing clients data';

-- -----------------------------------------------------
-- Table `ServicesCar`.`vehicles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicesCar`.`vehicles`
(
    `id`                    INT          NOT NULL AUTO_INCREMENT COMMENT 'The primary key of the table.',
    `brand`                 VARCHAR(245) NULL COMMENT 'Brand of car',
    `engine`                VARCHAR(245) NULL COMMENT 'Engine of car',
    `color`                 VARCHAR(245) NULL COMMENT 'Color of car',
    `production_year`       DATE         NULL COMMENT 'Year when car was produced',
    `gear_box`              VARCHAR(245) NULL COMMENT 'Type of gear box in car',
    `registration_number`   VARCHAR(245) NULL COMMENT 'registration code of car',
    `model`                 VARCHAR(245) NULL COMMENT 'Model of car',
    `next_technical_review` DATE         NULL COMMENT 'Time when technical review expire and need to do new',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    COMMENT = 'Table containing vehicles data';

-- -----------------------------------------------------
-- Table `ServicesCar`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicesCar`.`orders`
(
    `id`                   INT          NOT NULL AUTO_INCREMENT COMMENT 'The primary key of the table.',
    `accepted_date`        DATE         NULL COMMENT 'Day when car was transferred to service',
    `planted_date`         DATE         NULL COMMENT 'Possible day of starting restore car',
    `start_date`           DATE         NULL COMMENT 'First day of restoring car',
    `description_problems` VARCHAR(245) NULL COMMENT 'Defects and problems what was found in car',
    `description_repair`   VARCHAR(245) NULL COMMENT 'What need do for fix the car',
    `status`               TINYINT(10)  NULL COMMENT 'If 1 then car is restored',
    `repair_cost`          DECIMAL      NULL COMMENT 'Payments for restoring',
    `parts_cost`           DECIMAL      NULL COMMENT 'Cost of part for cars',
    `hourly_rate`          INT          NULL COMMENT 'Payment for employee per hour/Foreign key for table employees',
    `repair_hours`         DECIMAL      NULL COMMENT 'Spended total time on repair car',
    `employee_id`          INT          NOT NULL COMMENT 'Foreign key for table employees by id',
    `client_id`            INT          NOT NULL COMMENT 'Foreign key for table clients by id',
    `vehicle_id`           INT          NOT NULL COMMENT 'Foreign key for table vehicles by id',
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