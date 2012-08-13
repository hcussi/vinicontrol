/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.comunicacion;
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
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Range;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name="CONEXION_IP", uniqueConstraints = {
            @UniqueConstraint(columnNames = { "codigo" }),
                @UniqueConstraint(columnNames = { "descripcion" }),
		@UniqueConstraint(columnNames = { "direccionIPv4","puerto" }),
                @UniqueConstraint(columnNames = { "direccionIPv4","puertoRecepcion" })
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="ConexionIP.findAll", query="select e from ConexionIP e "),
	@NamedQuery(name="ConexionIP.findById", query="select e from ConexionIP e where id = :id")
})
public class ConexionIP extends ConfiguracionConexion{

    private String direccionIPv4;
    private Integer puerto;
    private Integer puertoRecepcion;
    private TipoConexion tipoConexion;

    public ConexionIP(){
        puerto = 3456;
        puertoRecepcion = 3457;
        tipoConexion = TipoConexion.UDP;
    }

    /**
     * @return the direccionIPv4
     */
    @NotNull(message = "La Dirección de la Conexión IP no puede ser nulo")
    @Length(min = 7, max = 15, message = "La Dirección de la Conexión IP debe estar entre {min} y {max} caracteres")
    public String getDireccionIPv4() {
        return direccionIPv4;
    }

    /**
     * @param direccionIPv4 the direccionIPv4 to set
     */
    public void setDireccionIPv4(String direccionIPv4) {
        this.direccionIPv4 = direccionIPv4;
    }

    /**
     * @return the puerto
     */
    @NotNull(message = "El número de puerto de la Conexión IP no puede ser nulo")
    @Range(min = 1,max=65535, message = "El número de puerto de la Conexión IP debe estar entre {min} y {max}")
    public Integer getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    /**
     * @return the puertoRecepcion
     */
    @NotNull(message = "El número de puerto de recepción de la Conexión IP no puede ser nulo")
    @Range(min = 1,max=65535, message = "El número de puerto de recepción de la Conexión IP debe estar entre {min} y {max}")
    public Integer getPuertoRecepcion() {
        return puertoRecepcion;
    }

    /**
     * @param puertoRecepcion the puertoRecepcion to set
     */
    public void setPuertoRecepcion(Integer puertoRecepcion) {
        this.puertoRecepcion = puertoRecepcion;
    }

    /**
     * @return the tipoConexion
     */
    @Enumerated(EnumType.STRING)
    @NotNull(message="Debe seleccionar un Tipo de Conexión para la Conexión IP")
    public TipoConexion getTipoConexion() {
        return tipoConexion;
    }

    /**
     *
     * @param tipoConexion the tipoConexion to set
     */
    public void setTipoConexion(TipoConexion tipoConexion) {
        this.tipoConexion = tipoConexion;
    }

    @Transient
    public String getEstado(){
        if (getActiva())return "activa";
        else return "desactivada";
    }

    public ConexionIP copy() {
        ConexionIP copy = new ConexionIP();

        copy.setActiva(this.getActiva());
        copy.setCodigo(this.getCodigo()+"-copy");
        copy.setDescripcion(this.getDescripcion()+"-copy");
        copy.setDireccionIPv4(this.getDireccionIPv4());
        copy.setPuerto(this.getPuerto() + 1);
        copy.setPuertoRecepcion(this.getPuertoRecepcion() + 1);
        copy.setTipoConexion(this.getTipoConexion());

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/descripción/dirección ip:puerto ya existe";
    }

    @Transient
    public byte[] getDireccionIPv4Bytes(){

        String[] partes = obtenerPartes('.');
        byte[] direccion = new byte[4];

        direccion[0] = new Integer(partes[0]).byteValue();
        direccion[1] = new Integer(partes[1]).byteValue();
        direccion[2] = new Integer(partes[2]).byteValue();
        direccion[3] = new Integer(partes[3]).byteValue();

        return direccion;
    }

    public String[] obtenerPartes(char split){

        String partes[] = new String[4];

        for (int i = 0,p = 0; i < direccionIPv4.length(); i++) {
            char c = direccionIPv4.charAt(i);
            if( split == c ){
                p++;
            }else{
                if( partes[p] == null ) partes[p] = new String();
                partes[p] += String.valueOf(c);
            }
        }

        return partes;
    }

    @Override
    public String toString() {
        return "IP: "+ direccionIPv4 +" Puerto: " + puerto + " Puerto Recepción: " + puertoRecepcion + " Tipo: " + tipoConexion.getDescripcion();
    }

}