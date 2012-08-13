/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.action.comunicacion.estrategia;

import edu.utn.frm.action.GestorConfiguracionSistema;
import edu.utn.frm.action.comunicacion.eventos.EventDispatcher;
import edu.utn.frm.action.comunicacion.PaqueteComunicacion;
import edu.utn.frm.action.comunicacion.eventos.ConexionMessageEvent;
import edu.utn.frm.action.comunicacion.eventos.NuevoPaqueteEvent;
import edu.utn.frm.action.exception.ComunicacionException;
import edu.utn.frm.action.log.LogManager;
import edu.utn.frm.entities.comunicacion.ConexionIP;
import edu.utn.frm.entities.comunicacion.ConfiguracionConexion;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.sistema.ConfiguracionSistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import org.apache.log4j.Logger;

/**
 *
 * @author Proyecto
 */
public class EstrategiaComunicacionUDP extends EventDispatcher implements IEstrategiaComunicacion {

    private ConexionIP conexionIP;
    private DatagramSocket datagramSocket = null;
    private boolean DEBUG = false;
    private static Logger logger = Logger.getLogger(EstrategiaComunicacionUDP.class);
    private DatagramSocket dsocket;
    private SwingWorker worker;
    private LogManager logManager = LogManager.getInstance();
    private static Integer REINTENTOS_RECONEXION = 10;
    private static Integer SEGUNDOS_RECONEXION = 5;
    private Timer timerReconexion;
    private Proceso proceso;
    private Integer reintentos = 0;

    public EstrategiaComunicacionUDP(Proceso proceso) {
        super();
        this.proceso = proceso;
        ConfiguracionSistema cs = GestorConfiguracionSistema.getInstance().getConfiguracionSistema();
        if (cs != null) {
            if (cs.getReitentosReconexion() != null) {
                REINTENTOS_RECONEXION = cs.getReitentosReconexion();
            }
            if (cs.getSegundosReconexion() != null) {
                SEGUNDOS_RECONEXION = cs.getSegundosReconexion();
            }
        }
    }

    /**
     * Activa el modo del debug para la simulación
     */
    public void setDebugMode() {
        DEBUG = true;
    }

    /**
     * Desactiva el modo de debug
     */
    public void deactivateDebugMode() {
        DEBUG = false;
    }

    /**
     * Se setea la configuración de conexión que esta estrategia tiene que utilizar
     * @param configuracionConexion La configuración de conexión IP
     */
    public void setConfiguracionConexion(ConfiguracionConexion configuracionConexion) {
        logger.info("[EstrategiaComunicacionUDP.setConfiguracionConexion]");
        logManager.addLog("[EstrategiaComunicacionUDP.setConfiguracionConexion]", proceso);
        if (configuracionConexion instanceof ConexionIP) {
            conexionIP = (ConexionIP) configuracionConexion;
            logManager.addLog(conexionIP.toString(), proceso);
            logger.info("La conexión se ha seteado exitosamente");
            logManager.addLog("La conexión se ha seteado exitosamente", proceso);
        } else {
            throw new RuntimeException("El tipo de Conexión no es válido");
        }
    }

