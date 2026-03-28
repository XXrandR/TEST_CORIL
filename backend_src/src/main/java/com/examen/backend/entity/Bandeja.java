package com.examen.backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bandeja")
public class Bandeja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "n_propuesta")
    private Long nPropuesta;

    private LocalDateTime inicio;

    @OneToMany(mappedBy = "bandeja", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Propuesta> propuestas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Long getNPropuesta() {
        return nPropuesta;
    }

    public void setNPropuesta(Long nPropuesta) {
        this.nPropuesta = nPropuesta;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public List<Propuesta> getPropuestas() {
        return propuestas;
    }

    public void setPropuestas(List<Propuesta> propuestas) {
        this.propuestas = propuestas;
    }
}
