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
public class AgregarProcesoEvent extends ViniControlEvent{

    private Proceso proceso;

    public AgregarProcesoEvent(Object source, Proceso proceso) {
        super(source);
        this.proceso = proceso;
    }

    public Proceso getProceso() {
        return proceso;
    }
    
}