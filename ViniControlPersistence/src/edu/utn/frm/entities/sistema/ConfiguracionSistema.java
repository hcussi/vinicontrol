/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.sistema;

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
import org.hibernate.validator.Range;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name = "CONFIGURACION_SISTEMA",
uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "ConfiguracionSistema.findAll", query = "select e from ConfiguracionSistema e "),
    @NamedQuery(name = "ConfiguracionSistema.findById", query = "select e from ConfiguracionSistema e where id = :id")
})
public class ConfiguracionSistema extends EntityBase {

    private String cuit;
    private String direccion;
    private String empresa;
    private Boolean funcionamientoAutomatico;
    private String razonSocial;
    private Integer idComandoBajarTemperatura;
    private Integer idComandoRemontaje;
    private Integer idComandoFinRemontaje;
    private Integer reitentosReconexion;
    private Integer segundosReconexion;
    private Integer mostrarAlarma;
    private Integer minutosPredecir;
    private Integer verificarProcesosDetener;
    private Integer verificarRemontaje;

    public ConfiguracionSistema() {
        this.funcionamientoAutomatico = true;
        this.idComandoBajarTemperatura = 1;
        this.idComandoRemontaje = 2;
        this.idComandoFinRemontaje = 3;
        this.reitentosReconexion = 10;
        this.segundosReconexion = 5;
        this.mostrarAlarma = 1200;
        this.minutosPredecir = 60;
        this.verificarProcesosDetener = 60;
        this.verificarRemontaje = 30;
    }

    public ConfiguracionSistema(String cuit, String direccion, String empresa, String razonSocial) {
        this();
        this.cuit = cuit;
        this.direccion = direccion;
        this.empresa = empresa;
        this.razonSocial = razonSocial;
    }

    /**
     * @return the cuit
     */
    @Length(min = 13, max = 13, message = "El CUIT de la Configuración del Sistema debe estar entre {min} y {max} caracteres")
    public String getCuit() {
        return cuit;
    }

    /**
     * @param cuit the cuit to set
     */
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    /**
     * @return the direccion
     */
    @Length(min = 0, max = 50, message = "La Dirección de la Empresa debe estar entre {min} y {max} caracteres")
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *
     * @return the empresa
     */
    @Length(min=0,max=50,message="El Nombre de la Empresa de la Configuración del Sistema debe estar entre {min} y {max} caracteres")
    public String getEmpresa() {
        return empresa;
    }

    /**
     *
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     *
     * @return the razonSocial
     */
    @Length(min=3,max=50,message="La Razón Social de la Configuración del Sistema debe estar entre {min} y {max} caracteres")
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     *
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     *
     * @return the funcionamientoAutomatico
     */
    public Boolean getFuncionamientoAutomatico() {
        return funcionamientoAutomatico;
    }

    /**
     *
     * @param funcionamientoAutomatico the funcionamientoAutomatico to set
     */
    public void setFuncionamientoAutomatico(Boolean funcionamientoAutomatico) {
        this.funcionamientoAutomatico = funcionamientoAutomatico;
    }

    public Integer getIdComandoBajarTemperatura() {
        return idComandoBajarTemperatura;
    }

    public void setIdComandoBajarTemperatura(Integer idComandoBajarTemperatura) {
        this.idComandoBajarTemperatura = idComandoBajarTemperatura;
    }

    public Integer getIdComandoFinRemontaje() {
        return idComandoFinRemontaje;
    }

    public void setIdComandoFinRemontaje(Integer idComandoFinRemontaje) {
        this.idComandoFinRemontaje = idComandoFinRemontaje;
    }

    public Integer getIdComandoRemontaje() {
        return idComandoRemontaje;
    }

    public void setIdComandoRemontaje(Integer idComandoRemontaje) {
        this.idComandoRemontaje = idComandoRemontaje;
    }

    @Range(min=0,max=100, message="La cantidad de reintentos de reconexión debe estar entre {min} y {max}")
    public Integer getReitentosReconexion() {
        return reitentosReconexion;
    }

    public void setReitentosReconexion(Integer reitentosReconexion) {
        this.reitentosReconexion = reitentosReconexion;
    }

    @Range(min=5,max=100, message="La cantidad de segundos de reconexión debe estar entre {min} y {max}")
    public Integer getSegundosReconexion() {
        return segundosReconexion;
    }

    public void setSegundosReconexion(Integer segundosReconexion) {
        this.segundosReconexion = segundosReconexion;
    }

    @Range(min=1200,message="El valor que indica cuanto tiempo se va a mostrar la alarma deber ser mayor a {min} milisegundos")
    public Integer getMostrarAlarma() {
        return mostrarAlarma;
    }

    public void setMostrarAlarma(Integer mostrarAlarma) {
        this.mostrarAlarma = mostrarAlarma;
    }

    @Range(min=10,max=90,message="El valor de minutos a predecir debe estar entre  {min} y {max}")
    public Integer getMinutosPredecir() {
        return minutosPredecir;
    }

    public void setMinutosPredecir(Integer minutosPredecir) {
        this.minutosPredecir = minutosPredecir;
    }

    @Range(min=60,message="El valor de cada cuanto se verifica los procesos a detener debe ser mayor a {min} segundos")
    public Integer getVerificarProcesosDetener() {
        return verificarProcesosDetener;
    }

    public void setVerificarProcesosDetener(Integer verificarProcesosDetener) {
        this.verificarProcesosDetener = verificarProcesosDetener;
    }

    @Range(min=30,max=600,message="El valor de cada cuanto se verifica los procesos a remontar debe estar entre {min} y {max} segundos")
    public Integer getVerificarRemontaje() {
        return verificarRemontaje;
    }

    public void setVerificarRemontaje(Integer verificarRemontaje) {
        this.verificarRemontaje = verificarRemontaje;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el id ya existe";
    }

    public ConfiguracionSistema copy() {
        return null;
    }
    
}