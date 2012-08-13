/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion;

import edu.utn.frm.action.comunicacion.estrategia.*;
import edu.utn.frm.action.exception.ComunicacionException;
import edu.utn.frm.action.exception.GestorGeneralEstadoException;
import edu.utn.frm.action.log.LogManager;
import edu.utn.frm.entities.comunicacion.ConexionIP;
import edu.utn.frm.entities.comunicacion.ConexionSerie;
import edu.utn.frm.entities.comunicacion.ConfiguracionConexion;
import edu.utn.frm.entities.comunicacion.TipoConexion;
import edu.utn.frm.entities.proceso.Proceso;
import org.apache.log4j.Logger;

/**
 *
 * @author Proyecto
 */
public class GestorComunicacionEnviar {

    private static Logger logger = Logger.getLogger(GestorComunicacionEnviar.class);
    private GestorGeneralEstado gestorGeneralEstado;
    private Proceso proceso;
    private LogManager logManager = LogManager.getInstance();

    public GestorComunicacionEnviar(Proceso proceso){
        this.proceso = proceso;
        gestorGeneralEstado = GestorGeneralEstado.getInstance();
    }

    /**
     *
     * @param configuracionConexion La configuración de conexión para el tanque
     * @return La estrategia de comunicación a utilizar de acuerdo a la conexión
     */
    private IEstrategiaComunicacion getEstrategiaComunicacion(ConfiguracionConexion configuracionConexion){
        logger.info("[GestorComunicacionEnviar.getEstrategiaComunicacion]");
        logManager.addLog("[GestorComunicacionEnviar.getEstrategiaComunicacion]", null);
        IEstrategiaComunicacion estrategiaComunicacion = null;

        if( configuracionConexion instanceof ConexionIP ){
            ConexionIP conexionIP = (ConexionIP)configuracionConexion;
            if( conexionIP.getTipoConexion().equals(TipoConexion.TCP) ){
                estrategiaComunicacion = FactoriaEstrategia.getInstance().getEstrategia(
                        FactoriaEstrategia.ESTRATEGIA_TCP, proceso);
            }else{
                estrategiaComunicacion = FactoriaEstrategia.getInstance().getEstrategia(
                        FactoriaEstrategia.ESTRATEGIA_UDP, proceso);
            }
        }else if(configuracionConexion instanceof ConexionSerie){
            estrategiaComunicacion = FactoriaEstrategia.getInstance().getEstrategia(
                        FactoriaEstrategia.ESTRATEGIA_RS232, proceso);
        }

        return estrategiaComunicacion;
    }

    /**
     * Se envia el paquete de comunicación de acuerdo a la estrategia de comunicación utilizada
     * @param configuracionConexion La configuración de conexión que se va a utilizar
     * @param paqueteComunicacion El paquete de comunicación recibido
     * @throws ComunicacionException
     */
    public void procesarPaquete(ConfiguracionConexion configuracionConexion, PaqueteComunicacion paqueteComunicacion) throws ComunicacionException{
        logger.info("[GestorComunicacionEnviar.procesarPaquete]");
        logManager.addLog("[GestorComunicacionEnviar.procesarPaquete]", null);
        IEstrategiaComunicacion estrategiaComunicacion = getEstrategiaComunicacion(configuracionConexion);

        if( estrategiaComunicacion != null ){
            estrategiaComunicacion.enviarPaqueteComunicacion(paqueteComunicacion);
        }
    }

    /**
     * Se prueba una configuración de conexión para determinar si es válida
     * @param configuracionConexion La configuración de conexión
     * @throws ComunicacionException
     */
    public void probarConexion(ConfiguracionConexion configuracionConexion) throws ComunicacionException{
        logger.info("[GestorComunicacionEnviar.probarConexion]");
        logManager.addLog("[GestorComunicacionEnviar.probarConexion]", null);
        IEstrategiaComunicacion estrategiaComunicacion = getEstrategiaComunicacion(configuracionConexion);

        if( estrategiaComunicacion != null ){
            estrategiaComunicacion.setConfiguracionConexion(configuracionConexion);
            estrategiaComunicacion.probarConfiguracionConexion();
        }else{
            logger.error("No se puede probar la Conexión. No se ha podido crear una Estrategia de Conexión");
            logManager.addLog("No se puede probar la Conexión. No se ha podido crear una Estrategia de Conexión", null);
            throw new ComunicacionException("No se puede probar la Conexión. Intente más tarde");
        }
    }

