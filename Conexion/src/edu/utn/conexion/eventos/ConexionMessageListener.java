/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.conexion.eventos;

import java.util.EventListener;

/**
 *
 * @author Hernán
 */
public interface ConexionMessageListener extends EventListener{

    /**
     * Invoked when an action occurs.
     */
    public void updateMessage(ConexionMessageEvent e);
}