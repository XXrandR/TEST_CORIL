DELIMITER $$

DROP PROCEDURE IF EXISTS SP_Propuesta_CountByDate $$

CREATE PROCEDURE SP_Propuesta_CountByDate()
BEGIN
SELECT
    CAST(horario_ejecuto AS DATE) AS fecha,
    COUNT(1) AS cantidad
FROM propuesta_aprobacion
GROUP BY CAST(horario_ejecuto AS DATE);
END $$

DELIMITER ;