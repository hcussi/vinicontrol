/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.comunicacion;

/**
 *
 * @author Proyecto
 */
public enum TipoConexion {
    TCP("TCP"),UDP("UDP");

    private String descripcion;

    private TipoConexion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
