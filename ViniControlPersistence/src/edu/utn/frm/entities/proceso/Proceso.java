/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.proceso;

import edu.utn.frm.entities.alarma.AlarmaNivelAzucar;
import edu.utn.frm.entities.alarma.AlarmaNivelCapacidad;
import edu.utn.frm.entities.alarma.AlarmaRemontaje;
import edu.utn.frm.entities.alarma.AlarmaTemperatura;
import edu.utn.frm.entities.comunicacion.ConfiguracionConexion;
import edu.utn.frm.entities.producto.*;
import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.comunicacion.ConexionIP;
import edu.utn.frm.entities.comunicacion.ConexionSerie;
import edu.utn.frm.entities.regla.ReglaControl;
import edu.utn.frm.entities.regla.ReglaControlNivel;
import edu.utn.frm.entities.regla.ReglaControlTemperatura;
import edu.utn.frm.entities.tanque.EstadoTanque;
import edu.utn.frm.entities.tanque.Tanque;
import edu.utn.frm.entities.tanque.demanda.PrediccionTendenciaTemperatura;
import edu.utn.frm.entities.utils.DateOperations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.Length;
import org.jdom.Element;
 /**
 * @author Proyecto
 */
@Entity
@Table(name = "PROCESO")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Proceso.findAll", query = "select e from Proceso e "),
    @NamedQuery(name = "Proceso.findById", query = "select e from Proceso e where id = :id")
})
public class Proceso extends EntityBase{

    private String descripcion;
    private Date fechaFin;
    private Date fechaInicio;
    private Date horaFin;
    private Date horaInicio;
    private Tanque tanque;
    private MostoEnTanque mostoEnTanque;
    private List<EstadoTanque> estadosTanque;
    private EstadoTanque estadoTanque;
    private EstadoProceso estadoProceso;

    private List<AlarmaNivelAzucar> alarmasNivelAzucar;
    private List<AlarmaNivelCapacidad> alarmasNivelCapacidad;
    private List<AlarmaRemontaje> alarmasRemontaje;
    private List<AlarmaTemperatura> alarmasTemperatura;

    private List<PrediccionTendenciaTemperatura> prediccionesTendencia;

    public Proceso() {
        estadosTanque = new ArrayList<EstadoTanque>();
        alarmasNivelAzucar = new ArrayList<AlarmaNivelAzucar>();
        alarmasNivelCapacidad = new ArrayList<AlarmaNivelCapacidad>();
        alarmasRemontaje = new ArrayList<AlarmaRemontaje>();
        alarmasTemperatura = new ArrayList<AlarmaTemperatura>();
        prediccionesTendencia = new ArrayList<PrediccionTendenciaTemperatura>();
        this.estadoProceso = EstadoProceso.EN_ESPERA;
        fechaInicio = new Date();
        fechaFin = new Date();
        horaInicio = new Date();
        horaFin = new Date();
    }

    /**
     * @return the descripcion
     */
    
