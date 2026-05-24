-- =====================================================================
--  ACTIVIDAD 5.1 - Creacion de la base de datos proyecto_orm
--  Correspondencia objeto-relacional a partir de tablas
-- =====================================================================

-- Creamos la base de datos si no existe y la seleccionamos
CREATE DATABASE IF NOT EXISTS proyecto_orm
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE proyecto_orm;

-- ---------------------------------------------------------------------
--  Tabla sede: cada sede tiene un id autoincremental y un nombre
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS sede (
    id_sede  INT AUTO_INCREMENT PRIMARY KEY,
    nom_sede VARCHAR(100) NOT NULL
);

-- ---------------------------------------------------------------------
--  Tabla departamento: pertenece a una sede (clave foranea id_sede)
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS departamento (
    id_depto  INT AUTO_INCREMENT PRIMARY KEY,
    nom_depto VARCHAR(100) NOT NULL,
    id_sede   INT NOT NULL,
    CONSTRAINT fk_depto_sede FOREIGN KEY (id_sede) REFERENCES sede(id_sede)
);

-- ---------------------------------------------------------------------
--  Tabla empleado: la clave primaria es el DNI; pertenece a un depto
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS empleado (
    dni      VARCHAR(9) PRIMARY KEY,
    nom_emp  VARCHAR(100) NOT NULL,
    id_depto INT NOT NULL,
    CONSTRAINT fk_emp_depto FOREIGN KEY (id_depto) REFERENCES departamento(id_depto)
);

-- ---------------------------------------------------------------------
--  Tabla proyecto (actividad 5.7)
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS proyecto (
    id_proy      INT AUTO_INCREMENT PRIMARY KEY,
    nom_proy     VARCHAR(100) NOT NULL,
    fecha_inicio DATE
);

-- ---------------------------------------------------------------------
--  Tabla intermedia proyecto_sede: relacion N:M entre proyecto y sede
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS proyecto_sede (
    id_proy INT NOT NULL,
    id_sede INT NOT NULL,
    PRIMARY KEY (id_proy, id_sede),
    CONSTRAINT fk_ps_proyecto FOREIGN KEY (id_proy) REFERENCES proyecto(id_proy),
    CONSTRAINT fk_ps_sede     FOREIGN KEY (id_sede) REFERENCES sede(id_sede)
);

-- ---------------------------------------------------------------------
--  Tabla empleado_datos_prof (actividad 5.7): datos profesionales,
--  comparte clave primaria con empleado (relacion 1:1)
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS empleado_datos_prof (
    dni       VARCHAR(9) PRIMARY KEY,
    categoria VARCHAR(10),
    sueldo    DOUBLE,
    CONSTRAINT fk_datos_empleado FOREIGN KEY (dni) REFERENCES empleado(dni)
);

-- Comprobacion: mostramos las tablas creadas
SHOW TABLES;
