/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.alarma;


import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.base.IExportable;
import edu.utn.frm.entities.proceso.EstadoProceso;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.utils.DateOperations;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.validator.NotNull;
import org.jdom.Element;

/**
 *
 * @author Proyecto
 */
@MappedSuperclass
public abstract class Alarma extends EntityBase implements IExportable{

    private Date fecha;
    private Date hora;
    private Date duracion;
    private Proceso proceso;

    public Alarma(){
        fecha = new Date();
        hora = new Date();
        duracion = new Date();
        DateOperations.cleanHHmmss(duracion);
    }

    /**
     * @return the fecha
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message="La Fecha de la Alarma no puede ser nula")
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    @Basic
    @Temporal(TemporalType.TIME)
    @NotNull(message="La Hora de la Alarma no puede ser nula")
    public Date getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Date hora) {
        this.hora = hora;
    }

    @Basic
    @Temporal(TemporalType.TIME)
    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    /**
     *
     * @return the proceso
     */
    @ManyToOne(optional=false,targetEntity=Proceso.class)
    @JoinColumn(name="procesoId")
    @PrimaryKeyJoinColumn
    @NotNull(message = "La Alarma debe tener un Proceso asociado")
    public Proceso getProceso() {
        return proceso;
    }

    /**
     *
     * @param proceso the proceso to set
     */
    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    @Transient
    public String getFechaString(){
        return DateOperations.toDDMMYYYYString(fecha, "/");
    }

    @Transient
    public String getHoraString(){
        return DateOperations.toHHmmssString(hora, ":");
    }

    @Transient
    public String getDuracionString(){
        return DateOperations.toHHmmssString(duracion, ":");
    }

    @Override
    public String toString() {
        return "Fecha: " + getFechaString() + " Hora: " + getHoraString();
    }

    @Transient
    public EstadoProceso getEstadoProceso(){
        return proceso.getEstadoProceso();
    }

    @Transient
    public String getDescripcionTanqueString(){
        return proceso.getTanque().getDescripcion();
    }
    @Transient
    public String getDescripcionProcesoString(){
        return proceso.getDescripcion();
    }

    public void exportar(Element elementAlarma){
        if(getFecha() != null)elementAlarma.setAttribute("fecha", getFechaString());
        if(getHora() != null)elementAlarma.setAttribute("hora", getHoraString());
        if(getDuracion() != null)elementAlarma.setAttribute("duracion", getDuracion().toString());
    }

}