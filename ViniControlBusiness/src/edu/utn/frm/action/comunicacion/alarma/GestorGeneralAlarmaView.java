/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion.alarma;

import edu.utn.frm.action.comunicacion.GestorGeneralEstado;
import edu.utn.frm.action.comunicacion.eventos.NuevaAlarmaEvent;
import edu.utn.frm.action.comunicacion.eventos.ViniControlEvent;
import edu.utn.frm.action.comunicacion.eventos.ViniControlListener;
import edu.utn.frm.entities.alarma.Alarma;

/**
 *
 * @author Proyecto
 */
public class GestorGeneralAlarmaView implements ViniControlListener{

    public GestorGeneralAlarmaView(){
        GestorGeneralEstado.getInstance().addEventListener(this);
    }

    public void actionPerformed(ViniControlEvent e) {
        if( e instanceof NuevaAlarmaEvent ){
            NuevaAlarmaEvent event = (NuevaAlarmaEvent)e;

            crearPopUp(event.getAlarma());
        }
    }

    private synchronized void crearPopUp(Alarma alarma) {

        AlarmaWorker alarmaWorker = new AlarmaWorker( alarma );
        alarmaWorker.execute();
    }



}
