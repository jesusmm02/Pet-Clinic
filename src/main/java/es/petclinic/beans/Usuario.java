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

@Entity
@Table(name = "usuarios", uniqueConstraints = { // Campos que no pueden tener valores repetidos
    @UniqueConstraint(columnNames = "Dni", name = "UK_usuario_dni"),
    @UniqueConstraint(columnNames = "Email", name = "UK_usuario_email")
})
@Inheritance(strategy = InheritanceType.JOINED) // Herencia entre clases
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Integer id;
    
    @Column(name = "Nombre", length = 30)
    private String nombre;
    
    @Column(name = "Apellidos", length = 60)
    private String apellidos;
    
    @Column(name = "DNI", length = 9, nullable = false)
    private String dni;
    
    @Column(name = "Email", length = 80, nullable = false)
    private String email;
    
    @Column(name = "Password", length = 128, nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Rol", length = 11, nullable = false)
    private Rol rol;
    
    public enum Rol {
        VETERINARIO, CLIENTE
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name = "UltimoAcceso")
    private Date ultimoAcceso;
    
    @Column(name = "Avatar", length = 30, nullable = false)
    private String avatar = "avatar.png";

    
    
    // CONSTRUCTORES
    
    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String apellidos, String dni, String email, String password, Rol rol, Date ultimoAcceso) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
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

    public String getDni() {
        return dni;
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

    public void setDni(String dni) {
        this.dni = dni;
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