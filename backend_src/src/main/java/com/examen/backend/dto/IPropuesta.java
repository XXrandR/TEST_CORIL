package com.examen.backend.dto;

import java.sql.Timestamp;

public interface IPropuesta {
    int getPropuestaId();
    Timestamp getInicio();
    Timestamp getAuditoriaFecha();
    Timestamp getRiesgosFecha();
    Timestamp getFechaDesembolso();
    Timestamp getFechaCancelacion();
    Integer getMinutosCancelacion();
    Integer getMinutosLiquidacion();
    Integer getCumpleSLA();
}
