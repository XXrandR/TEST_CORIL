package com.examen.backend.controller;

import com.examen.backend.dto.PropuestaResponse;
import com.examen.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("propuestas/{id}")
    public List<PropuestaResponse> propuestasByBandeja(@PathVariable(name = "id") int id){
        return reportService.propuestaByBandeja(id);
    }

    @GetMapping("bandejas")
    public List<Long> idsOfBandejas(){
        return reportService.idsOfBandejas();
    }
}
