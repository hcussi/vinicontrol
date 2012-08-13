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
import org.hibernate.validator.NotNull;
import org.jdom.Element;


/**
 *
 * @author Proyecto
 */
@Entity
@Table(name="ALARMA_TEMPERATURA")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="AlarmaTemperatura.findAll", query="select e from AlarmaTemperatura e "),
	@NamedQuery(name="AlarmaTemperatura.findById", query="select e from AlarmaTemperatura e where id = :id")
})
public class AlarmaTemperatura extends Alarma{

    private Double temperatura;
    private Boolean maxima;

    public AlarmaTemperatura(){
        super();
        maxima = true;
    }

    @NotNull(message="El valor de la temperatura no puede ser nulo")
    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Boolean getMaxima() {
        return maxima;
    }

    public void setMaxima(Boolean maxima) {
        this.maxima = maxima;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "Ha ocurrido un error inesperado al guardar";
    }

    public Element exportar(){
        Element elementAlarmaTemperaturaItem = new Element("alarmaTemperaturaItem");

        elementAlarmaTemperaturaItem.setAttribute("tipo", "temperatura");
        super.exportar(elementAlarmaTemperaturaItem);
        if(temperatura != null)elementAlarmaTemperaturaItem.setAttribute("temperatura", temperatura.toString());

        return elementAlarmaTemperaturaItem;
    }

    @Override
    public String toString() {
        return super.toString() + " Temperatura: " + temperatura;
    }

    public EntityBase copy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}