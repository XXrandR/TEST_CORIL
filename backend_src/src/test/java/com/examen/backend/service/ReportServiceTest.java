package com.examen.backend.service;

import com.examen.backend.dto.IPropuesta;
import com.examen.backend.dto.PropuestaResponse;
import com.examen.backend.repository.PropuestaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {
    @Mock
    private PropuestaRepository propuestaRepository;

    @InjectMocks
    private ReportService propuestaService;

    @Test
    void propuestaByBandeja_returnsExpectedResult() {
        int bandejaId = 20528;

        IPropuesta row = mock(IPropuesta.class);
        Timestamp now = Timestamp.from(Instant.parse("2026-03-28T10:15:30Z"));

        when(row.getPropuestaId()).thenReturn(1);
        when(row.getInicio()).thenReturn(now);
        when(row.getAuditoriaFecha()).thenReturn(now);
        when(row.getRiesgosFecha()).thenReturn(now);
        when(row.getFechaDesembolso()).thenReturn(now);
        when(row.getFechaCancelacion()).thenReturn(now);
        when(row.getMinutosCancelacion()).thenReturn(10);
        when(row.getMinutosLiquidacion()).thenReturn(20);
        when(row.getCumpleSLA()).thenReturn(1);

        when(propuestaRepository.propuestaByBandeja(bandejaId))
                .thenReturn(List.of(row));

        List<PropuestaResponse> result = propuestaService.propuestaByBandeja(bandejaId);

        assertEquals(1, result.size());

        PropuestaResponse dto = result.get(0);

        assertEquals(1, dto.propuestaId());
        assertEquals(now.toInstant(), dto.inicio().toInstant());
        assertEquals(now.toInstant(), dto.auditoria_fecha().toInstant());
        assertEquals(now.toInstant(), dto.riesgos_fecha().toInstant());
        assertEquals(now.toInstant(), dto.fecha_desembolso().toInstant());
        assertEquals(now.toInstant(), dto.fecha_cancelacion().toInstant());
        assertEquals(10, dto.minutosCancelacion());
        assertEquals(20, dto.minutosLiquidacion());
        assertEquals(1, dto.cumpleSLA());
    }

}