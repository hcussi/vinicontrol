/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.tanque;

import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.utils.DateOperations;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Range;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name = "ESTADO_TANQUE")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "EstadoTanque.findAll", query = "select e from EstadoTanque e "),
    @NamedQuery(name = "EstadoTanque.findById", query = "select e from EstadoTanque e where id = :id")
})
public class EstadoTanque extends EntityBase{

    private Date fechaHora;
    private Double nivelAzucar;
    private Double nivelContenido;
    private Double temperatura;
    private Proceso proceso;

    public EstadoTanque() {
        fechaHora = new Date();
    }

    /**
     * @return the fechaHora
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "La Fecha Hora del Estado del Tanque no puede ser nulo")
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * @return the nivelAzucar
     */
    @NotNull(message = "El Nivel de Azúcar del Estado del Tanque no puede ser nulo")
    public Double getNivelAzucar() {
        return nivelAzucar;
    }

    /**
     * @param nivelAzucar the nivelAzucar to set
     */
    public void setNivelAzucar(Double nivelAzucar) {
        this.nivelAzucar = nivelAzucar;
    }

    /**
     * @return the temperatura
     */
    @NotNull(message = "la Temperatura del Estado del Tanque no puede ser nulo")
    public Double getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    /**
     *
     * @return the capacidad
     */
    @NotNull(message = "El Nivel de Contenido del Estado del Tanque no puede ser nulo")
    @Range(min=0,message="El Nivel de Contenido del Estado del Tanque debe ser mayor a {min}")
    public Double getNivelContenido() {
        return nivelContenido;
    }

    /**
     *
     * @param nivelContenido the nivelContenido to set
     */
    public void setNivelContenido(Double nivelContenido) {
        this.nivelContenido = nivelContenido;
    }

    /**
     *
     * @return the proceso
     */
    @ManyToOne(optional=false,targetEntity=Proceso.class)
    @JoinColumn(name="procesoId")
    @PrimaryKeyJoinColumn
    @NotNull(message = "El Estado del Tanque debe tener un Proceso asociado")
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

    public EstadoTanque copy() {
        EstadoTanque copy = new EstadoTanque();

        copy.setFechaHora(this.getFechaHora());
        copy.setNivelAzucar(this.getNivelAzucar());
        copy.setNivelContenido(this.getNivelContenido());
        copy.setProceso(this.getProceso());
        copy.setTemperatura(this.getTemperatura());
        
        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, ha ocurrido un error inesperado";
    }

    @Override
    public boolean equals(Object obj) {
        if( obj instanceof EstadoTanque ){
            EstadoTanque estadoTanque = (EstadoTanque)obj;
            if( estadoTanque.getNivelAzucar().equals(this.getNivelAzucar()) &&
                    estadoTanque.getNivelContenido().equals(this.getNivelContenido()) &&
                    estadoTanque.getTemperatura().equals(this.getTemperatura())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Transient
    public String getFechaHoraString(){
        return DateOperations.toDateString(fechaHora, "/", ":");
    }

    @Override
    public String toString() {
        return "Fecha: "+ getFechaHoraString() +" Nivel Azucar: "+ nivelAzucar +
                " Nivel Contenido: " + nivelContenido + " Temperatura: " +temperatura;
    }

}