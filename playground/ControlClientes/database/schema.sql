CREATE SCHEMA `control_clientes`;

CREATE TABLE `control_clientes`.`clientes` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `imei` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `saldo` DOUBLE NULL,
  PRIMARY KEY (`id_cliente`));

INSERT INTO `control_clientes`.`clientes` (`nombre`, `apellido`, `imei`, `telefono`, `saldo`) VALUES ('Renato', 'Cruz', '@testCrenato.com', '23123123', '199');
INSERT INTO `control_clientes`.`clientes` (`nombre`, `apellido`, `imei`, `telefono`, `saldo`) VALUES ('Rey', 'Reyes', '@gmail.com', '3242', '30');
INSERT INTO `control_clientes`.`clientes` (`nombre`, `apellido`, `imei`, `telefono`, `saldo`) VALUES ('Diego', 'Flores', 'test@gmail.com', '32323', '70');

SELECT * FROM clientes;