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
import org.jdom.Element;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name="VARIEDAD", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "codigo" }),
                @UniqueConstraint(columnNames = { "descripcion" })
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="Variedad.findAll", query="select e from Variedad e "),
	@NamedQuery(name="Variedad.findById", query="select e from Variedad e where id = :id")
})
public class Variedad extends EntityBase{

    private String codigo;
    private String descripcion;
    private String tinte;
    
    public Variedad(){

    }

    /**
     * @return the codigo
     */
    @NotNull(message = "El Código de la Variedad no puede ser nulo")
    @Length(min = 3, max = 10, message = "El Código de la Variedad debe estar entre {min} y {max} caracteres")
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
    @NotNull(message = "La Descripción de la Variedad no puede ser nulo")
    @Length(min = 3, max = 20, message = "La Descripción de la Variedad debe estar entre {min} y {max} caracteres")
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
     * @return the tinte
     */
    @NotNull(message = "El Tinte de la Variedad no puede ser nulo")
    @Length(min = 3, max = 20, message = "El Tinte de la Variedad debe estar entre {min} y {max} caracteres")
    public String getTinte() {
        return tinte;
    }

    /**
     * @param tinte the tinte to set
     */
    public void setTinte(String tinte) {
        this.tinte = tinte;
    }

    public Variedad copy() {
        Variedad copy = new Variedad();

        copy.setCodigo(this.getCodigo()+"-copy");
        copy.setDescripcion(this.getDescripcion()+"-copy");
        copy.setTinte(this.getTinte());

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/descripción ya existe";
    }

    public Element exportar(){
        Element elementVariedad = new Element("variedad");

        if(codigo != null)elementVariedad.setAttribute("codigo", codigo);
        if(descripcion != null)elementVariedad.setAttribute("descripcion", descripcion);
        if(tinte != null)elementVariedad.setAttribute("tinte", tinte);

        return elementVariedad;
    }

}