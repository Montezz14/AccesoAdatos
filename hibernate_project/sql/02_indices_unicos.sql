-- =====================================================================
--  ACTIVIDAD 5.6 - Restricciones de unicidad mediante indices unicos
-- =====================================================================

USE proyecto_orm;

-- No pueden existir dos sedes distintas con el mismo nombre
CREATE UNIQUE INDEX idx_sede_nombre
    ON sede (nom_sede);

-- No pueden existir dos departamentos con el mismo nombre dentro de una
-- misma sede (el indice es sobre la pareja nom_depto + id_sede)
CREATE UNIQUE INDEX idx_depto_nombre_sede
    ON departamento (nom_depto, id_sede);

-- Comprobacion: mostramos los indices de cada tabla
SHOW INDEX FROM sede;
SHOW INDEX FROM departamento;
