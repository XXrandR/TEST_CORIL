package com.examen.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "propuesta_detalle")
public class PropuestaDetalle {

    @Id
    @Column(length = 100)
    private String guid;

    private String nroFactura;

    private LocalDateTime fechaDesembolso;

    private LocalDateTime fechaCancelacion;

    @ManyToOne
    @JoinColumn(name = "propuesta_id")
    private Propuesta propuesta;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public LocalDateTime getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(LocalDateTime fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public LocalDateTime getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDateTime fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Propuesta getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(Propuesta propuesta) {
        this.propuesta = propuesta;
    }
}
