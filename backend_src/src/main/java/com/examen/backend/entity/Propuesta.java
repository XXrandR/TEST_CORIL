package com.examen.backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "propuesta")
public class Propuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bandeja_id")
    private Bandeja bandeja;

    @OneToMany(mappedBy = "propuesta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropuestaDetalle> detalles = new ArrayList<>();

    @OneToMany(mappedBy = "propuesta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropuestaAprobacion> aprobaciones = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Bandeja getBandeja() {
        return bandeja;
    }

    public void setBandeja(Bandeja bandeja) {
        this.bandeja = bandeja;
    }

    public List<PropuestaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<PropuestaDetalle> detalles) {
        this.detalles = detalles;
    }

    public List<PropuestaAprobacion> getAprobaciones() {
        return aprobaciones;
    }

    public void setAprobaciones(List<PropuestaAprobacion> aprobaciones) {
        this.aprobaciones = aprobaciones;
    }
}
