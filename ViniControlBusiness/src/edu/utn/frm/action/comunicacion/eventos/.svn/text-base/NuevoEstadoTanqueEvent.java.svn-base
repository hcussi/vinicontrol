/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion.eventos;

import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.tanque.EstadoTanque;

/**
 *
 * @author Proyecto
 */
public class NuevoEstadoTanqueEvent extends ViniControlEvent{

    private EstadoTanque estadoTanque;
    private Proceso proceso;

    public NuevoEstadoTanqueEvent(Object source, EstadoTanque estadoTanque,Proceso proceso) {
        super(source);
        this.estadoTanque = estadoTanque;
        this.proceso = proceso;
    }

    public EstadoTanque getEstadoTanque(){

        return estadoTanque;
    }

    public Proceso getProceso() {
        return proceso;
    }
    
}