    /**
     * Se envia el paquete de comunicación como un paquete UDP
     * @param paqueteComunicacion El paquete de comunicación a enviar
     */
    public void enviarPaqueteComunicacion(PaqueteComunicacion paqueteComunicacion) {
        logger.info("[EstrategiaComunicacionUDP.enviarPaqueteComunicacion]");
        logManager.addLog("[EstrategiaComunicacionUDP.enviarPaqueteComunicacion]", proceso);
        if (conexionIP == null) {
            logManager.addLog("No hay ninguna conexión disponible para enviar el paquete", proceso);
            throw new RuntimeException("No hay ninguna conexión disponible para enviar el paquete");
        }
        try {
            datagramSocket = new DatagramSocket(conexionIP.getPuerto());
            datagramSocket.connect(InetAddress.getByAddress(conexionIP.getDireccionIPv4Bytes()), conexionIP.getPuerto());
            DatagramPacket packet = new DatagramPacket(paqueteComunicacion.getDatos(), paqueteComunicacion.getDatos().length);
            logger.info("Se ha creado el socket para la comunicación");
            logManager.addLog("Se ha creado el socket para la comunicación", proceso);
            datagramSocket.send(packet);
            logger.info("Se ha enviado el paquete exitosamente");
            logManager.addLog("Se ha enviado el paquete exitosamente", proceso);
            if (DEBUG) {
                logger.info("El modo DEBUG esta activado");
                logManager.addLog("El modo DEBUG esta activado", proceso);
                populateDebugMessage(paqueteComunicacion);
            } else {
                populateMessage(paqueteComunicacion);
            }

        } catch (UnknownHostException ex) {
            logger.error("El host de la conexión no es válido");
            logManager.addLog("El host de la conexión no es válido", proceso);
        } catch (SocketException ex) {
            logger.error("No se puede conectar al socket, esta siendo ocupado");
            logManager.addLog("No se puede conectar al socket, esta siendo ocupado", proceso);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            logManager.addLog(ex.getMessage(), proceso);
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
                logger.info("Se ha cerrado la conexión exitosamente");
                logManager.addLog("Se ha cerrado la conexión exitosamente", proceso);
            }
        }
    }

    /**
     * Se prepara la recepción de los paquetes de comunicación
     * @throws ComunicacionException
     */
    public void recibirPaquete() throws ComunicacionException {
        logger.info("[EstrategiaComunicacionUDP.recibirPaquete]");
        logManager.addLog("[EstrategiaComunicacionUDP.recibirPaquete]", proceso);
        // Crea un socket para escuchar en el puerto.
        try {
            if (dsocket == null || dsocket.isClosed()) {
                dsocket = new DatagramSocket(conexionIP.getPuertoRecepcion(), InetAddress.getByAddress(conexionIP.getDireccionIPv4Bytes()));
            }
            worker = new SwingWorker() {
                // Crea un buffer para alamcenar los datagramas. Si el paquete es mas largo
                // que el buffer, el exceso será descartado

                byte[] buffer = new byte[12];
                // Crea un paquete para recibir los datos adentro del buffer
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                @Override
                protected Object doInBackground() throws Exception {
                    logger.info("[SwingWorker.doInBackground]");
                    logManager.addLog("[SwingWorker.doInBackground]", proceso);
                    logger.info("Preparado para recibir tramas UDP en background");
                    logManager.addLog("Preparado para recibir tramas UDP en background", proceso);
                    // Espera hasta recibir un datagrama
                    dsocket.receive(packet);
                    dispatchEvent(new NuevoPaqueteEvent(this, buffer));
                    return null;
                }

                @Override
                protected void done() {
                    logger.info("[SwingWorker.done]");
                    logManager.addLog("[SwingWorker.done]", proceso);

                    logger.info("Trama UDP Recibida. Preparando para recibir nuevamente");
                    logManager.addLog("Trama UDP Recibida. Preparando para recibir nuevamente", proceso);
                    if (!worker.isCancelled()) {
                        try {
                            recibirPaquete();
                        } catch (ComunicacionException ex) {
                            logManager.addLog("No se pudo establecer la conexión", proceso);
                            logger.error("No se pudo establecer la conexión");
                        }
                    }

                }
            };
            worker.execute();
        } catch (Exception ex) {

            if (reintentos >= REINTENTOS_RECONEXION) {
                logger.error("Se han terminado los reintentos de reconexión");
                logManager.addLog("Se han terminado los reintentos de reconexión", proceso);
                throw new ComunicacionException(ex.getMessage());
            }
            reintentos++;
            logger.info("Se esta intentando reparar la conexión");
            logger.info("Intento " + reintentos + " de " + REINTENTOS_RECONEXION);
            logManager.addLog("Se esta intentando reparar la conexión", proceso);
            logManager.addLog("Intento " + reintentos + " de " + REINTENTOS_RECONEXION, proceso);
            if (timerReconexion != null && timerReconexion.isRunning()) {
                timerReconexion.stop();
            }
            timerReconexion = new Timer(SEGUNDOS_RECONEXION * 1000, new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    try {
                        recibirPaquete();
                    } catch (ComunicacionException ex) {
                        logManager.addLog("No se pudo establecer la conexión", proceso);
                        logger.error("No se pudo establecer la conexión");
                    }
                }
            });
            timerReconexion.setRepeats(false);
            timerReconexion.start();
        }


    }

    /**
     * Se intenta cerrar la conexión UDP para liberar el recurso
     */
    public void detenerConexion() {
        logger.info("[EstrategiaComunicacionUDP.detenerConexion]");
        logManager.addLog("[EstrategiaComunicacionUDP.detenerConexion]", proceso);
        logger.info("Deteniendo la conexión y liberando recursos");
        logManager.addLog("Deteniendo la conexión y liberando recursos", proceso);
        if (worker != null) {
            worker.cancel(true);
        }
        if (dsocket != null && dsocket.isConnected()) {
            dsocket.close();
        }
    }

    /**
     * Se dispara un evento para mostrar el paquete de comunicación recibido
     * @param paqueteComunicacion El paquete de comunicación a mostrar
     */
    private void populateMessage(PaqueteComunicacion paqueteComunicacion) {
        byte[] datos = paqueteComunicacion.getDatos();
        Integer idComando = paqueteComunicacion.getDatosInt(paqueteComunicacion.getDatosString(datos), 0, 4);

        String message = "[" + new Date() + "] Enviando: ";
        message += "idComando: " + idComando;
        logger.info(message);
        logManager.addLog(message, proceso);
        dispatchEvent(new ConexionMessageEvent(this, message));
    }

    /**
     * Se dispara un evento para mostrar el paquete de comunicación recibido en modo debug
     * @param paqueteComunicacion El paquete de comunicación a mostrar
     */
    private void populateDebugMessage(PaqueteComunicacion paqueteComunicacion) {

        byte[] temperatura = paqueteComunicacion.getDatos(0, 4);
        String temperaturaString = paqueteComunicacion.getDatosInt(temperatura).toString();

        byte[] nivel = paqueteComunicacion.getDatos(4, 8);
        String nivelString = paqueteComunicacion.getDatosInt(nivel).toString();

        byte[] estado = paqueteComunicacion.getDatos(8, 12);
        String estadoString = paqueteComunicacion.getDatosInt(estado).toString();

        String message = "[" + new Date() + "] Enviando: ";
        message += "  temperatura: " + temperaturaString + "  nivel: " + nivelString +
                "  estado: " + estadoString;
        logger.info(message);
        logManager.addLog(message, proceso);
        dispatchEvent(new ConexionMessageEvent(this, message));
    }

    /**
     * Se intenta abrir la conexión con la configuración de conexión seteada
     * @throws ComunicacionException
     */
    public void probarConfiguracionConexion() throws ComunicacionException {
        logger.info("[EstrategiaComunicacionConexion.probarConfiguracionConexion]");
        logManager.addLog("[EstrategiaComunicacionConexion.probarConfiguracionConexion]", proceso);
        try {
            datagramSocket = new DatagramSocket(conexionIP.getPuerto());
            datagramSocket.connect(InetAddress.getByName(conexionIP.getDireccionIPv4()), conexionIP.getPuerto());
            logger.info("La prueba de conexión ha sido exitosa");
            logManager.addLog("La prueba de conexión ha sido exitosa", proceso);
            logger.info(conexionIP);
            logManager.addLog(conexionIP.toString(), proceso);
        } catch (UnknownHostException ex) {
            logger.error("El host de la conexión no es válido");
            throw new ComunicacionException("El host de la conexión no es válido");
        } catch (SocketException ex) {
            logger.error("No se puede conectar al socket");
            throw new ComunicacionException("No se puede conectar al socket");
        } catch (IOException ex) {
            logger.error("Ha ocurrido un error inesperado. " + ex.getMessage());
            throw new ComunicacionException("Ha ocurrido un error inesperado. " + ex.getMessage());
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
                logger.info("Cerrando la conexión");
                logManager.addLog("Cerrando la conexión", proceso);
            }
        }
    }

    /**
     * Se intenta abrir la conexión con la configuración remota de conexión seteada
     * @throws ComunicacionException
     */
    public void probarConfiguracionRecepcionConexion() throws ComunicacionException {
        logger.info("[EstrategiaComunicacionConexion.probarConfiguracionRecepcionConexion]");
        logManager.addLog("[EstrategiaComunicacionConexion.probarConfiguracionRecepcionConexion]", proceso);
        try {
            datagramSocket = new DatagramSocket(conexionIP.getPuerto());
            datagramSocket.connect(InetAddress.getByName(conexionIP.getDireccionIPv4()), conexionIP.getPuertoRecepcion());
            logger.info("La prueba de conexión ha sido exitosa");
            logManager.addLog("La prueba de conexión ha sido exitosa", proceso);
            logger.info(conexionIP);
            logManager.addLog(conexionIP.toString(), proceso);
        } catch (UnknownHostException ex) {
            logger.error("El host de la conexión no es válido");
            throw new ComunicacionException("El host de la conexión no es válido");
        } catch (SocketException ex) {
            logger.error("No se puede conectar al socket");
            throw new ComunicacionException("No se puede conectar al socket");
        } catch (IOException ex) {
            logger.error("Ha ocurrido un error inesperado. " + ex.getMessage());
            throw new ComunicacionException("Ha ocurrido un error inesperado. " + ex.getMessage());
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
                logger.info("Se ha cerrado la conexión exitosamente");
                logManager.addLog("Se ha cerrado la conexión exitosamente", proceso);
            }
        }
    }
}
