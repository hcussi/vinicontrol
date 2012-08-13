/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.tanque;

import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.comunicacion.ConexionIP;
import edu.utn.frm.entities.comunicacion.ConexionSerie;
import edu.utn.frm.entities.regla.ReglaControl;
import edu.utn.frm.entities.tanque.demanda.EstadoPrediccionTendencia;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
@Table(name = "TANQUE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"codigo"}),
    @UniqueConstraint(columnNames = {"descripcion"})
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Tanque.findAll", query = "select e from Tanque e "),
    @NamedQuery(name = "Tanque.findById", query = "select e from Tanque e where id = :id")
})
public class Tanque extends EntityBase {

    private String codigo;
    private String descripcion;
    private Double capacidad;
    private GrupoTanque grupoTanque;
    private ReglaControl reglaControl;
    private ConexionIP conexionIP;
    private ConexionSerie conexionSerie;
    private Boolean usaConexionIP;
    
    private EstadoPrediccionTendencia estadoPrediccionTendencia;

    public Tanque() {
        usaConexionIP = true;
        capacidad = 500.0;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true, targetEntity = EstadoPrediccionTendencia.class)
    @JoinColumn(name="estadoPrediccionTendencia_id")
    public EstadoPrediccionTendencia getEstadoPrediccionTendencia() {
        return this.estadoPrediccionTendencia;
    }

    public void setEstadoPrediccionTendencia(EstadoPrediccionTendencia estadoPrediccionTendencia) {
        this.estadoPrediccionTendencia = estadoPrediccionTendencia;
    }

    /**
     * @return the codigo
     */
    @NotNull(message = "El Código del Tanque no puede ser nulo")
    @Length(min = 3, max = 10, message = "El Código del Tanque debe estar entre {min} y {max} caracteres")
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
     * @return the descripcion
     */
    @NotNull(message = "La Descripción del Tanque no puede ser nulo")
    @Length(min = 5, max = 50, message = "La Descripción del Tanque debe estar entre {min} y {max} caracteres")
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
     * @return the grupoTanque
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true, targetEntity = GrupoTanque.class)
    @JoinColumn(name = "grupoTanqueId")
    @PrimaryKeyJoinColumn
    public GrupoTanque getGrupoTanque() {
        return grupoTanque;
    }

    /**
     * @param grupoTanque the grupoTanque to set
     */
    public void setGrupoTanque(GrupoTanque grupoTanque) {
        this.grupoTanque = grupoTanque;
    }

    /**
     * @return the reglaControl
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true, targetEntity = ReglaControl.class)
    @JoinColumn(name = "reglaControlId")
    @PrimaryKeyJoinColumn
    public ReglaControl getReglaControl() {
        return reglaControl;
    }

    /**
     * @param reglaControl the reglaControl to set
     */
    public void setReglaControl(ReglaControl reglaControl) {
        this.reglaControl = reglaControl;
    }

    /**
     * @return the conexionIP
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true, targetEntity = ConexionIP.class)
    @JoinColumn(name = "conexionIPId")
    @PrimaryKeyJoinColumn
    public ConexionIP getConexionIP() {
        return conexionIP;
    }

    /**
     * @param conexionIP the conexionIP to set
     */
    public void setConexionIP(ConexionIP conexionIP) {
        this.conexionIP = conexionIP;
    }

    /**
     * @return the conexionSerie
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true, targetEntity = ConexionSerie.class)
    @JoinColumn(name = "conexionSerieId")
    @PrimaryKeyJoinColumn
    public ConexionSerie getConexionSerie() {
        return conexionSerie;
    }

    /**
     * @param conexionSerie the conexionSerie to set
     */
    public void setConexionSerie(ConexionSerie conexionSerie) {
        this.conexionSerie = conexionSerie;
    }

    /**
     *
     * @return the capacidad
     */
    @NotNull(message = "La Capacidad del Tanque no puede ser nulo")
    @Range(min = 0, message = "La Capacidad del Tanque debe ser mayor a {min}")
    public Double getCapacidad() {
        return capacidad;
    }

    /**
     *
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(Double capacidad) {
        this.capacidad = capacidad;
    }

    public Boolean getUsaConexionIP() {
        return usaConexionIP;
    }

    public void setUsaConexionIP(Boolean usaConexionIP) {
        this.usaConexionIP = usaConexionIP;
    }

    

    public Tanque copy() {
        Tanque copy = new Tanque();

        copy.setCapacidad(this.getCapacidad());
        copy.setCodigo(this.getCodigo() + "-copy");
        copy.setConexionIP(this.getConexionIP());
        copy.setConexionSerie(this.getConexionSerie());
        copy.setDescripcion(this.getDescripcion() + "-copy");

        copy.setGrupoTanque(this.getGrupoTanque());
        copy.setReglaControl(this.getReglaControl());
        copy.setUsaConexionIP(this.getUsaConexionIP());

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/descripción ya existe";
    }

    @Override
    public String toString() {

        return "Código: " + codigo + " Descripción: " + descripcion + " Capacidad: " + capacidad;
    }

    public Element exportar() {
        Element elementTanque = new Element("tanque");

        if (codigo != null) elementTanque.setAttribute("codigo", codigo);
        if (descripcion != null) elementTanque.setAttribute("descripcion", descripcion);
        if (capacidad != null) elementTanque.setAttribute("capacidad", capacidad.toString());
        
        if (reglaControl != null) {
            elementTanque.addContent(reglaControl.getReglaControlRemontaje().exportar());
        }else if( grupoTanque.getReglaControl()!=null ){
            elementTanque.addContent(grupoTanque.getReglaControl().getReglaControlRemontaje().exportar());
        }

        return elementTanque;
    }
}
