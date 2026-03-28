package com.examen.backend.service;

import com.examen.backend.dto.PropuestaResponse;
import com.examen.backend.repository.BandejaRepository;
import com.examen.backend.repository.PropuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ReportService {
//    @Autowired
//    private PropuestaRepository propuestaRepository;

    @Autowired
    private BandejaRepository bandejaRepository;

    private final PropuestaRepository propuestaRepository;

    public ReportService(PropuestaRepository propuestaRepository) {
        this.propuestaRepository = propuestaRepository;
    }

    public List<Long> idsOfBandejas(){
        return bandejaRepository.idsOfBandejas();
    }

    public List<PropuestaResponse> propuestaByBandeja(int id) {
        return propuestaRepository.propuestaByBandeja(id).stream()
                .map(row -> new PropuestaResponse(
                        ((Number) row.getPropuestaId()).intValue(),
                        Date.from(row.getInicio().toInstant()),
                        Date.from(row.getAuditoriaFecha().toInstant()),
                        Date.from(row.getRiesgosFecha().toInstant()),
                        Date.from(row.getFechaDesembolso().toInstant()),
                        Date.from(row.getFechaCancelacion().toInstant()),
                        row.getMinutosCancelacion(),
                        row.getMinutosLiquidacion(),
                        row.getCumpleSLA()
                ))
                .toList();
    }
}
