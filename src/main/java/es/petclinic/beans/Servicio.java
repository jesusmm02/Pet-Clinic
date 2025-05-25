package es.petclinic.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Entidad Servicio:
 * Representa un servicio veterinario que se puede ofrecer a las mascotas.
 * 
 * @author Jesús
 */
@Entity
@Table(name = "servicios")
public class Servicio implements Serializable {
    
    // Clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdServicio")
    private Integer id;

    // Nombre del servicio.
    @Column(name = "Nombre", length = 50, nullable = false)
    private String nombre;

    // Descripción del servicio.
    @Column(name = "Descripcion", length = 255)
    private String descripcion;

    // Precio del servicio.
    @Column(name = "Precio", precision = 5, scale = 2, nullable = false)
    private Double precio;

    // Duración del servicio.
    @Column(name = "Duracion", nullable = false)
    private Integer duracion; // en minutos
    
    
    
    // CONSTRUCTORES

    public Servicio() {
    }

    public Servicio(Integer id, String nombre, String descripcion, Double precio, Integer duracion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracion = duracion;
    }
    
    
    
    // GETTERS y SETTERS

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

}
