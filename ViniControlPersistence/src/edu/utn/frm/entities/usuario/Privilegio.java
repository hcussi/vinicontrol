/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.usuario;

import edu.utn.frm.entities.base.EntityBase;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name="PRIVILEGIO", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "tipoPrivilegio" }),
                @UniqueConstraint(columnNames = { "descripcion" })
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="Privilegio.findAll", query="select e from Privilegio e "),
	@NamedQuery(name="Privilegio.findById", query="select e from Privilegio e where id = :id")
})
public class Privilegio extends EntityBase{

    private String descripcion;
    private TipoPrivilegio tipoPrivilegio;
    private boolean seleccionado;
    
    public Privilegio(){
        tipoPrivilegio = TipoPrivilegio.REGLA_CONTROL;
    }

    /**
     * @return the descripcion
     */
    @NotNull(message = "La Descripción del Privilegio no puede ser nulo")
    @Length(min = 5, max = 50, message = "La Descripción del Privilegio debe estar entre {min} y {max} caracteres")
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
     * @return the tipoPrivilegio
     */
    @Enumerated(EnumType.STRING)
    public TipoPrivilegio getTipoPrivilegio() {
        return tipoPrivilegio;
    }

    /**
     * @param tipoPrivilegio the tipoPrivilegio to set
     */
    public void setTipoPrivilegio(TipoPrivilegio tipoPrivilegio) {
        this.tipoPrivilegio = tipoPrivilegio;
    }

    /**
     * @return the seleccionado
     */
    @Transient
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el tipo privilegio/descripción ya existe";
    }

    public Privilegio copy() {
        return null;
    }

}