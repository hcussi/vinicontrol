/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.producto;

import edu.utn.frm.entities.base.EntityBase;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Range;
import org.jdom.Element;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name="FINCA", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "codigo" }),
                @UniqueConstraint(columnNames = { "descripcion" })
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="Finca.findAll", query="select e from Finca e "),
	@NamedQuery(name="Finca.findById", query="select e from Finca e where id = :id")
})
public class Finca extends EntityBase{

    private String descripcion;
    private String codigo;
    private String direccion;
    private String razonSocial;
    private Integer cantidadHectareas;
    
    public Finca(){
        cantidadHectareas = 0;
    }

    /**
     * @return the descripcion
     */
    @Length(min = 0, max = 50, message = "La Descripción de la Finca debe estar entre {min} y {max} caracteres")
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
     * @return the codigo
     */
    @NotNull(message = "El Código de la Finca no puede ser nulo")
    @Length(min = 3, max = 10, message = "El Código de la Finca debe estar entre {min} y {max} caracteres")
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
     * @return the direccion
     */
    @NotNull(message = "La  Dirección la Finca no puede ser nulo")
    @Length(min = 5, max = 50, message = "La Dirección de la Finca debe estar entre {min} y {max} caracteres")
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the razonSocial
     */
    @NotNull(message = "La  Razón la Finca no puede ser nulo")
    @Length(min = 5, max = 50, message = "La Razón Social de la Finca debe estar entre {min} y {max} caracteres")
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the modelo
     */
    @Range(min=0,message="La Cantidad de Hectáreas de la Finca debe ser mayor a {min}")
    public Integer getCantidadHectareas() {
        return cantidadHectareas;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setCantidadHectareas(Integer cantidadHectareas) {
        this.cantidadHectareas = cantidadHectareas;
    }

    public Finca copy() {
        Finca copy = new Finca();

        copy.setCantidadHectareas(this.getCantidadHectareas());
        copy.setCodigo(this.getCodigo()+"-copy");
        copy.setDescripcion(this.getDescripcion()+"-copy");
        copy.setDireccion(this.getDireccion());
        copy.setRazonSocial(this.getRazonSocial());
        
        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/descripción ya existe";
    }

    public Element exportar(){
        Element elementFinca = new Element("finca");

        if(codigo != null)elementFinca.setAttribute("codigo", codigo);
        if(descripcion != null)elementFinca.setAttribute("descripcion", descripcion);
        if(direccion != null)elementFinca.setAttribute("direccion", direccion);
        if(razonSocial != null)elementFinca.setAttribute("razonSocial", razonSocial);
        if(cantidadHectareas != null)elementFinca.setAttribute("cantidadHectareas", cantidadHectareas.toString());

        return elementFinca;
    }


}