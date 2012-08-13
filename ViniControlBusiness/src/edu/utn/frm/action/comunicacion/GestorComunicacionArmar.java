/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion;

import edu.utn.frm.action.exception.ComunicacionException;
import edu.utn.frm.action.log.LogManager;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.tanque.EstadoTanque;
import org.apache.log4j.Logger;

/**
 *
 * @author Proyecto
 */
public class GestorComunicacionArmar{

    private static Logger logger = Logger.getLogger(GestorComunicacionArmar.class);
    private LogManager logManager = LogManager.getInstance();
    private Proceso proceso;
    public GestorComunicacionArmar(Proceso proceso){
        this.proceso = proceso;
    }

    /**
     *
     * @param datos El buffer con los datos recibidos
     * @return El estado del tanque a partir de los datos recibidos de la conexión
     * @throws ComunicacionException
     */
    public EstadoTanque armarEstadoTanque(byte[] datos) throws ComunicacionException{
        logger.info("[GestorComunicacionArmar.armarEstadoTanque]");
        logger.info("Armando estado del tanque");
        logManager.addLog("[GestorComunicacionArmar.armarEstadoTanque]", proceso);
        logManager.addLog("Armando estado del tanque", proceso);

        EstadoTanque estadoTanque = new EstadoTanque();

        PaqueteComunicacion paqueteComunicacion = new PaqueteComunicacion(12);
        paqueteComunicacion.setDatos(datos);

        try{
            int temperatura = paqueteComunicacion.getDatosInt(paqueteComunicacion.getDatosString(datos),0,4);
            int nivel = paqueteComunicacion.getDatosInt(paqueteComunicacion.getDatosString(datos),5,8);
            int estado = paqueteComunicacion.getDatosInt(paqueteComunicacion.getDatosString(datos),9,12);

            estadoTanque.setNivelAzucar(new Double(nivel));
            estadoTanque.setNivelContenido(new Double(estado));
            estadoTanque.setTemperatura(new Double(temperatura));
        }catch(NumberFormatException ex){
            logger.error("El formato de la trama recibida no es válido");
            logManager.addLog("El formato de la trama recibida no es válido", proceso);
            throw new ComunicacionException("El formato de la trama recibida no es válido");
        }
        logger.info("El estado del tanque ha sido creado.");
        logManager.addLog("El estado del tanque ha sido creado.", proceso);
        logger.info(estadoTanque);
        logManager.addLog("Estado del tanque: " + estadoTanque, proceso);
        return estadoTanque;
    }

    /**
     *
     * @param idTipoComando El identificador del tipo del comando
     * @return El paquete de comunicación armado a partir de los datos dados
     */
    public PaqueteComunicacion armarPaqueteEnviar(Integer idTipoComando) {
        logger.info("Armando paquete de comunicación para enviar");
        logManager.addLog("Armando paquete de comunicación para enviar", proceso);
        PaqueteComunicacion paqueteComunicacion = new PaqueteComunicacion(4);

        /* Tipo Comando */
        /* Representa un entero/ 4 bytes */
        paqueteComunicacion.put(idTipoComando.toString().getBytes());

        return paqueteComunicacion;
    }

    /**
     *
     * @param temperatura La temperatura del tanque
     * @param nivel El nivel de azúcar
     * @param estado El nivel del tanque
     * @return El paquete de comunicación armado a partir de los datos dados
     */
     public PaqueteComunicacion armarPaqueteEnviarSimulacion(int temperatura, int nivel, int estado) {
        logger.info("[GestorComunicacionArmar.armarPaqueteEnviarSimulacion]");
        logManager.addLog("[GestorComunicacionArmar.armarPaqueteEnviarSimulacion]", proceso);
        PaqueteComunicacion paqueteComunicacion = new PaqueteComunicacion(12);

        /* Temperatura */
        /* Representa un entero/ 4 bytes */
        paqueteComunicacion.put(temperatura);

        /* Nivel */
        /* Representa un entero/ 4 bytes */
        paqueteComunicacion.put(nivel);

        /* Estado */
        /* Representa un entero/ 4 bytes */
        paqueteComunicacion.put(estado);

        return paqueteComunicacion;
    }
}
