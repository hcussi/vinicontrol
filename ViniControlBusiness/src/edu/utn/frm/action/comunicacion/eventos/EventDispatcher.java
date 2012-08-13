/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion.eventos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Proyecto
 */
public abstract class EventDispatcher{

    /**
     * Los escuchadores a ser notificados cuando un evento ocurra
     */
    protected List<ViniControlListener> listeners;

    /**
     * Crea una instancia de la clase EventDispatcher
     */
    public EventDispatcher(){
        listeners = new ArrayList<ViniControlListener>();
    }

    /**
     * Agrega una clase escuchador a la lista para ser notificada
     * @param viniControlListener El escuchador a agregar
     */
    public void addEventListener(ViniControlListener viniControlListener){
        if( listeners != null && !listeners.contains(viniControlListener) ){
            listeners.add(viniControlListener);
        }
    }

    /**
     * Remueve una clase escuchador de la lista para ser notificada
     * @param viniControlListener El escuchador a remover
     */
    public void removeEventListener(ViniControlListener viniControlListener){
        if( listeners != null ){
            listeners.remove(viniControlListener);
        }
    }

    /**
     * Notifica a los escuchadores que un nuevo evento a ocurrido en el sistema
     * @param event El evento general del sistema
     */
    protected void dispatchEvent( ViniControlEvent event ){
        for (ViniControlListener viniControlListener : listeners) {
            viniControlListener.actionPerformed(event);
        }
    }

}