/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.alarma;
import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.base.IExportable;
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
@Table(name="ALARMA_REMONTAJE")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="AlarmaRemontaje.findAll", query="select e from AlarmaRemontaje e "),
	@NamedQuery(name="AlarmaRemontaje.findById", query="select e from AlarmaRemontaje e where id = :id")
})
public class AlarmaRemontaje extends Alarma implements IExportable{

    private Boolean inicio;

    public AlarmaRemontaje(){
        super();
        inicio = true;
    }

    public Boolean getInicio() {
        return inicio;
    }

    public void setInicio(Boolean inicio) {
        this.inicio = inicio;
    }

    @Transient
    public String getInicioString(){
        return inicio? "Iniciar":"Detener";
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "Ha ocurrido un error inesperado al guardar";
    }

    public Element exportar(){
        Element elementAlarmaRemontajeItem = new Element("alarmaRemontajeItem");

        elementAlarmaRemontajeItem.setAttribute("tipo", "remontaje");
        super.exportar(elementAlarmaRemontajeItem);
        if(inicio != null)elementAlarmaRemontajeItem.setAttribute("inicio", getInicioString());

        return elementAlarmaRemontajeItem;
    }

    @Override
    public String toString() {
        return super.toString() + " Estado: " + getInicioString();
    }

    public EntityBase copy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}