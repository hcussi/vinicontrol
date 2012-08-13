/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion.eventos;

import edu.utn.frm.entities.proceso.Proceso;

/**
 *
 * @author Proyecto
 */
public class NuevoComandoEvent extends ViniControlEvent{

    private String message;
    private Proceso proceso;

    public NuevoComandoEvent(Object source, String message,Proceso proceso) {
        super(source);
        this.message = message;
        this.proceso = proceso;
    }

    public String getMessage(){

        return message;
    }

    public Proceso getProceso() {
        return proceso;
    }

    
}
