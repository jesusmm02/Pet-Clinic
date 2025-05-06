package es.petclinic.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@PrimaryKeyJoinColumn(name="IdUsuario", foreignKey= @ForeignKey(name = "FK_clientes_usuarios"))
@Table(name = "clientes")
public class Cliente extends Usuario implements Serializable {
    
    @Enumerated(EnumType.STRING)
    @Column(name="Genero", length = 6 , nullable = false)
    private Genero genero = Genero.MUJER;
    
    public enum Genero {
        MUJER, HOMBRE, OTRO
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="FechaNacimiento", nullable = true)
    private Date fechaNacimiento;
    
    
    
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

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
}