package com.examen.backend.dto;

import java.util.Date;

public record PropuestaResponse(int propuestaId, Date inicio, Date auditoria_fecha, Date riesgos_fecha,
                                Date fecha_desembolso, Date fecha_cancelacion, Integer minutosCancelacion,
                                Integer minutosLiquidacion, Integer cumpleSLA) {
};
