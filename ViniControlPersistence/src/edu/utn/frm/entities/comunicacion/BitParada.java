/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.comunicacion;

/**
 *
 * @author Proyecto
 */
public enum BitParada {
    NONE("Ninguno"),ONE("1"), TWO("2"), ONEPOINTFIVE("1.5");

    private String descripcion;

    private BitParada(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