    /**
     * Se prueba una configuración de conexión remota para determinar si es válida
     * @param configuracionConexion La configuración de conexión
     * @throws ComunicacionException
     */
    public void probarConexionRecepcion(ConfiguracionConexion configuracionConexion) throws ComunicacionException{
        logger.info("[GestorComunicacionEnviar.probarConexionRecepcion]");
        logManager.addLog("[GestorComunicacionEnviar.probarConexionRecepcion]", null);
        IEstrategiaComunicacion estrategiaComunicacion = getEstrategiaComunicacion(configuracionConexion);

        if( estrategiaComunicacion != null ){
            estrategiaComunicacion.setConfiguracionConexion(configuracionConexion);
            estrategiaComunicacion.probarConfiguracionRecepcionConexion();
        }else{
            logger.error("No se puede probar la Conexión. No se ha podido crear una Estrategia de Conexión");
            logManager.addLog("No se puede probar la Conexión. No se ha podido crear una Estrategia de Conexión", null);
            throw new ComunicacionException("No se puede probar la Conexión. Intente más tarde");
        }
    }

    /**
     * Se ejecuta el comando de bajar temperatura para el proceso
     * @param proceso El proceso que se esta controlando
     * @throws GestorGeneralEstadoException
     * @throws ComunicacionException
     */
    public void enviarComandoBajarTemperatura(Proceso proceso) throws GestorGeneralEstadoException, ComunicacionException{
        logger.info("[GestorComunicacionEnviar.enviarComandoBajarTemperatura]");
        logManager.addLog("[GestorComunicacionEnviar.enviarComandoBajarTemperatura]", null);
        gestorGeneralEstado.enviarComandoBajarTemperatura(proceso);
    }

    /**
     * Se ejecuta el comando de remontaje para el proceso
     * @param proceso El proceso que se esta controlando
     * @throws GestorGeneralEstadoException
     * @throws ComunicacionException
     */
    public void enviarComandoRemontaje(Proceso proceso) throws GestorGeneralEstadoException, ComunicacionException{
        logger.info("[GestorComunicacionEnviar.enviarComandoRemontaje]");
        logManager.addLog("[GestorComunicacionEnviar.enviarComandoRemontaje]", null);
        gestorGeneralEstado.enviarComandoRemontaje(proceso);
    }

    /**
     * Se ejecuta el comando de fin de remontaje para el proceso
     * @param proceso El proceso que se esta controlando
     * @throws GestorGeneralEstadoException
     * @throws ComunicacionException
     */
    public void enviarComandoFinRemontaje(Proceso proceso) throws GestorGeneralEstadoException, ComunicacionException{
        logger.info("[GestorComunicacionEnviar.enviarComandoFinRemontaje]");
        logManager.addLog("[GestorComunicacionEnviar.enviarComandoFinRemontaje]", null);
        gestorGeneralEstado.enviarComandoFinRemontaje(proceso);
    }

    /**
     *
     * @param idTipoComando Un identificador único que representa el comando a ejecutar
     * @return El paquete de comunicación armado para enviar
     */
    public PaqueteComunicacion armarPaquete(int idTipoComando) {
        logger.info("[GestorComunicacionEnviar.armarPaquete]");
        logManager.addLog("[GestorComunicacionEnviar.armarPaquete]", null);
        return new GestorComunicacionArmar( proceso ).armarPaqueteEnviar( idTipoComando );
    }

    /**
     * 
     * @param temperatura la temperatura del tanque
     * @param nivel El nivel de azúcar
     * @param estado El nivel del tanque
     * @return El paquete de comunicación armado para enviar
     */
    public PaqueteComunicacion armarPaqueteSimulacion(int temperatura, int nivel, int estado) {
        logger.info("[GestorComunicacionEnviar.armarPaqueteSimulacion]");
        logManager.addLog("[GestorComunicacionEnviar.armarPaqueteSimulacion]", null);
        return new GestorComunicacionArmar( proceso ).armarPaqueteEnviarSimulacion(temperatura, nivel, estado);
    }
}
