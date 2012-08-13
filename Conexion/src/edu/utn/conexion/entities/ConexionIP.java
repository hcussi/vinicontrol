/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.conexion.entities;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Proyecto
 */
public class ConexionIP extends ConfiguracionConexion{

    private String direccionIPv4;
    private Integer puerto;
    private TipoConexion tipoConexion;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;

    public ConexionIP(){
        puerto = 3456;
    }

    /**
     * @return the direccionIPv4
     */
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
     * @return the tipoConexion
     */
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

    /***
     *
     * @return the inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     *
     * @param inputStream the inputStream to set
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     *
     * @return the outputStream
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     *
     * @param outputStream the outputStream to set
     */
    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     *
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     *
     * @param socket the socket to set
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public String getEstado(){
        if (getActiva())return "activa";
        else return "desactiva";
    }

}