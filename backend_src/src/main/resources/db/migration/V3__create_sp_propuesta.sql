DELIMITER $$

DROP PROCEDURE IF EXISTS SP_propuesta_Q01 $$

CREATE PROCEDURE SP_propuesta_Q01(
    IN bandeja_id BIGINT-- '20528'
)
BEGIN

    -- OBSERVACION 1: DEBERIA EXISTIR UNA SOLA AUDITORIA,RIESGOS row ya que el JSON del ejemplo solo retorna 1 valor, se normaliza al mayor

    -- SELECT t3.propuesta_id,t3.aprobacion,MAX(t3.horario_ejecuto) FROM bandeja t1
    -- INNER JOIN propuesta t2 ON t1.id = t2.bandeja_id
                -- LEFT JOIN propuesta_aprobacion t3 ON t2.id = t3.propuesta_id
                -- LEFT JOIN propuesta_detalle t4 ON t2.id = t4.propuesta_id
                -- WHERE t1.id = '20528' AND t3.aprobacion = 'AUDITORIA'
                -- GROUP BY 1,2
                --
                -- SELECT t3.propuesta_id,t3.aprobacion,MAX(t3.horario_ejecuto) FROM bandeja t1
    -- INNER JOIN propuesta t2 ON t1.id = t2.bandeja_id
                      -- LEFT JOIN propuesta_aprobacion t3 ON t2.id = t3.propuesta_id
                      -- LEFT JOIN propuesta_detalle t4 ON t2.id = t4.propuesta_id
                      -- WHERE t1.id = '20528' AND t3.aprobacion = 'RIESGOS'
                      -- GROUP BY 1,2
                      -- SELECT MAX(t4.fecha_desembolso),MAX(fecha_cancelacion) FROM bandeja t1
    -- INNER JOIN propuesta t2 ON t1.id = t2.bandeja_id
                            -- LEFT JOIN propuesta_aprobacion t3 ON t2.id = t3.propuesta_id
                            -- LEFT JOIN propuesta_detalle t4 ON t2.id = t4.propuesta_id
                            -- WHERE t1.id = '20528'
                            -- OBSERVACION 2: ALGUNAS BANDEJAS NO TIENEN AUDITORIA/RIESGOS PERO SI TIENEN CANCELACION, ESTO NO DEBE SER PERMITIDO

SELECT
    t2.id AS propuestaId,
    MAX(t1.inicio) inicio,
    MAX(CASE
            WHEN t3.aprobacion = 'AUDITORIA'
                THEN t3.horario_ejecuto
        END) AS auditoriaFecha,
    MAX(CASE
            WHEN t3.aprobacion = 'RIESGOS'
                THEN t3.horario_ejecuto
        END) AS riesgosFecha,
    MAX(t4.fecha_desembolso) AS fechaDesembolso,
    MAX(t4.fecha_cancelacion) AS fechaCancelacion,
    TIMESTAMPDIFF(MINUTE,MAX(t4.fecha_desembolso),MAX(t4.fecha_cancelacion)) AS minutosCancelacion,
    TIMESTAMPDIFF(MINUTE,
            MAX(CASE
                    WHEN t3.aprobacion = 'AUDITORIA'
                        THEN t3.horario_ejecuto
                END),
            MAX(t4.fecha_desembolso)
    ) AS minutosLiquidacion,
    CASE
        WHEN TIMESTAMPDIFF(
                 MINUTE,
                     MAX(t4.fecha_desembolso),
                     MAX(t4.fecha_cancelacion)
             ) <= 2000
            THEN 1
        ELSE 0
        END AS cumpleSLA
FROM bandeja t1
         INNER JOIN propuesta t2
                    ON t1.id = t2.bandeja_id
         LEFT JOIN propuesta_aprobacion t3
                   ON t2.id = t3.propuesta_id
         LEFT JOIN propuesta_detalle t4
                   ON t2.id = t4.propuesta_id
WHERE t1.id = bandeja_id
GROUP BY t2.id;

end $$

DELIMITER $$
