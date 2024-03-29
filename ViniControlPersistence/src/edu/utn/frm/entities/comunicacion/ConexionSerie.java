/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.comunicacion;
import edu.utn.frm.entities.base.EntityBase;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import org.hibernate.validator.Range;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name="CONEXION_SERIE", uniqueConstraints = {
                @UniqueConstraint(columnNames = { "codigo" }),
                @UniqueConstraint(columnNames = { "descripcion" }),
		@UniqueConstraint(columnNames = { "nombrePuerto" })
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="ConexionSerie.findAll", query="select e from ConexionSerie e "),
	@NamedQuery(name="ConexionSerie.findById", query="select e from ConexionSerie e where id = :id")
})
public class ConexionSerie extends ConfiguracionConexion{

    private String nombrePuerto;
    private Integer ratioBaudio;
    private Integer reintentos;
    private Integer timeOut;
    private BitParada bitParada;
    private Paridad paridad;

    public ConexionSerie(){
        reintentos = 3;
        timeOut = 60;
        nombrePuerto = "COM2";
        ratioBaudio = 9600;
    }

    /**
     * @return the nombrePuerto
     */
    @NotNull(message = "El Nombre del Puerto de la Conexión Serie no puede ser nulo")
    @Length(min = 3, max = 20, message = "El Nombre del Puerto de la Conexión Serie debe estar entre {min} y {max} caracteres")
    public String getNombrePuerto() {
        return nombrePuerto;
    }

    /**
     * @param nombrePuerto the nombrePuerto to set
     */
    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
    }

    /**
     * @return the ratioBaudio
     */
    @NotNull(message = "El ratio baudio de la Conexión Serie no puede ser nulo")
    @Range(min = 0, message = "El ratio baudio de la Conexión Serie debe ser mayor a {min}")
    public Integer getRatioBaudio(){
        return ratioBaudio;
    }

    /**
     * @param ratioBaudio the ratioBaudio to set
     */
    public void setRatioBaudio(Integer ratioBaudio) {
        this.ratioBaudio = ratioBaudio;
    }

    /**
     * @return the reintentos
     */
    @NotNull(message = "Los reintentos de la Conexión Serie no puede ser nulo")
    @Range(min = 0,max=10, message = "Los reintentos de la Conexión Serie debe estar entre {min} y {max}")
    public Integer getReintentos() {
        return reintentos;
    }

    /**
     * @param reintentos the reintentos to set
     */
    public void setReintentos(Integer reintentos) {
        this.reintentos = reintentos;
    }

    /**
     * @return the timeOut
     */
    @NotNull(message = "El timeOut de la Conexión Serie no puede ser nulo")
    @Range(min = 0,max=60, message = "El timeOut de la Conexión Serie debe estar entre {min} y {max}")
    public Integer getTimeOut() {
        return timeOut;
    }

    /**
     *
     * @param timeOut the timeOut to set
     */
    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * @return the bitParada
     */
    @NotNull(message = "El bit de parada no puede ser nulo")
    @Enumerated(EnumType.STRING)
    public BitParada getBitParada() {
        return bitParada;
    }

    /**
     *
     * @param bitParada the bitParada to set
     */
    public void setBitParada(BitParada bitParada) {
        this.bitParada = bitParada;
    }

    /**
     * @return the paridad
     */
    @NotNull(message = "La paridad no puede ser nula")
    @Enumerated(EnumType.STRING)
    public Paridad getParidad() {
        return paridad;
    }

    /**
     *
     * @param paridad the paridad to set
     */
    public void setParidad(Paridad paridad) {
        this.paridad = paridad;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/descripción/nombre de puerto ya existe";
    }

    public EntityBase copy() {
        ConexionSerie copy = new ConexionSerie();

        copy.setActiva(this.getActiva());
        copy.setCodigo(this.getCodigo()+"-copy");
        copy.setDescripcion(this.getDescripcion()+"-copy");
        copy.setBitParada(this.getBitParada());
        copy.setNombrePuerto(this.getNombrePuerto());
        copy.setParidad(this.getParidad());
        copy.setRatioBaudio(this.getRatioBaudio());
        copy.setReintentos(this.getReintentos());
        copy.setTimeOut(this.getTimeOut());

        return copy;
    }

    @Override
    public String toString() {
        return "Puerto: "+ nombrePuerto +" Ratio Baudio: " + ratioBaudio + " Reintentos: " + reintentos + " TimeOut: " + timeOut;
    }

}