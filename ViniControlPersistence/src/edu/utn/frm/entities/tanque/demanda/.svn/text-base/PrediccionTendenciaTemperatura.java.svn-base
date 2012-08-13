/*
 * PrediccionTendenciaTemperatura.java
 *
 * Created on 27 de noviembre de 2006, 20:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.utn.frm.entities.tanque.demanda;

import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.utils.Mat;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.NotNull;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name="PREDICCION_TENDENCIA_TEMPERATURA")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="PrediccionTendenciaTemperatura.findAll", query="select e from PrediccionTendenciaTemperatura e "),
	@NamedQuery(name="PrediccionTendenciaTemperatura.findById", query="select e from PrediccionTendenciaTemperatura e where id = :id")
})
public class PrediccionTendenciaTemperatura extends EntityBase{
    
    private Double temperaturaReal;
    private Double promedioAnterior;
    private Double tendenciaAnterior;
    private Double alfa;
    private Double beta;
    private Integer periodo;
    private Proceso proceso;
    
    /** Creates a new instance of PrediccionTendenciaTemperatura */
    public PrediccionTendenciaTemperatura() {
        
    }
    
    /** Creates a new instance of PrediccionTendenciaTemperatura */
    public PrediccionTendenciaTemperatura(double alfa,double beta,int periodo,Proceso proceso,double tendenciaAnterior,double promedioAnterior,Double temperaturaReal) {
        this.alfa = alfa;
        this.beta = beta;
        this.periodo = periodo;
        this.proceso = proceso;
        this.tendenciaAnterior = tendenciaAnterior;
        this.promedioAnterior = promedioAnterior;
        this.temperaturaReal = temperaturaReal;
    }

    @NotNull
    public Double getTemperaturaReal() {
        return temperaturaReal;
    }
    
    public void setTemperaturaReal(Double temperaturaReal) {
        this.temperaturaReal = temperaturaReal;
    }

    @NotNull
    public Double getPromedioAnterior() {
        return promedioAnterior;
    }
    
    public void setPromedioAnterior(Double promedioAnterior) {
        this.promedioAnterior = promedioAnterior;
    }

    @NotNull
    public Double getTendenciaAnterior() {
        return tendenciaAnterior;
    }
    
    public void setTendenciaAnterior(Double tendenciaAnterior) {
        this.tendenciaAnterior = tendenciaAnterior;
    }

    @NotNull
    public Integer getPeriodo() {
        return periodo;
    }
    
    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    /**
     *
     * @return the tanque
     */
    @ManyToOne(optional=false,targetEntity=Proceso.class)
    @JoinColumn(name="procesoId")
    @PrimaryKeyJoinColumn
    @NotNull(message = "La Predicción debe tener un Proceso asociado")
    public Proceso getProceso() {
        return proceso;
    }
    
    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    @NotNull
    public Double getAlfa() {
        return this.alfa;
    }
    
    public void setAlfa(Double alfa) {
        this.alfa = alfa;
    }

    @NotNull
    public Double getBeta() {
        return this.beta;
    }
    
    public void setBeta(Double beta) {
        this.beta = beta;
    }
    
    @Override
    public String toString(){
        
        return "Tendencia: \nalfa:"+this.alfa+" beta:"+this.beta+" tendenciaA:"+this.tendenciaAnterior+" promedioA:"+this.promedioAnterior;
    }
    
    @Transient
    public Double getError() {
        return Mat.abs(this.temperaturaReal-this.promedioAnterior);
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public EntityBase copy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}