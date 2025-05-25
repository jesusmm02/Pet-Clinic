package es.petclinic.beans;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Entidad Cliente:
 * Hereda de Usuario e incorpora atributos adicionales específicos del cliente:
 * género, fecha de nacimiento y lista de mascotas.
 * 
 * @author Jesús
 */
@Entity
@PrimaryKeyJoinColumn(name="IdUsuario", foreignKey= @ForeignKey(name = "FK_clientes_usuarios"))
@Table(name = "clientes")
public class Cliente extends Usuario implements Serializable {
    
    // Género del cliente.
    @Enumerated(EnumType.STRING)
    @Column(name="Genero", length = 6 , nullable = false)
    private Genero genero = Genero.MUJER;
    
    // Enumeración de géneros posibles que puede tener un cliente.
    public enum Genero {
        MUJER, HOMBRE, OTRO
    }
    
    // Fecha de nacimiento del cliente (puede ser nula)
    @Temporal(TemporalType.DATE)
    @Column(name="FechaNacimiento", nullable = true)
    private Date fechaNacimiento;
    
    /**
     * 
     * Relación uno a muchos con mascotas.
     * Un cliente puedes tener muchas mascotas registradas a su nombre.
     * 
     */
    @OneToMany(mappedBy = "propietario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mascota> mascotas;
    
   
    
    // CONSTRUCTORES

    public Cliente() {
    }
    
    public Cliente(Genero genero, Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }
    
    
    
    // GETTERS y SETTERS

    public Genero getGenero() {
        return genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }
    

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    
}