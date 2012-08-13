/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.regla;

import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.utils.DateOperations;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Range;
import org.jdom.Element;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name = "MOMENTO_REMONTAJE")
@Inheritance(strategy = InheritanceType.JOINED)
public class MomentoRemontaje extends EntityBase{

    private Integer duracion;
    private Date hora;
    private RemontajeDia remontajeDia;

    public MomentoRemontaje() {
        duracion = 30;
        hora = new Date();
    }

    /**
     * @return the duracion
     */
    @NotNull(message = "La Duración de la Regla de Control no puede ser nula")
    @Range(min=1,message="La Duración de la Regla de Control debe ser mayor a {min}")
    public Integer getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the hora
     */
    @Basic
    @Temporal(TemporalType.TIME)
    @NotNull(message="La Hora de la Regla de Control no puede ser nula")
    public Date getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Date hora) {
        this.hora = hora;
    }

    @ManyToOne(optional=false,targetEntity=RemontajeDia.class,fetch=FetchType.LAZY)
    @JoinColumn(name="remontajeDiaId")
    @PrimaryKeyJoinColumn
    public RemontajeDia getRemontajeDia() {
        return remontajeDia;
    }

    public void setRemontajeDia(RemontajeDia remontajeDia) {
        this.remontajeDia = remontajeDia;
    }

    @Transient
    public String getHoraInicioString(){

        return DateOperations.toHHmmssString(hora, ":");
    }

    @Transient
    public String getHoraFinString(){

        Date horaFin = DateOperations.add(hora, duracion, DateOperations.MINUTE);
        return DateOperations.toHHmmssString(horaFin, ":");
    }

    public MomentoRemontaje copy() {
        MomentoRemontaje copy = new MomentoRemontaje();

        copy.setDuracion(this.getDuracion());
        copy.setHora(this.getHora());
        
        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, ha ocurrido un error inesperado";
    }

    public Element exportar(){
        Element elementMomentoRemontaje = new Element("momentoRemontaje");

        if(duracion != null)elementMomentoRemontaje.setAttribute("duracion", duracion.toString());
        if(hora != null)elementMomentoRemontaje.setAttribute("hora", getHoraInicioString());

        return elementMomentoRemontaje;
    }


}