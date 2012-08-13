/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.proceso;

/**
 *
 * @author Proyecto
 */
public enum EstadoProceso {
    INICIADO("Iniciado"),EN_ESPERA("En espera"), PAUSADO("Pausado"), CANCELADO("Cancelado"),
    TERMINADO("Terminado");

    private String descripcion;

    private EstadoProceso(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
