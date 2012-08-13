/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.comunicacion;


import edu.utn.frm.entities.base.EntityBase;
import javax.persistence.MappedSuperclass;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 *
 * @author Proyecto
 */
@MappedSuperclass
public abstract class ConfiguracionConexion extends EntityBase{

    private String codigo;
    private String descripcion;
    private Boolean activa;

    public ConfiguracionConexion(){
        activa = true;
    }

    /**
     * @return the codigo
     */
    @NotNull(message = "El Código de la Configuración Conexión no puede ser nulo")
    @Length(min = 3, max = 20, message = "El Código de la Configuración Conexión debe estar entre {min} y {max} caracteres")
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descripcion
     */
    @NotNull(message = "La Descripción de la Configuración Conexión no puede ser nulo")
    @Length(min = 3, max = 50, message = "La Descripción de la Configuración Conexión debe estar entre {min} y {max} caracteres")
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the activa
     */
    public Boolean getActiva() {
        return activa;
    }

    /**
     * @param activa the activa to set
     */
    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

}