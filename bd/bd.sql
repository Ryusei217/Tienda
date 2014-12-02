SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Tienda
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Tienda` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Tienda` ;

-- -----------------------------------------------------
-- Table `Tienda`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tienda`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `dpi` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tienda`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tienda`.`Pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATETIME NOT NULL,
  `Cliente_idCliente` INT NOT NULL,
  PRIMARY KEY (`idPedido`),
  INDEX `fk_Pedido_Cliente_idx` (`Cliente_idCliente` ASC),
  CONSTRAINT `fk_Pedido_Cliente`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `Tienda`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tienda`.`Articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tienda`.`Articulo` (
  `idArticulo` INT NOT NULL AUTO_INCREMENT,
  `numeroSerie` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `precio` DECIMAL(18,4) NOT NULL,
  PRIMARY KEY (`idArticulo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tienda`.`DetallePedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tienda`.`DetallePedido` (
  `Articulo_idArticulo` INT NOT NULL,
  `Pedido_idPedido` INT NOT NULL,
  `cantidad` INT NOT NULL,
  PRIMARY KEY (`Articulo_idArticulo`, `Pedido_idPedido`),
  INDEX `fk_Articulo_has_Pedido_Pedido1_idx` (`Pedido_idPedido` ASC),
  INDEX `fk_Articulo_has_Pedido_Articulo1_idx` (`Articulo_idArticulo` ASC),
  CONSTRAINT `fk_Articulo_has_Pedido_Articulo1`
    FOREIGN KEY (`Articulo_idArticulo`)
    REFERENCES `Tienda`.`Articulo` (`idArticulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Articulo_has_Pedido_Pedido1`
    FOREIGN KEY (`Pedido_idPedido`)
    REFERENCES `Tienda`.`Pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
