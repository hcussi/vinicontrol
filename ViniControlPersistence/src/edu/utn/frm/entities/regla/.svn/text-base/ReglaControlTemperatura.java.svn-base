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
@Table(name="REGLA_CONTROL_TEMPERATURA", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "codigo" }),
                @UniqueConstraint(columnNames = { "descripcion" })
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="ReglaControlTemperatura.findAll", query="select e from ReglaControlTemperatura e "),
	@NamedQuery(name="ReglaControlTemperatura.findById", query="select e from ReglaControlTemperatura e where id = :id")
})
public class ReglaControlTemperatura extends EntityBase{

    private String codigo;
    private String descripcion;
    private Double temperaturaMaxima;
    private Double temperaturaMinima;
    private Double temperaturaTolerancia;
    
    public ReglaControlTemperatura(){
        temperaturaMaxima = 20.0;
        temperaturaMinima = 15.0;
        temperaturaTolerancia = 2.0;
    }

    /**
     * @return the codigo
     */
    @NotNull(message = "El Código de la Regla de Control de Temperatura no puede ser nulo")
    @Length(min = 3, max = 10, message = "El Código de la Regla de Control de Temperatura debe estar entre {min} y {max} caracteres")
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
    @NotNull(message = "La Descripción de la Regla de Control de Temperatura no puede ser nulo")
    @Length(min = 5, max = 50, message = "La Descripción de la Regla de Control de Temperatura debe estar entre {min} y {max} caracteres")
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
     * @return the temperaturaMaxima
     */
    @NotNull(message = "La Temperatura Máxima de la Regla de Control de Temperatura no puede ser nulo")
    @Range(min=1,max=50,message="La Temperatura Máxima de la Regla de Control de Temperatura debe ser entre {min} y {max}")
    public Double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    /**
     * @param temperaturaMaxima the temperaturaMaxima to set
     */
    public void setTemperaturaMaxima(Double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    /**
     *
     * @return the temperaturaMinima
     */
    @NotNull(message = "La Temperatura Mínima de la Regla de Control de Temperatura no puede ser nulo")
    @Range(min=1,max=50,message="La Temperatura Mínima de la Regla de Control de Temperatura debe ser entre {min} y {max}")
    public Double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    /**
     * @param temperaturaMinima the temperaturaMinima to set
     */
    public void setTemperaturaMinima(Double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    /**
     *
     * @return the temperaturaTolerancia
     */
    @NotNull(message = "La Temperatura Tolerancia de la Regla de Control de Temperatura no puede ser nulo")
    @Range(min=0,message="La Temperatura Tolerancia de la Regla de Control de Temperatura debe ser mayor a {min}")
    public Double getTemperaturaTolerancia() {
        return temperaturaTolerancia;
    }

    /**
     * @param temperaturaTolerancia the temperaturaTolerancia to set
     */
    public void setTemperaturaTolerancia(Double temperaturaTolerancia) {
        this.temperaturaTolerancia = temperaturaTolerancia;
    }

    public ReglaControlTemperatura copy() {
        ReglaControlTemperatura copy = new ReglaControlTemperatura();

        copy.setCodigo(this.getCodigo()+"-copy");
        copy.setDescripcion(this.getDescripcion()+"-copy");
        copy.setTemperaturaMaxima(this.getTemperaturaMaxima());
        copy.setTemperaturaMinima(this.getTemperaturaMinima());
        copy.setTemperaturaTolerancia(this.getTemperaturaTolerancia());

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/descripción ya existe";
    }

}