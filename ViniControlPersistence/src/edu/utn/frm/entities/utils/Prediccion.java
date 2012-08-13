/*
 * Prediccion.java
 *
 * Created on 27 de octubre de 2009, 21:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.utn.frm.entities.utils;

/**
 *
 * @author Proyecto
 */
public class Prediccion {
    
    //Suavizamiento Exponencial con Tendencia
    public static Double getAlfaTendencia(){
        return 0.2;
    }
    public static Double getBetaTendencia(){
        return 0.05;
    }
    public static Double getPromTendencia(){
        return 0.0;
    }
    public static Double getTenTendencia(){
        return 0.0;
    }
    
}