/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion;

import edu.utn.frm.action.comunicacion.estrategia.*;
import edu.utn.frm.action.comunicacion.eventos.EventDispatcher;
import edu.utn.frm.action.comunicacion.eventos.NuevoEstadoTanqueEvent;
import edu.utn.frm.action.comunicacion.eventos.NuevoPaqueteEvent;
import edu.utn.frm.action.comunicacion.eventos.ViniControlEvent;
import edu.utn.frm.action.comunicacion.eventos.ViniControlListener;
import edu.utn.frm.action.exception.ComunicacionException;
import edu.utn.frm.action.log.LogManager;
import edu.utn.frm.entities.comunicacion.ConexionIP;
import edu.utn.frm.entities.comunicacion.ConexionSerie;
import edu.utn.frm.entities.comunicacion.ConfiguracionConexion;
import edu.utn.frm.entities.comunicacion.TipoConexion;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.tanque.EstadoTanque;
import org.apache.log4j.Logger;

/**
 *
 * @author Proyecto
 */
public class GestorComunicacionRecibir extends EventDispatcher implements ViniControlListener{

    private static Logger logger = Logger.getLogger(GestorComunicacionRecibir.class);
    private Proceso proceso;
    private IEstrategiaComunicacion iec;
    private LogManager logManager = LogManager.getInstance();
    
    public GestorComunicacionRecibir(Proceso proceso){
        this.proceso = proceso;
        try {
            recibirPaquete();
        } catch (ComunicacionException ex) {
            logger.error("Ha fallado la configuración del inicio de recepción");
            logManager.addLog("Ha fallado la configuración del inicio de recepción", proceso);
            logger.error(ex.getMessage());
            logManager.addLog(ex.getMessage(), proceso);
        } 
    }

    /**
     *
     * @return El proceso que se esta siguiendo
     */
    public Proceso getProceso(){
        return this.proceso;
    }

    /**
     *
     * @param configuracionConexion La configuración de conexión para el tanque
     * @return La estrategia de comunicación a utilizar de acuerdo a la conexión
     */
    private IEstrategiaComunicacion getEstrategiaComunicacion(ConfiguracionConexion configuracionConexion){
        logger.info("[GestorComunicacionRecibir.getEstrategiaComunicacion]");
        IEstrategiaComunicacion estrategiaComunicacion = null;

        if( configuracionConexion instanceof ConexionIP ){
            ConexionIP conexionIP = (ConexionIP)configuracionConexion;
            if( conexionIP.getTipoConexion().equals(TipoConexion.TCP) ){
                estrategiaComunicacion = FactoriaEstrategia.getInstance().getEstrategia(
                        FactoriaEstrategia.ESTRATEGIA_TCP, this.proceso);
            }else{
                estrategiaComunicacion = FactoriaEstrategia.getInstance().getEstrategia(
                        FactoriaEstrategia.ESTRATEGIA_UDP, this.proceso);
            }
        }else if(configuracionConexion instanceof ConexionSerie){
            estrategiaComunicacion = FactoriaEstrategia.getInstance().getEstrategia(
                        FactoriaEstrategia.ESTRATEGIA_RS232, this.proceso);
        }

        return estrategiaComunicacion;
    }

    /**
     * Procesa los eventos del sistema de acuerdo al tipo del evento
     * @param e Un evento del sistema
     */
    public void actionPerformed(ViniControlEvent e) {
        logger.info("[GestorComunicacionRecibir.actionPerformed]");
        if( e instanceof NuevoPaqueteEvent ){
            try {
                procesarPaqueteComunicacion(((NuevoPaqueteEvent) e).getDatos());
            } catch (ComunicacionException ex) {
                logger.error(ex.getMessage());
                logManager.addLog(ex.getMessage(), proceso);
            }
        }
    }

    /**
     * Se procesan los datos recibidos
     * @param datos El buffer de comunicación
     * @throws ComunicacionException
     */
    private void procesarPaqueteComunicacion(byte[] datos) throws ComunicacionException{
        logger.info("[GestorComunicacionRecibir.procesarPaqueteComunicacion]");
        EstadoTanque estadoTanque = new GestorComunicacionArmar( proceso ).armarEstadoTanque(datos);

        dispatchEvent(new NuevoEstadoTanqueEvent(this, estadoTanque, proceso));
    }

    /**
     * Se detiene la conexión para la estrategia de comunicación que se estaba utilizando
     */
    public void detenerConexion() {
        logger.info("[GestorComunicacionRecibir.detenerConexion]");
        iec.detenerConexion();
    }

    /**
     * Se prepara la conexión para recibir los paquetes de comunicación
     * @throws ComunicacionException
     */
    private void recibirPaquete() throws ComunicacionException {
        logger.info("[GestorComunicacionRecibir.recibirPaquete]");
        if( iec != null ){
            detenerConexion();
        }
        iec = getEstrategiaComunicacion(proceso.getConfiguracionConexion());
        iec.setConfiguracionConexion(proceso.getConfiguracionConexion());
        //Suscribo para esperar el evento del nuevo estado tanque recibido
        iec.addEventListener(this);
        iec.recibirPaquete();
    }

}