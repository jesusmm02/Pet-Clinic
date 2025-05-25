package es.petclinic.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 
 * Entidad Mascota:
 * Representa un animal registrado por un cliente (propietario), con sus datos
 * personales básicos.
 * 
 * @author Jesús
 */
@Entity
@Table(name = "mascotas")
public class Mascota implements Serializable {
    
    // Clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMascota")
    private Integer id;
    
    // Nombre de la mascota.
    @Column(name = "Nombre", length = 30, nullable = false)
    private String nombre;
    
    // Especie de la mascota.
    @Column(name = "Especie", length = 40, nullable = false)
    private String especie;
    
    // Raza de la mascota.
    @Column(name = "Raza", length = 60, nullable = false)
    private String raza;
    
    // Fecha  de nacimiento de la mascota (puede ser nula)
    @Temporal(TemporalType.DATE)
    @Column(name = "FechaNacimiento", nullable = true)
    private Date fechaNacimiento;
    
    // Peso en kilogramos con precisión decimal.
    @Column(name = "Peso", precision = 5, scale = 2)
    private Double peso;
    
    // Género de la mascota.
    @Enumerated(EnumType.STRING)
    @Column(name = "Genero", length = 6)
    private Genero genero;
    
    // Enum con valores posibles de género.
     public enum Genero {
        MACHO, HEMBRA
    }
    
    // Foto de perfil de la mascota
    @Column(name = "Foto", length = 30, nullable = false)
    private String foto = "foto.png"; // por defecto
    
    /**
     * 
     * Relación muchos a uno con propietario.
     * Muchas mascotas pueden pertenecer al mismo cliente.
     * 
     */
    @ManyToOne
    @JoinColumn(name = "IdUsuario", nullable = false,
                foreignKey = @ForeignKey(name = "FK_mascotas_usuarios"))
    private Cliente propietario;
    
    
    
    // CONSTRUCTORES

    public Mascota() {
    }

    public Mascota(Integer id, String nombre, String especie, String raza, Date fechaNacimiento, Double peso, Genero genero, Cliente propietario) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.genero = genero;
        this.propietario = propietario;
    }
    
    
    
    // GETTERS y SETTERS

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Double getPeso() {
        return peso;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getFoto() {
        return foto;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setPropietario(Cliente propietario) {
        this.propietario = propietario;
    }
    
    
    
}
