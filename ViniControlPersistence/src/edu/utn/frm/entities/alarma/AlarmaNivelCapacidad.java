/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.alarma;
import edu.utn.frm.entities.base.EntityBase;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import javax.persistence.Transient;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.jdom.Element;


/**
 *
 * @author Proyecto
 */
@Entity
@Table(name="ALARMA_NIVEL_CAPACIDAD")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="AlarmaNivelCapacidad.findAll", query="select e from AlarmaNivelCapacidad e "),
	@NamedQuery(name="AlarmaNivelCapacidad.findById", query="select e from AlarmaNivelCapacidad e where id = :id")
})
public class AlarmaNivelCapacidad extends Alarma{

    private Double nivelCapacidad;

    public AlarmaNivelCapacidad(){
        super();
    }

    public Double getNivelCapacidad() {
        return nivelCapacidad;
    }

    public void setNivelCapacidad(Double nivelCapacidad) {
        this.nivelCapacidad = nivelCapacidad;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "Ha ocurrido un error inesperado al guardar";
    }

    public Element exportar(){
        Element elementAlarmaCapacidadItem = new Element("alarmaCapacidadItem");

        elementAlarmaCapacidadItem.setAttribute("tipo", "capacidad");
        super.exportar(elementAlarmaCapacidadItem);
        if( nivelCapacidad != null ) elementAlarmaCapacidadItem.setAttribute("nivelCapacidad", getNivelCapacidad().toString());

        return elementAlarmaCapacidadItem;
    }

    @Override
    public String toString() {
        return super.toString() + " Nivel: " + nivelCapacidad;
    }

    public EntityBase copy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}