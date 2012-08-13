/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.conexion.eventos;

import java.util.EventObject;

/**
 *
 * @author Hernán
 */
public class ConexionMessageEvent extends EventObject{

    private String message;

    public ConexionMessageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage(){

        return message;
    }
}
