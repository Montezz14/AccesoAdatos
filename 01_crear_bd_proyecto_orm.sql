-- =====================================================================
--  ACTIVIDAD 5.7 - Restriccion de unicidad en el nombre del proyecto
-- =====================================================================

USE proyecto_orm;

-- No puede haber dos proyectos con el mismo nombre. Al intentar insertar
-- un nombre repetido, Spring lanzara una DataIntegrityViolationException.
ALTER TABLE proyecto
    ADD CONSTRAINT uq_proyecto_nombre UNIQUE (nom_proy);

-- Comprobacion
SHOW INDEX FROM proyecto;
