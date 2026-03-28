package com.examen.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "propuesta_aprobacion")
public class PropuestaAprobacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "propuesta_id")
    private Propuesta propuesta;

    private String aprobacion;

    private LocalDateTime horarioEjecuto;

    public Long getId() {
        return id;
    }

    public Propuesta getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(Propuesta propuesta) {
        this.propuesta = propuesta;
    }

    public String getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(String aprobacion) {
        this.aprobacion = aprobacion;
    }

    public LocalDateTime getHorarioEjecuto() {
        return horarioEjecuto;
    }

    public void setHorarioEjecuto(LocalDateTime horarioEjecuto) {
        this.horarioEjecuto = horarioEjecuto;
    }
}
