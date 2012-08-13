/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.producto;

import edu.utn.frm.entities.base.EntityBase;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name="CAMION", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "patente" })
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="Camion.findAll", query="select e from Camion e "),
	@NamedQuery(name="Camion.findById", query="select e from Camion e where id = :id")
})
public class Camion extends EntityBase{

    private Double capacidadKilos;
    private String descripcion;
    private String marca;
    private String patente;
    private Integer modelo;
    
    public Camion(){
        capacidadKilos = 0.0;
        modelo = 0;
    }

    /**
     * @return the capacidadKilos
     */
    @NotNull(message = "La Capacidad del Camión no puede ser nulo")
    @Range(min=0,message="La Capacidad del Camión debe ser mayor a {min}")
    public Double getCapacidadKilos() {
        return capacidadKilos;
    }

    /**
     * @param capacidadKilos the capacidadKilos to set
     */
    public void setCapacidadKilos(Double capacidadKilos) {
        this.capacidadKilos = capacidadKilos;
    }

    /**
     * @return the descripcion
     */
    @NotNull(message = "La Descripción del Camión no puede ser nulo")
    @Length(min = 5, max = 50, message = "La Descripción del Camión debe estar entre {min} y {max} caracteres")
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
     * @return the marca
     */
    @Length(min = 0, max = 20, message = "La Marca del Camión debe estar entre {min} y {max} caracteres")
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the patente
     */
    @NotNull(message = "La Patente del Camión no puede ser nulo")
    @Length(min = 5, max = 50, message = "La Patente del Camión debe estar entre {min} y {max} caracteres")
    public String getPatente() {
        return patente;
    }

    /**
     * @param patente the patente to set
     */
    public void setPatente(String patente) {
        this.patente = patente;
    }

    /**
     * @return the modelo
     */
    @Range(min = 1900, max = 2100, message = "El Modelo del Camión debe estar entre {min} y {max}")
    public Integer getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public Camion copy() {
        Camion copy = new Camion();

        copy.setCapacidadKilos(this.getCapacidadKilos());
        copy.setDescripcion(this.getDescripcion());
        copy.setMarca(this.getMarca());
        copy.setModelo(this.getModelo());
        copy.setPatente(this.getPatente()+"-copy");

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, la patente ya existe";
    }

    @Transient
    public String getDatosInforme(){
        String datos = getId() + " ;" + getDescripcion() + " ;" +
                      getMarca() + " ;" +getModelo();
        return datos;
    }

    public Element exportar(){
        Element elementCamion = new Element("camion");

        if(capacidadKilos != null)elementCamion.setAttribute("capacidadKilos", capacidadKilos.toString());
        if(descripcion != null)elementCamion.setAttribute("descripcion", descripcion);
        if(marca != null)elementCamion.setAttribute("marca", marca);
        if(patente != null)elementCamion.setAttribute("patente", patente);
        if(modelo != null)elementCamion.setAttribute("modelo", modelo.toString());

        return elementCamion;
    }
}