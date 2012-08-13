/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.persona;


import edu.utn.frm.entities.base.EntityBase;
import javax.persistence.MappedSuperclass;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 *
 * @author Proyecto
 */
@MappedSuperclass
public abstract class Persona extends EntityBase{

    private String nombre;
    private String apellido;
    private String dni;
    private String domicilio;
    private String localidad;
    private String telefono;

    public Persona(){

    }

    /**
     * @return the nombre
     */
    @NotNull(message = "El Nombre de la Persona no puede ser nulo")
    @Length(min = 3, max = 20, message = "El Nombre de la Persona debe estar entre {min} y {max} caracteres")
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    @NotNull(message = "El Apellido de la Persona no puede ser nulo")
    @Length(min = 3, max = 20, message = "El Apellido de la Persona debe estar entre {min} y {max} caracteres")
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the dni
     */
    @Length(min = 0, max = 8, message = "El DNI de la Persona debe estar entre {min} y {max} caracteres")
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the domicilio
     */
    @Length(min = 0, max = 50, message = "El Domicilio de la Persona debe estar entre {min} y {max} caracteres")
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * @param domicilio the domicilio to set
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * @return the localidad
     */
    @Length(min = 0, max = 50, message = "La Localidad de la Persona debe estar entre {min} y {max} caracteres")
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param localidad the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * @return the telefono
     */
    @Length(min = 0, max = 50, message = "El Teléfono de la Persona debe estar entre {min} y {max} caracteres")
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
