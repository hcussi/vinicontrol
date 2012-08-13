/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.conexion.estrategia;

import edu.utn.conexion.PaqueteComunicacion;
import edu.utn.conexion.entities.ConexionIP;
import edu.utn.conexion.entities.ConfiguracionConexion;
import edu.utn.conexion.eventos.ConexionMessageEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

/**
 *
 * @author Proyecto
 */
public class EstrategiaComunicacionUDP extends EstrategiaComunicacion{

    private ConexionIP conexionIP;
    private DatagramSocket datagramSocket = null;
    private boolean DEBUG = false;

    public EstrategiaComunicacionUDP(){
        super();
    }

    public void setDebugMode(){
        DEBUG = true;
    }

    public void deactivateDebugMode(){
        DEBUG = false;
    }

    public void setConfiguracionConexion(ConfiguracionConexion configuracionConexion) {
        if( configuracionConexion instanceof ConexionIP ){
            conexionIP = (ConexionIP)configuracionConexion;
        }else{
            throw new RuntimeException("El tipo de Conexión no es válido");
        }
    }

    public void enviarPaqueteComunicacion(PaqueteComunicacion paqueteComunicacion) {
        if( conexionIP == null){
            throw new RuntimeException("No hay ninguna conexión disponible para enviar el paquete");
        }
        try {
            datagramSocket = new DatagramSocket(conexionIP.getPuerto());
            datagramSocket.connect(InetAddress.getByName(conexionIP.getDireccionIPv4()),conexionIP.getPuerto());
            datagramSocket.send(new DatagramPacket(paqueteComunicacion.getDatos(), paqueteComunicacion.getDatos().length));

            if( DEBUG ){
                populateDebugMessage(paqueteComunicacion);
            }else{
                populateMessage(paqueteComunicacion);
            }

        } catch (UnknownHostException ex) {
            throw new RuntimeException("El host de la conexión no es válido");
        } catch (SocketException ex) {
            throw new RuntimeException("No se puede conectar al socket");
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }finally{
            if (datagramSocket != null){
                datagramSocket.close();
            }
        }
    }

    private void populateMessage(PaqueteComunicacion paqueteComunicacion ){
        byte[] idTanque = paqueteComunicacion.getDatos(0,10);
        String idTanqueString = paqueteComunicacion.getDatosString(idTanque);

        byte[] idComando = paqueteComunicacion.getDatos(10,14);
        String idComandoString = paqueteComunicacion.getDatosInt(idComando).toString();

        String message = "[" + new Date() + "] Enviando: ";
        message += "idTanque: " + idTanqueString.trim() + "  idComando: " + idComandoString;
        dispatchEvent(new ConexionMessageEvent(this, message));
    }

    private void populateDebugMessage(PaqueteComunicacion paqueteComunicacion ){
        
        byte[] tipoPaquete = paqueteComunicacion.getDatos(0,4);
        String tipoPaqueteString = paqueteComunicacion.getDatosInt(tipoPaquete).toString();

        byte[] idTanque = paqueteComunicacion.getDatos(4,14);
        String idTanqueString = paqueteComunicacion.getDatosString(idTanque);

        byte[] temperatura = paqueteComunicacion.getDatos(14,18);
        String temperaturaString = paqueteComunicacion.getDatosInt(temperatura).toString();

        byte[] nivel = paqueteComunicacion.getDatos(18,22);
        String nivelString = paqueteComunicacion.getDatosInt(nivel).toString();

        byte[] estado = paqueteComunicacion.getDatos(22,26);
        String estadoString = paqueteComunicacion.getDatosInt(estado).toString();

        String message = "[" + new Date() + "] Enviando: ";
        message += "tipoPaquete: " + tipoPaqueteString.trim() + "  idTanque: " + idTanqueString +
                "  temperatura: " + temperaturaString + "  nivel: " + nivelString +
                "  estado: " + estadoString;
        dispatchEvent(new ConexionMessageEvent(this, message));
    }
    

}