package edu.utn.frm.dao.demanda.estado;

import edu.utn.frm.dao.generic.GenericFilter;

/*
 * EstadoPrediccionFilter.java
 *
 * Created on 2 de marzo de 2008, 10:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Proyecto
 */
public class EstadoPrediccionFilter extends GenericFilter{
   
    /**
     * Creates a new instance of EstadoPrediccionFilter
     */
    public EstadoPrediccionFilter() {
        
    }

    public boolean isEmpty() {
        return super.isNullAnulada();
    }

    public void clear() {
        
    }
    
}