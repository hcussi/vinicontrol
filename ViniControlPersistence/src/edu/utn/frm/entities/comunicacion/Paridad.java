/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.comunicacion;

/**
 *
 * @author Proyecto
 */
public enum Paridad {
    NONE("Ninguna"),ODD("Par"), EVEN("Impar"), MARK("Marca"), SPACE("Espacio");

    private String descripcion;

    private Paridad(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
