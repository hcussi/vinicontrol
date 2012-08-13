/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.conexion.estrategia;

import edu.utn.conexion.eventos.ConexionMessageEvent;
import edu.utn.conexion.eventos.ConexionMessageListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hernán
 */
public abstract class EstrategiaComunicacion implements IEstrategiaComunicacion{

    protected List<ConexionMessageListener> listeners;

    public EstrategiaComunicacion(){
        listeners = new ArrayList<ConexionMessageListener>();
    }

    public void addEventListener(ConexionMessageListener conexionMessageListener){
        if( listeners != null && !listeners.contains(conexionMessageListener) ){
            listeners.add(conexionMessageListener);
        }
    }

    public void removeEventListener(ConexionMessageListener conexionMessageListener){
        if( listeners != null ){
            listeners.remove(conexionMessageListener);
        }
    }

    protected void dispatchEvent( ConexionMessageEvent event ){
        for (ConexionMessageListener conexionMessageListener : listeners) {
            conexionMessageListener.updateMessage(event);
        }
    }

}