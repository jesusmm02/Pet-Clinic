package es.petclinic.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * 
 * Entidad Veterinario:
 * Hereda de Usuario e incorpora atributos adicionales específicos del veterinario:
 * "modoDios" para distinguir privilegios especiales.
 * 
 * @author Jesús
 */
@Entity
@Table(name = "veterinarios")
@PrimaryKeyJoinColumn(name="IdUsuario", foreignKey= @ForeignKey(name = "FK_veterinarios_usuarios")) // Clave primaria heredada
public class Veterinario extends Usuario implements Serializable {
    
    // Campo modoDios.
    @Type(type = "true_false")
    @Column(name="ModoDios", nullable = false, length = 1)
    private boolean modoDios;

    
    
    // CONSTRUCTORES
    
    public Veterinario() {
    }
    
    public Veterinario(boolean modoDios) {
        this.modoDios = modoDios;
    }
    
    
    
    // GETTERS Y SETTERS
    
    public boolean isModoDios() {
        return modoDios;
    }

    public void setModoDios(boolean modoDios) {
        this.modoDios = modoDios;
    }
    
}