/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.exception;

/**
 *
 * @author Proyecto
 */
public class ReporteException extends Exception{

    public ReporteException(String message){
        super(message);
    }

    public ReporteException(Exception innerException){
        super(innerException);
    }
}
