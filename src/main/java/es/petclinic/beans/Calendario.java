package es.petclinic.beans;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calendarios")
public class Calendario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCalendario")
    private Integer id;
    
    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha;
    
    @Column(name = "HoraInicio", nullable = false)
    private LocalTime horaInicio;
    
    @Column(name = "HoraFin", nullable = false)
    private LocalTime horaFin;
    
    @Column(name = "Disponible", nullable = false)
    private Boolean disponible = true;
    
    
    
    // CONSTRUCTORES

    public Calendario() {
    }

    public Calendario(Integer id, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    
    
    
    // GETTERS y SETTERS

    public Integer getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
    
}
