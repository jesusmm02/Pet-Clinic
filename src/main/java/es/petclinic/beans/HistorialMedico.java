package es.petclinic.beans;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Entidad HistorialMedico:
 * Representa una entrada de historial clínico para una mascota, incluyendo
 * la fecha, descripción del problema o diagnóstico y el tratamiento aplicado.
 * 
 * @author Jesús
 */
@Entity
@Table(name = "historialesMedicos")
public class HistorialMedico implements Serializable {
    
    // Clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHistorial")
    private Integer id;

    // Fecha del historial.
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Fecha", nullable = false)
    private Date fecha;

    // Descripción del diagnóstico.
    @Column(name = "Descripcion", length = 100)
    private String descripcion;

    // Tratamiento aplicado.
    @Column(name = "Tratamiento", length = 100)
    private String tratamiento;

    /**
     * 
     * Relación muchos a uno con mascota.
     * Muchos historiales médicos pueden pertenecer a una misma mascota.
     * 
     */
    @ManyToOne
    @JoinColumn(name = "IdMascota", nullable = false,
                foreignKey = @ForeignKey(name = "FK_historialesMedicos_mascotas"))
    private Mascota mascota;
    
    
    
    // CONSTRUCTORES

    public HistorialMedico() {
    }

    public HistorialMedico(Integer id, Date fecha, String descripcion, String tratamiento, Mascota mascota) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tratamiento = tratamiento;
        this.mascota = mascota;
    }
    
    
    
    // GETTERS y SETTERS

    public Integer getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

}
