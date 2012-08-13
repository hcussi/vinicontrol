/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.producto;


import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.utils.DateOperations;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.Future;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Range;
import org.jdom.Element;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name = "PESADA")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Pesada.findAll", query = "select e from Pesada e "),
    @NamedQuery(name = "Pesada.findById", query = "select e from Pesada e where id = :id")
})
public class Pesada extends EntityBase{

    private Date fecha;
    private Double valor;
    private Camion camion;
    private Finca finca;
    private MostoEnTanque mostoEnTanque;
    private UnidadMedida unidadMedida;

    public Pesada() {
        valor = 100.0;
        unidadMedida = UnidadMedida.KILO;
    }

    /**
     * @return the fecha
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "La Fecha de la Pesada no puede ser nulo")
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
     * @return the valor
     */
    @NotNull(message = "El Valor de la Pesada no puede ser nulo")
    @Range(min = 0, message = "El Valor de la Pesada debe ser mayor a {min}")
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the camion
     */
    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY,optional=true,targetEntity=Camion.class)
    @JoinColumn(name = "camionId")
    @PrimaryKeyJoinColumn
    public Camion getCamion() {
        return camion;
    }

    /**
     *
     * @param camion the camion to set
     */
    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    /**
     * @return the finca
     */
    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY,optional=true,targetEntity=Finca.class)
    @JoinColumn(name = "fincaId")
    @PrimaryKeyJoinColumn
    public Finca getFinca() {
        return finca;
    }

    /**
     *
     * @param finca the finca to set
     */
    public void setFinca(Finca finca) {
        this.finca = finca;
    }

    /**
     * @return the mostoEnTanque
     */
    @ManyToOne(cascade=CascadeType.REFRESH, optional=false,fetch=FetchType.LAZY,targetEntity=MostoEnTanque.class)
    @JoinColumn(name = "mostoEnTanqueId")
    public MostoEnTanque getMostoEnTanque() {
        return mostoEnTanque;
    }

    /**
     *
     * @param mostoEnTanque the mostoEnTanque to set
     */
    public void setMostoEnTanque(MostoEnTanque mostoEnTanque) {
        this.mostoEnTanque = mostoEnTanque;
    }

    /**
     * @return the unidadMedida
     */
    @Enumerated(EnumType.STRING)
    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    /**
     *
     * @param unidadMedida the unidadMedida to set
     */
    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @PrePersist
    @Override
    public void PrePersist() {
        super.PrePersist();
        fecha = new Date();
    }

    @Transient
    public String getFechaString(){

        return DateOperations.toDDMMYYYYString(fecha, "/");
    }

    public Pesada copy() {
        Pesada copy = new Pesada();

        copy.setCamion(this.getCamion());
        copy.setFecha(this.getFecha());
        copy.setFinca(this.getFinca());
        copy.setUnidadMedida(this.getUnidadMedida());
        copy.setValor(this.getValor());

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, ha ocurrido un error inesperado";
    }

     public Element exportar(){
        Element elementPesada = new Element("pesadaItem");

        if(valor != null)elementPesada.setAttribute("valor", valor.toString());
        if(fecha != null)elementPesada.setAttribute("fecha", getFechaString());
        if(unidadMedida != null)elementPesada.setAttribute("unidadMedida", unidadMedida.toString());

        if(camion!=null)elementPesada.addContent(camion.exportar());
        if(finca!=null)elementPesada.addContent(finca.exportar());

        return elementPesada;
    }

}
