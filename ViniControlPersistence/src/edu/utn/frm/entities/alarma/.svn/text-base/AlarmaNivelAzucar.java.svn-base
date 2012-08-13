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
@Table(name="ALARMA_NIVEL_AZUCAR")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="AlarmaNivelAzucar.findAll", query="select e from AlarmaNivelAzucar e "),
	@NamedQuery(name="AlarmaNivelAzucar.findById", query="select e from AlarmaNivelAzucar e where id = :id")
})
public class AlarmaNivelAzucar extends Alarma{

    private Double nivelAzucar;

    public AlarmaNivelAzucar(){
        super();
    }

    public Double getNivelAzucar() {
        return nivelAzucar;
    }

    public void setNivelAzucar(Double nivelAzucar) {
        this.nivelAzucar = nivelAzucar;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "Ha ocurrido un error inesperado al guardar";
    }

    public Element exportar(){
        Element elementAlarmaAzucarItem = new Element("alarmaAzucarItem");

        elementAlarmaAzucarItem.setAttribute("tipo", "azucar");
        super.exportar(elementAlarmaAzucarItem);
        if( nivelAzucar != null)elementAlarmaAzucarItem.setAttribute("nivelAzucar", getNivelAzucar().toString());

        return elementAlarmaAzucarItem;
    }

    @Override
    public String toString() {
        return super.toString() + " Nivel Azúcar: " + nivelAzucar;
    }

    public EntityBase copy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}