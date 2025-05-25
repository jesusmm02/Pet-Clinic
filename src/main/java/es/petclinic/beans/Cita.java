package es.petclinic.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * Entidad Cita:
 * Representa una cita veterinaria reservada por un cliente para su mascota,
 * en una fecha y hora concretas (asociadas a un calendario), con un veterinario y un servicio concreto.
 * 
 * @author Jesús
 */
@Entity
@Table(name = "citas")
public class Cita implements Serializable {
    
    // Clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCita")
    private Integer id;

    /**
     * 
     * Relación muchos a uno con mascota.
     * Muchas citas pueden estar asociadas a una misma mascota.
     * 
     */ 
    @ManyToOne
    @JoinColumn(name = "IdMascota", nullable = false,
                foreignKey = @ForeignKey(name = "FK_citas_mascotas"))
    private Mascota mascota;

    /**
     * 
     * Relación muchos a uno con calendario.
     * Cada cita ocupa un bloque de calendario.
     * 
     */
    @ManyToOne
    @JoinColumn(name = "IdCalendario", nullable = false,
                foreignKey = @ForeignKey(name = "FK_citas_calendarios"))
    private Calendario calendario;

    /**
     * 
     * Relación muchos a uno con servicio.
     * Muchas citas puedes estar asociadas al mismo tipo de servicio.
     * 
     */
    @ManyToOne
    @JoinColumn(name = "IdServicio", nullable = false,
                foreignKey = @ForeignKey(name = "FK_citas_servicios"))
    private Servicio servicio;

    /**
     * 
     * Relación muchos a uno con veterinario.
     * Muchas citas puedes ser atendidas por un mismo veterinario.
     * 
     */
    @ManyToOne
    @JoinColumn(name = "IdVeterinario", nullable = false,
                foreignKey = @ForeignKey(name = "FK_citas_veterinarios"))
    private Usuario veterinario;
    
    
    
    // CONSTRUCTORES

    public Cita() {
    }

    public Cita(Integer id, Mascota mascota, Calendario calendario, Servicio servicio, Usuario veterinario) {
        this.id = id;
        this.mascota = mascota;
        this.calendario = calendario;
        this.servicio = servicio;
        this.veterinario = veterinario;
    }
    
    
    
    // GETTERS y SETTERS

    public Integer getId() {
        return id;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public Usuario getVeterinario() {
        return veterinario;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public void setVeterinario(Usuario veterinario) {
        this.veterinario = veterinario;
    }
    
}
