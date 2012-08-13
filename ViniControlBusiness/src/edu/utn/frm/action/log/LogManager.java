/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.log;

import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.utils.DateOperations;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Proyecto
 */
public class LogManager {

    private static LogManager instance;
    private HashMap<Proceso,StringBuffer > mapBuffer = new HashMap<Proceso, StringBuffer>();
    private HashMap<Proceso,IMessageLogger > mapLogger = new HashMap<Proceso, IMessageLogger>();
    private IMessageLogger messageLogger;
    private StringBuffer buffer = new StringBuffer();

    private LogManager(){
       
    }

    public static LogManager getInstance(){
        if( instance == null ){
            instance = new LogManager();
        }
        return instance;
    }

    public StringBuffer getMessageOriginal(Proceso proceso){
        return proceso != null? mapBuffer.get( proceso ): buffer;
    }

    public void limpiar(Proceso proceso){
        if( proceso != null && mapBuffer.containsKey(proceso) ){
            mapBuffer.put(proceso, new StringBuffer());
        }else if( proceso == null ){
            buffer = new StringBuffer();
        }
    }

    public String setLogger(IMessageLogger logger, Proceso proceso) {
        if( proceso != null ){
            this.mapLogger.put(proceso, logger);
            if( !mapBuffer.containsKey(proceso) ){
                mapBuffer.put(proceso, new StringBuffer());
            }
            return mapBuffer.get(proceso).toString();
        }else{
            messageLogger = logger;
            return buffer.toString();
        }
    }

    public synchronized void addLog(String messageToAdd, Proceso proceso){
        Date fecha = new Date();
        String message = "["+ DateOperations.toDateString(fecha, "/", ":") + "] " + messageToAdd + " \n";
        if( proceso!=null && mapLogger.containsKey(proceso) ){
            mapLogger.get(proceso).setMessage(message);
        }
        if( proceso!=null ) {
            if( !mapBuffer.containsKey(proceso) ){
                mapBuffer.put(proceso, new StringBuffer());
            }
            mapBuffer.get(proceso).append( message );
        }else{
            buffer.append(message);
        }
    }

}