    @Length(min = 0, max = 50, message = "La Descripción del Proceso debe estar entre {min} y {max} caracteres")
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
     * @return the fechaFin
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the horaFin
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * @return the horaInicio
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the fechaInicio
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the estadosTanque
     */
    @OneToMany(mappedBy="proceso", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<EstadoTanque> getEstadosTanque() {
        return estadosTanque;
    }

    public void setEstadosTanque(List<EstadoTanque> estadosTanque) {
        this.estadosTanque = estadosTanque;
    }

    @Enumerated(EnumType.STRING)
    public EstadoProceso getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(EstadoProceso estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    /**
     *
     * @return the alarmasNivelAzucar
     */
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<AlarmaNivelAzucar> getAlarmasNivelAzucar() {
        return alarmasNivelAzucar;
    }

    /**
     *
     * @param alarmasNivelAzucar the alarmasNivelAzucar to set
     */
    public void setAlarmasNivelAzucar(List<AlarmaNivelAzucar> alarmasNivelAzucar) {
        this.alarmasNivelAzucar = alarmasNivelAzucar;
    }

    /**
     *
     * @return the alarmasNivelCapacidad
     */
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<AlarmaNivelCapacidad> getAlarmasNivelCapacidad() {
        return alarmasNivelCapacidad;
    }

    /**
     *
     * @param alarmasNivelCapacidad the alarmasNivelCapacidad to set
     */
    public void setAlarmasNivelCapacidad(List<AlarmaNivelCapacidad> alarmasNivelCapacidad) {
        this.alarmasNivelCapacidad = alarmasNivelCapacidad;
    }

    /**
     *
     * @return the alarmasRemontaje
     */
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<AlarmaRemontaje> getAlarmasRemontaje() {
        return alarmasRemontaje;
    }

    /**
     *
     * @param alarmasRemontaje the alarmasRemontaje to set
     */
    public void setAlarmasRemontaje(List<AlarmaRemontaje> alarmasRemontaje) {
        this.alarmasRemontaje = alarmasRemontaje;
    }

    /**
     *
     * @return the alarmasTemperatura
     */
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<AlarmaTemperatura> getAlarmasTemperatura() {
        return alarmasTemperatura;
    }

    /**
     *
     * @param alarmasTemperatura the alarmasTemperatura to set
     */
    public void setAlarmasTemperatura(List<AlarmaTemperatura> alarmasTemperatura) {
        this.alarmasTemperatura = alarmasTemperatura;
    }

    /**
     *
     * @return the mostoEnTanque
     */
    @ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY,optional=true)
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
     *
     * @return the tanque
     */
    @ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY,optional=true)
    public Tanque getTanque() {
        return tanque;
    }

