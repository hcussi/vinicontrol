/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.conexion.entities;

/**
 *
 * @author Proyecto
 */
public abstract class ConfiguracionConexion{

    private String codigo;
    private String descripcion;
    private Boolean activa;

    public ConfiguracionConexion(){
        activa = true;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the activa
     */
    public Boolean getActiva() {
        return activa;
    }

    /**
     * @param activa the activa to set
     */
    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

}