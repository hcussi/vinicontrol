/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.producto;

import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.utils.DateOperations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Range;
import org.jdom.Element;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name = "MOSTO_EN_TANQUE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"codigo"}),
    @UniqueConstraint(columnNames = {"descripcion"})
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "MostoEnTanque.findAll", query = "select e from MostoEnTanque e "),
    @NamedQuery(name = "MostoEnTanque.findById", query = "select e from MostoEnTanque e where id = :id")
})
public class MostoEnTanque extends EntityBase {

    private String descripcion;
    private String codigo;
    private Date fechaEgreso;
    private Date fechaIngreso;
    private Double volumenFinal;
    private Double volumenInicial;
    private List<Pesada> pesadas;
    private Variedad variedad;

    public MostoEnTanque() {
        fechaIngreso = new Date();
        volumenFinal = 0.0;
        volumenInicial = 0.0;
        pesadas = new ArrayList<Pesada>();
    }

    /**
     * @return the descripcion
     */
    @NotNull(message = "La Descripción del Mosto en Tanque no puede ser nula")
    @Length(min = 5, max = 50, message = "La Descripción del Mosto en Tanque debe estar entre {min} y {max} caracteres")
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the codigo
     */
    @NotNull(message = "El Código del Mosto en Tanque no puede ser nulo")
    @Length(min = 3, max = 10, message = "El Código del Mosto en Tanque debe estar entre {min} y {max} caracteres")
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the fechaEgreso
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    /**
     * @param fechaEgreso the fechaEgreso to set
     */
    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    /**
     * @return the fechaIngreso
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "La Fecha de Ingreso del Mosto en Tanque no puede ser nulo")
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the volumenFinal
     */
    @Range(min = 0, message = "El Volume Final del Mosto en Tanque debe ser mayor a {min}")
    public Double getVolumenFinal() {
        return volumenFinal;
    }

    /**
     * @param volumenFinal the volumenFinal to set
     */
    public void setVolumenFinal(Double volumenFinal) {
        this.volumenFinal = volumenFinal;
    }

    /**
     * @return the volumenInicial
     */
    @Range(min = 0, message = "El Volume Inicial del Mosto en Tanque debe ser mayor a {min}")
    public Double getVolumenInicial() {
        return volumenInicial;
    }

    /**
     * @param volumenInicial the volumenInicial to set
     */
    public void setVolumenInicial(Double volumenInicial) {
        this.volumenInicial = volumenInicial;
    }

    /**
     *
     * @return the pesada
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mostoEnTanque", targetEntity = Pesada.class)
    public List<Pesada> getPesadas() {
        return pesadas;
    }

    /**
     *
     * @param pesada the pesada to set
     */
    public void setPesadas(List<Pesada> pesadas) {
        this.pesadas = pesadas;
    }

    /**
     *
     * @return the variedad
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true, targetEntity = Variedad.class)
    public Variedad getVariedad() {
        return variedad;
    }

    /**
     *
     * @param variedad the variedad to set
     */
    public void setVariedad(Variedad variedad) {
        this.variedad = variedad;
    }

    public void addPesada(Pesada pesada) {
        if (!this.pesadas.contains(pesada)) {
            this.pesadas.add(pesada);
            pesada.setMostoEnTanque(this);
        }
    }

    public void remove(Pesada pesada) {
        this.pesadas.remove(pesada);
        //subCuentaMayor.setCuentaMayor(null);
    }

    public MostoEnTanque copy() {
        MostoEnTanque copy = new MostoEnTanque();

        copy.setCodigo(this.getCodigo() + "-copy");
        copy.setDescripcion(this.getDescripcion() + "-copy");
        copy.setFechaEgreso(this.getFechaEgreso());
        copy.setFechaIngreso(this.getFechaIngreso());
        for (Pesada item : pesadas) {
            copy.addPesada(item.copy());
        }
        copy.setVariedad(this.getVariedad());
        copy.setVolumenFinal(this.getVolumenFinal());
        copy.setVolumenInicial(this.getVolumenInicial());

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/descripción ya existe";
    }

    public boolean validarFechasPesadas() {
        boolean valid = true;

        for (Pesada pesada : pesadas) {
            if (this.getFechaIngreso() != null && DateOperations.compareTime(pesada.getFecha(), this.getFechaIngreso(),
                    DateOperations.MAYOR, DateOperations.IGNORAR_DESDE_HORA)) {
                pesada.setFecha(this.getFechaIngreso());
                valid = false;
            }
        }

        return valid;
    }

    @Transient
    public String getFechaIngresoString(){
        return DateOperations.toDDMMYYYYString(fechaIngreso, "/");
    }

    @Transient
    public String getFechaEgresoString(){
        return DateOperations.toDDMMYYYYString(fechaEgreso, "/");
    }

    public Element exportar() {
        Element elementMosto = new Element("mostoEnTanque");

        if (codigo != null) elementMosto.setAttribute("codigo", codigo);
        if (descripcion != null) elementMosto.setAttribute("descripcion", descripcion);
        if (fechaEgreso != null) elementMosto.setAttribute("fechaEgreso", getFechaEgresoString());
        if (fechaIngreso != null) elementMosto.setAttribute("fechaIngreso", getFechaIngresoString());
        if (volumenFinal != null) elementMosto.setAttribute("volumenFinal", volumenFinal.toString());
        if (volumenInicial != null) elementMosto.setAttribute("volumenInicial", volumenInicial.toString());

        if ( !pesadas.isEmpty() ) {
            Element elementPesada = new Element("pesada");
            for (Pesada pesada : pesadas) {
                elementPesada.addContent(pesada.exportar());
            }
            elementMosto.addContent(elementPesada);
        }
        
        elementMosto.addContent(variedad.exportar());

        return elementMosto;
    }
}