    /**
     *
     * @param tanque the tanque to set
     */
    public void setTanque(Tanque tanque) {
        this.tanque = tanque;
    }

    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=PrediccionTendenciaTemperatura.class)
    public List<PrediccionTendenciaTemperatura> getPrediccionesTendencia() {
        return prediccionesTendencia;
    }

    public void setPrediccionesTendencia(List<PrediccionTendenciaTemperatura> prediccionesTendencia) {
        this.prediccionesTendencia = prediccionesTendencia;
    }

    public void addPrediccionTendencia(PrediccionTendenciaTemperatura prediccionTendenciaTemperatura) {
        if (!this.prediccionesTendencia.contains(prediccionTendenciaTemperatura)) {
            this.prediccionesTendencia.add(prediccionTendenciaTemperatura);
            prediccionTendenciaTemperatura.setProceso(this);
        }
    }

    /**
     *
     * @return the estadoTanque
     */
    @Transient
    public EstadoTanque getEstadoTanque() {
        if( estadoTanque == null ){
            int size = this.estadosTanque.size();
            if( size > 0 ){
                EstadoTanque estadoTanqueUltimo = this.estadosTanque.get( size - 1 );
                estadoTanque = estadoTanqueUltimo;
            }
        }
        return estadoTanque;
    }

    public PrediccionTendenciaTemperatura getPrediccionesTendencia(int periodo) {

        for (PrediccionTendenciaTemperatura prediccionTendencia : prediccionesTendencia) {
            if (prediccionTendencia.getPeriodo() == periodo) {
                return prediccionTendencia;
            }
        }
        return null;
    }

    /**
     *
     * @param estadoTanque the estadoTanque to set
     */
    public void setEstadoTanque(EstadoTanque estadoTanque) {
        this.estadoTanque = estadoTanque;
    }

    public boolean addEstadoTanque(EstadoTanque estadoTanque){
        //Solo agrego el estado si es diferente al ultimo
        int size = this.estadosTanque.size();
        if( size > 0 ){
            EstadoTanque estadoTanqueUltimo = this.estadosTanque.get( size - 1 );
            if( estadoTanqueUltimo.equals(estadoTanque) ) {
                return false;
            }
        }
        this.estadosTanque.add(estadoTanque);
        estadoTanque.setProceso(this);
        return true;
    }

    public void addAlarmaNivelAzucar(AlarmaNivelAzucar alarmaNivelAzucar){
        if( !this.alarmasNivelAzucar.contains(alarmaNivelAzucar) ){
            this.alarmasNivelAzucar.add(alarmaNivelAzucar);
            alarmaNivelAzucar.setProceso(this);
        }
    }

    public void addAlarmaNivelCapacidad(AlarmaNivelCapacidad alarmaNivelCapacidad){
        if( !this.alarmasNivelCapacidad.contains(alarmaNivelCapacidad) ){
            this.alarmasNivelCapacidad.add(alarmaNivelCapacidad);
            alarmaNivelCapacidad.setProceso(this);
        }
    }

    public void addAlarmaRemontaje(AlarmaRemontaje alarmaRemontaje){
        if( !this.alarmasRemontaje.contains(alarmaRemontaje) ){
            this.alarmasRemontaje.add(alarmaRemontaje);
            alarmaRemontaje.setProceso(this);
        }
    }

    public void addAlarmaTemperatura(AlarmaTemperatura alarmaTemperatura){
        if( !this.alarmasTemperatura.contains(alarmaTemperatura) ){
            this.alarmasTemperatura.add(alarmaTemperatura);
            alarmaTemperatura.setProceso(this);
        }
    }

    public void remove(AlarmaNivelAzucar alarmaNivelAzucar){
        this.alarmasNivelAzucar.remove(alarmaNivelAzucar);
    }

    public void remove(AlarmaNivelCapacidad alarmaNivelCapacidad){
        this.alarmasNivelCapacidad.remove(alarmaNivelCapacidad);
    }

    public void remove(AlarmaRemontaje alarmaRemontaje){
        this.alarmasRemontaje.remove(alarmaRemontaje);
    }

    public void remove(AlarmaTemperatura alarmaTemperatura){
        this.alarmasTemperatura.remove(alarmaTemperatura);
    }

    @Override
    @PreUpdate
    public void PreUpdate(){
        super.PreUpdate();
        if(this.fechaInicio!=null) this.fechaInicio = DateOperations.cleanHHmmss(fechaInicio);
        if(this.fechaFin!=null) this.fechaFin = DateOperations.cleanHHmmss(fechaFin);
    }

    @Override
    @PrePersist
    public void PrePersist(){
        super.PrePersist();
        if(this.fechaInicio!=null) this.fechaInicio = DateOperations.cleanHHmmss(fechaInicio);
        if(this.fechaFin!=null) this.fechaFin = DateOperations.cleanHHmmss(fechaFin);
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, la fecha de inicio ya existe";
    }

    public Proceso copy() {
        throw new UnsupportedOperationException("No soporta copiar el Proceso");
    }

    @Transient
    public String getFechaInicioString(){
        return DateOperations.toDDMMYYYYString(fechaInicio, "/");
    }

    @Transient
    public String getFechaFinString(){
        return DateOperations.toDDMMYYYYString(fechaFin, "/");
    }

    @Transient
    public String getHoraFinString(){
        return DateOperations.toHHmmssString(horaFin, ":");
    }

    @Transient
    public String getHoraInicioString(){
        return DateOperations.toHHmmssString(horaInicio, ":");
    }

    @Transient
    public ConfiguracionConexion getConfiguracionConexion() {
        ConexionIP conexionIP = this.tanque.getConexionIP();
        ConexionSerie conexionSerie = this.tanque.getConexionSerie();
        if( conexionIP != null && conexionIP.getActiva() ){
            return conexionIP;
        }else if( conexionSerie != null && conexionSerie.getActiva() ){
            return conexionSerie;
        }else{
            return null;
        }
    }

    @Override
    public String toString(){

        return "Descripción: " + descripcion + " Estado Proceso: " + estadoProceso.getDescripcion();
    }

    public Element exportar(){
        Element elementProceso = new Element("proceso");

        if(descripcion != null)elementProceso.setAttribute("descripcion", descripcion);
        if(fechaInicio != null)elementProceso.setAttribute("fechaInicio", getFechaInicioString());
        if(fechaFin != null)elementProceso.setAttribute("fechaFin", getFechaFinString());
        if(horaInicio != null)elementProceso.setAttribute("horaInicio",getHoraInicioString());
        if(horaFin != null)elementProceso.setAttribute("horaFin",getHoraFinString());

        if(tanque != null)elementProceso.addContent(tanque.exportar());
        if(mostoEnTanque != null)elementProceso.addContent(mostoEnTanque.exportar());

        if(alarmasNivelAzucar.size()!=0){
            Element elementAlarmaAzucar = new Element("alarmaAzucar");
            for(AlarmaNivelAzucar alarma : alarmasNivelAzucar){
                elementAlarmaAzucar.addContent(alarma.exportar());
            }
            elementProceso.addContent(elementAlarmaAzucar);
        }

        if(alarmasNivelCapacidad.size()!=0){
            Element elementAlarmaCapacidad = new Element("alarmaCapacidad");
            for(AlarmaNivelCapacidad alarma : alarmasNivelCapacidad){
                elementAlarmaCapacidad.addContent(alarma.exportar());
            }
            elementProceso.addContent(elementAlarmaCapacidad);
        }

        if(alarmasRemontaje.size()!=0){
            Element elementAlarmaRemontaje = new Element("alarmaRemontaje");
            for(AlarmaRemontaje alarma : alarmasRemontaje){
                elementAlarmaRemontaje.addContent(alarma.exportar());
            }
            elementProceso.addContent(elementAlarmaRemontaje);
        }

        if(alarmasTemperatura.size()!=0){
        Element elementAlarmaTemperatura = new Element("alarmaTemperatura");
            for(AlarmaTemperatura alarma : alarmasTemperatura){
                elementAlarmaTemperatura.addContent(alarma.exportar());
            }
            elementProceso.addContent(elementAlarmaTemperatura);
        }

        return elementProceso;
    }

    public boolean verificarAlarmaNivelAzucar() {
        ReglaControl reglaControl = getReglaControl();
        if( reglaControl == null ) return false;
        if( reglaControl.getReglaControlNivel() == null ) return false;
        ReglaControlNivel rcn = reglaControl.getReglaControlNivel();
        double nivelAzucar = this.getEstadoTanque().getNivelAzucar();
        return nivelAzucar <= rcn.getNivelMinimoAzucar();
    }

    public boolean verificarAlarmaNivel() {
        ReglaControl reglaControl = getReglaControl();
        if( reglaControl == null ) return false;
        if( reglaControl.getReglaControlNivel() == null ) return false;
        ReglaControlNivel rcn = reglaControl.getReglaControlNivel();
        double nivelCapacidad = this.getEstadoTanque().getNivelContenido();
        return nivelCapacidad >= rcn.getNivelMaximoCapacidad();
    }

    public boolean verificarAlarmaTemperaturaMaxima() {
        ReglaControl reglaControl = getReglaControl();
        if( reglaControl == null ) return false;
        if( reglaControl.getReglaControlTemperatura() == null ) return false;
        ReglaControlTemperatura rcn = reglaControl.getReglaControlTemperatura();
        double temperatura = this.getEstadoTanque().getTemperatura();
        return temperatura > rcn.getTemperaturaMaxima();
    }

    public boolean verificarAlarmaTemperaturaMinima() {
        ReglaControl reglaControl = getReglaControl();
        if( reglaControl == null ) return false;
        if( reglaControl.getReglaControlTemperatura() == null ) return false;
        ReglaControlTemperatura rcn = reglaControl.getReglaControlTemperatura();
        double temperatura = this.getEstadoTanque().getTemperatura();
        return temperatura < rcn.getTemperaturaMinima();
    }

    public boolean verificarAlarmaTemperaturaTolerancia() {
        ReglaControl reglaControl = getReglaControl();
        if( reglaControl == null ) return false;
        if( reglaControl.getReglaControlTemperatura() == null ) return false;
        ReglaControlTemperatura rcn = reglaControl.getReglaControlTemperatura();
        double temperatura = this.getEstadoTanque().getTemperatura();
        return ( (temperatura + rcn.getTemperaturaTolerancia() ) > rcn.getTemperaturaMaxima() );
    }

    /**
     *
     * @param temperaturaPredecida
     * @return true si falla la validacion de la regla
     */
    public boolean verificarAlarmaTemperaturaPrediccion(double temperaturaPredecida) {
        ReglaControl reglaControl = getReglaControl();
        if( reglaControl == null ) return false;
        if( reglaControl.getReglaControlTemperatura() == null ) return false;
        ReglaControlTemperatura rcn = reglaControl.getReglaControlTemperatura();
        return ( temperaturaPredecida >= rcn.getTemperaturaMaxima() );
    }

    @Transient
    public ReglaControl getReglaControl(){
        ReglaControl reglaControl = getTanque().getReglaControl();
        if (reglaControl == null) {
            return getTanque().getGrupoTanque().getReglaControl();
        }
        return reglaControl;
    }
    
}