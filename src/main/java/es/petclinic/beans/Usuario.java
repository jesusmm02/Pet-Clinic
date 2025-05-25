package es.petclinic.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * 
 * Entidad Usuario:
 * Clase base para todos los usuarios del sistema (Clientes y Veterinarios).
 * Utiliza herencia JOINED para dividir los atributos comunes y específicos
 * en tablas distintas.
 * 
 * @author Jesús
 */
@Entity
@Table(name = "usuarios", uniqueConstraints = { // Campos que no pueden tener valores repetidos
    @UniqueConstraint(columnNames = "NumIdentificacion", name = "UK_usuario_numIdentificacion"),
    @UniqueConstraint(columnNames = "Email", name = "UK_usuario_email")
})
@Inheritance(strategy = InheritanceType.JOINED) // Herencia entre clases
public class Usuario implements Serializable {
    
    // Clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Integer id;
    
    // Nombre del usuario.
    @Column(name = "Nombre", length = 30)
    private String nombre;
    
    // Apellidos del usuario.
    @Column(name = "Apellidos", length = 60)
    private String apellidos;
    
    // Número de DNI o NIE.
    @Column(name = "NumIdentificacion", length = 9, nullable = false)
    private String numIdentificacion;
    
    // Email del usuario.
    @Column(name = "Email", length = 80, nullable = false)
    private String email;
    
    // Contraseña del usuario.
    @Column(name = "Password", length = 128, nullable = false)
    private String password;
    
    // Rol del usuario.
    @Enumerated(EnumType.STRING)
    @Column(name = "Rol", length = 11, nullable = false)
    private Rol rol;
    
    // Enumeración de roles posibles que puede tener un usuario.
    public enum Rol {
        VETERINARIO, CLIENTE
    }
    
    // Última fecha en la que el usuario accedió al sistema.
    @Temporal(TemporalType.DATE)
    @Column(name = "UltimoAcceso")
    private Date ultimoAcceso;
    
    // Avatar del usuario.
    @Column(name = "Avatar", length = 30, nullable = false)
    private String avatar = "avatar.png"; // por defecto

    
    
    // CONSTRUCTORES
    
    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String apellidos, String numIdentificacion, String email, String password, Rol rol, Date ultimoAcceso) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numIdentificacion = numIdentificacion;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.ultimoAcceso = ultimoAcceso;
    }
    
    
    
    // GETTERS Y SETTERS

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Rol getRol() {
        return rol;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}