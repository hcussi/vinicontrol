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
@Table(name="ESTADO_PREDICCION")
@NamedQueries({
    @NamedQuery(name = "EstadoPrediccion.findAll", query = "select e from EstadoPrediccion e "),
    @NamedQuery(name = "EstadoPrediccion.findById", query = "select e from EstadoPrediccion e where id = :id")
})
public class EstadoPrediccion extends EntityBase{
    
    //Suavizamiento Exponencial con Tendencia
    private Double alfaTendencia;
    private Double betaTendencia;
    private Double promTendencia;
    private Double tenTendencia;
    
    
    /** Creates a new instance of EstadoPrediccion */
    public EstadoPrediccion() {
        init();
    }
    
    public void init(){
        
        //Suavizamiento Exponencial con Tendencia
        setAlfaTendencia(0.2);
        setBetaTendencia(0.05);
        setPromTendencia(0.0);
        setTenTendencia(0.0);
        
    }
    
    @NotNull(message="alfa tendencia es un valor requerido")
    @Range(min=0,message="alfa tendencia no puede tener un valor negativo")
    public Double getAlfaTendencia() {
        return this.alfaTendencia;
    }
    
    public void setAlfaTendencia(Double alfaTendencia) {
        this.alfaTendencia = alfaTendencia;
    }
    
    @NotNull(message="beta tendencia es un valor requerido")
    @Range(min=0,message="beta tendencia no puede tener un valor negativo")
    public Double getBetaTendencia() {
        return this.betaTendencia;
    }
    
    public void setBetaTendencia(Double betaTendencia) {
        this.betaTendencia = betaTendencia;
    }
    
    @NotNull(message="promedio tendencia es un valor requerido")
    @Range(min=0,message="promedio tendencia no puede tener un valor negativo")
    public Double getPromTendencia() {
        return this.promTendencia;
    }
    
    public void setPromTendencia(Double promTendencia) {
        this.promTendencia = promTendencia;
    }
    
    @NotNull(message="la tendencia es un valor requerido")
    public Double getTenTendencia() {
        return this.tenTendencia;
    }
    
    public void setTenTendencia(Double tenTendencia) {
        this.tenTendencia = tenTendencia;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, la fecha de inicio ya existe";
    }

    public EstadoPrediccion copy() {
        throw new UnsupportedOperationException("No soporta copiar el estado");
    }
    
}