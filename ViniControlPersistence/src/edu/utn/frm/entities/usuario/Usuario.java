/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.usuario;

import edu.utn.frm.entities.persona.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name = "USUARIO", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"usuario"}),
    @UniqueConstraint(columnNames = {"password"})
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "select e from Usuario e "),
    @NamedQuery(name = "Usuario.findById", query = "select e from Usuario e where id = :id")
})
public class Usuario extends Persona{

    private String password;
    private String usuario;
    private List<Rol> roles;

    public Usuario() {
        roles = new ArrayList<Rol>();
    }

    /**
     * @return the pasword
     */
    @NotNull(message = "El Password del Usuario no puede ser nulo")
    @Length(min = 5, max = 50, message = "El Password del Usuario debe estar entre {min} y {max} caracteres")
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the usuario
     */
    @NotNull(message = "El Nombre de Usuario no puede ser nulo")
    @Length(min = 5, max = 20, message = "El Nombre de Usuario debe estar entre {min} y {max} caracteres")
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the roles
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Rol.class)
    public List<Rol> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    @PrePersist
    @Override
    public void PrePersist() {
        super.PrePersist();
        password = EncriptadorPassword.encriptarPassword(password);
    }

    @PreUpdate
    @Override
    public void PreUpdate() {
        super.PreUpdate();
        password = EncriptadorPassword.encriptarPassword(password);
    }

    public Usuario copy() {
        Usuario copy = new Usuario();

        copy.setApellido(this.getApellido());
        copy.setDni(this.getDni());
        copy.setDomicilio(this.getDomicilio());
        copy.setLocalidad(this.getLocalidad());
        copy.setNombre(this.getNombre());
        copy.setPassword("123456");
        for (Rol rol : roles) {
            copy.getRoles().add( rol );
        }
        copy.setTelefono(this.getTelefono());
        copy.setUsuario(this.getUsuario() + "-copy");
        
        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el usuario/password ya existe";
    }
}
