/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.regla;

import edu.utn.frm.entities.base.EntityBase;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Range;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name="REGLA_CONTROL_NIVEL", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "codigo" }),
                @UniqueConstraint(columnNames = { "descripcion" })
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="ReglaControlNivel.findAll", query="select e from ReglaControlNivel e "),
	@NamedQuery(name="ReglaControlNivel.findById", query="select e from ReglaControlNivel e where id = :id")
})
public class ReglaControlNivel extends EntityBase{

    private String codigo;
    private String descripcion;
    private Double nivelMaximoCapacidad;
    private Double nivelMinimoAzucar;
    
    public ReglaControlNivel(){
        nivelMaximoCapacidad = 500.0;
        nivelMinimoAzucar = 0.0;
    }

    /**
     * @return the codigo
     */
    @NotNull(message = "El Codigo de la Regla de Control de Nivel no puede ser nulo")
    @Length(min = 3, max = 10, message = "El Codigo de la Regla de Control de Nivel debe estar entre {min} y {max} caracteres")
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
    @NotNull(message = "La Descripción de la Regla de Control de Nivel no puede ser nulo")
    @Length(min = 5, max = 50, message = "La Descripción de la Regla de Control de Nivel debe estar entre {min} y {max} caracteres")
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
     *
     * @return the nivelMaximoCapacidad
     */
    @NotNull(message = "El Nivel Máximo de Capacidad de la Regla de Control de Nivel no puede ser nulo")
    @Range(min=0,message="El Nivel Máximo de Capacidad de la Regla de Control de Nivel debe ser mayor a {min}")
    public Double getNivelMaximoCapacidad() {
        return nivelMaximoCapacidad;
    }

    /**
     * @param nivelMaximoCapacidad the nivelMaximoCapacidad to set
     */
    public void setNivelMaximoCapacidad(Double nivelMaximoCapacidad) {
        this.nivelMaximoCapacidad = nivelMaximoCapacidad;
    }

    /**
     *
     * @return the nivelMinimoAzucar
     */
    @NotNull(message = "El Nivel Mínimo de Azúcar de la Regla de Control de Nivel no puede ser nulo")
    @Range(min=0,message="El Nivel Mínimo de Azúcar de la Regla de Control de Nivel debe ser mayor a {min}")
    public Double getNivelMinimoAzucar() {
        return nivelMinimoAzucar;
    }

    /**
     * @param nivelMinimoAzucar the nivelMinimoAzucar to set
     */
    public void setNivelMinimoAzucar(Double nivelMinimoAzucar) {
        this.nivelMinimoAzucar = nivelMinimoAzucar;
    }

    public ReglaControlNivel copy() {
        ReglaControlNivel copy = new ReglaControlNivel();

        copy.setCodigo(this.getCodigo()+"-copy");
        copy.setDescripcion(this.getDescripcion()+"-copy");
        copy.setNivelMaximoCapacidad(this.getNivelMaximoCapacidad());
        copy.setNivelMinimoAzucar(this.getNivelMinimoAzucar());
        
        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/descripción ya existe";
    }

}