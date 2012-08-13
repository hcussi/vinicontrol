/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion.eventos;

import java.util.EventListener;

/**
 * Implementa el patrón observador junto con la clase @see edu.utn.frm.action.comunicacion.eventos.EventDispatcher
 * Las clases que quieran ser notificadas cuando ocurra un evento en el sistema deben implementar
 * esta interface
 * @author Proyecto
 */
public interface ViniControlListener extends EventListener{

    /**
     * Se llama cuando se dispara un nuevo evento, este metodo notifica a los escuchadores
     * que algo ha ocurrido
     * @param e Un evento general del sistema
     */
    public void actionPerformed(ViniControlEvent e);
}