/*
 * EstadoPrediccion.java
 *
 * Created on 27 de noviembre de 2006, 22:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.utn.frm.entities.tanque.demanda;


import edu.utn.frm.entities.base.EntityBase;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Range;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name="ESTADO_PREDICCION_TENDENCIA")
@NamedQueries({
    @NamedQuery(name = "EstadoPrediccionTendencia.findAll", query = "select e from EstadoPrediccionTendencia e "),
    @NamedQuery(name = "EstadoPrediccionTendencia.findById", query = "select e from EstadoPrediccionTendencia e where id = :id")
})
public class EstadoPrediccionTendencia extends EntityBase{
    
    //Suavizamiento Exponencial con Tendencia
    private Double alfaTendencia;
    private Double betaTendencia;
    private Double promTendencia;
    private Double tenTendencia;
    private Boolean editTendencia;
    private Integer lastTendencia;
    
    /** Creates a new instance of EstadoPrediccion */
    public EstadoPrediccionTendencia() {
        editTendencia = false;
        lastTendencia = 0;
    }
    
    @NotNull(message="alfa de Tendencia es un valor requerido")
    @Range(min=0,max=1,message="alfa de Tendencia tiene que estar entre 0 y 1")
    public Double getAlfaTendencia() {
        return this.alfaTendencia;
    }

    public void setAlfaTendencia(Double alfaTendencia) {
        this.alfaTendencia = alfaTendencia;
    }

    @NotNull(message="beta de Simple es un valor requerido")
    @Range(min=0,max=1,message="beta de Tendencia tiene que estar entre 0 y 1")
    public Double getBetaTendencia() {
        return this.betaTendencia;
    }

    public void setBetaTendencia(Double betaTendencia) {
        this.betaTendencia = betaTendencia;
    }

    @NotNull(message="promedio de Tendencia es un valor requerido")
    @Range(min=0,message="promedio de Tendencia tiene que ser una valor positivo")
    public Double getPromTendencia() {
        return this.promTendencia;
    }

    public void setPromTendencia(Double promTendencia) {
        this.promTendencia = promTendencia;
    }
    
    @NotNull(message="tendencia de Tendencia es un valor requerido")
    public Double getTenTendencia() {
        return this.tenTendencia;
    }

    public void setTenTendencia(Double tenTendencia) {
        this.tenTendencia = tenTendencia;
    }

    public Boolean isEditTendencia() {
        return this.editTendencia;
    }

    public void setEditTendencia(Boolean editTendencia) {
        this.editTendencia = editTendencia;
    }

    @NotNull(message="El ultimo periodo de tendencia es un valor requerido")
    @Range(min=0,message="la cantidad de estaciones debe ser mayor a 0")
    public Integer getLastTendencia() {
        return this.lastTendencia;
    }

    public void setLastTendencia(Integer lastTendencia) {
        this.lastTendencia = lastTendencia;
    }
    
    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, la fecha de inicio ya existe";
    }

    public EstadoPrediccionTendencia copy() {
        throw new UnsupportedOperationException("No soporta copiar el estado");
    }
}