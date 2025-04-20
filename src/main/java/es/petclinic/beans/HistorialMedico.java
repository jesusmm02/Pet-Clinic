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

@Entity
@Table(name = "historialesMedicos")
public class HistorialMedico implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHistorial")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "Fecha", nullable = false)
    private Date fecha;

    @Column(name = "Descripcion", length = 100)
    private String descripcion;

    @Column(name = "Tratamiento", length = 100)
    private String tratamiento;

    @ManyToOne
    @JoinColumn(name = "IdMascota", nullable = false,
                foreignKey = @ForeignKey(name = "FK_historialesMedicos_mascotas"))
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "IdVeterinario", nullable = false,
                foreignKey = @ForeignKey(name = "FK_historialesMedicos_veterinarios"))
    private Usuario veterinario;
    
    
    
    // CONSTRUCTORES

    public HistorialMedico() {
    }

    public HistorialMedico(Integer id, Date fecha, String descripcion, String tratamiento, Mascota mascota, Usuario veterinario) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tratamiento = tratamiento;
        this.mascota = mascota;
        this.veterinario = veterinario;
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

    public Usuario getVeterinario() {
        return veterinario;
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

    public void setVeterinario(Usuario veterinario) {
        this.veterinario = veterinario;
    }

}
