/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.operacion;

import edu.utn.frm.action.comunicacion.PaqueteComunicacion;
import edu.utn.frm.action.comunicacion.estrategia.FactoriaEstrategia;
import edu.utn.frm.action.comunicacion.GestorComunicacionArmar;
import edu.utn.frm.action.comunicacion.estrategia.IEstrategiaComunicacion;
import edu.utn.frm.action.exception.ComunicacionException;
import edu.utn.frm.action.log.LogManager;
import edu.utn.frm.entities.comunicacion.ConexionIP;
import edu.utn.frm.entities.comunicacion.ConexionSerie;
import edu.utn.frm.entities.comunicacion.ConfiguracionConexion;
import edu.utn.frm.entities.comunicacion.TipoConexion;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.tanque.EstadoTanque;
import edu.utn.frm.entities.tanque.Tanque;
import org.apache.log4j.Logger;

/**
 *
 * @author Proyecto
 */
public abstract class Comando{

    protected ConfiguracionConexion configuracionConexion;
    protected Tanque tanque;
    protected EstadoTanque estadoTanque;
    private static Logger logger = Logger.getLogger(Comando.class);
    private LogManager logManager = LogManager.getInstance();
    private Proceso proceso;

    public Comando(ConfiguracionConexion configuracionConexion, Proceso proceso){
        this.configuracionConexion = configuracionConexion;
        this.proceso = proceso;
    }

    public void setTanque(Tanque tanque, EstadoTanque estadoTanque) {
        this.tanque = tanque;
        this.estadoTanque = estadoTanque;
    }

    private IEstrategiaComunicacion getEstrategia(){
        logger.info("[Comando.getEstrategia]");
        if( configuracionConexion instanceof ConexionIP ){
            ConexionIP conexionIP = (ConexionIP)configuracionConexion;
            if( conexionIP.getTipoConexion().equals(TipoConexion.TCP) ){
                logger.info("Creando conexión TCP");
                logManager.addLog("Creando conexión TCP", proceso);
                return FactoriaEstrategia.getInstance().getEstrategia(FactoriaEstrategia.ESTRATEGIA_TCP, proceso);
            }else{
                logger.info("Creando conexión UDP");
                logManager.addLog("Creando conexión UDP", proceso);
                return FactoriaEstrategia.getInstance().getEstrategia(FactoriaEstrategia.ESTRATEGIA_UDP, proceso);
            }
        }else if(configuracionConexion instanceof ConexionSerie){
            logger.info("Creando conexión Serie");
            logManager.addLog("Creando conexión Serie", proceso);
            return FactoriaEstrategia.getInstance().getEstrategia(FactoriaEstrategia.ESTRATEGIA_RS232, proceso);
        }else{
            return null;
        }
    }

    protected abstract int getComandoID();

    public void execute() throws ComunicacionException{
        logger.info("[Comando.execute]");
        logManager.addLog("[Comando.execute]", proceso);
        IEstrategiaComunicacion estrategia = getEstrategia();
        estrategia.setConfiguracionConexion(configuracionConexion);
        estrategia.enviarPaqueteComunicacion(armarPaqueteComunicacion());
    }

    protected PaqueteComunicacion armarPaqueteComunicacion() {
        logger.info("[Comando.armarPaqueteComunicacion]");
        logManager.addLog("[Comando.armarPaqueteComunicacion]", proceso);
        logger.info("Armando el paquete de comunicación");
        logManager.addLog("Armando el paquete de comunicación", proceso);
        GestorComunicacionArmar gca = new GestorComunicacionArmar( proceso );
        return gca.armarPaqueteEnviar( getComandoID());
    }

}