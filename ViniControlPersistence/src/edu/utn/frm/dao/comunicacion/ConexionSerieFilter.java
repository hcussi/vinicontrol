package edu.utn.frm.dao.comunicacion;

import edu.utn.frm.dao.generic.GenericFilter;

public class ConexionSerieFilter extends GenericFilter {

    private String descripcion;
    private String nombrePuerto;
    private String codigo;

    public ConexionSerieFilter() {
    }

    /**
     * @return the Nombre del Puerto
     */
    public String getNombrePuerto() {
        return nombrePuerto;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
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

    @Override
    public void clear() {
        nombrePuerto = null;
        codigo = null;
        descripcion = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullNobrePuerto() && isNullCodigo() && isNullDescripcion();
    }

    public boolean isNullNobrePuerto() {

        return isNull(nombrePuerto);
    }

    public boolean isNullCodigo() {

        return isNull(codigo);
    }

    public boolean isNullDescripcion() {

        return isNull(descripcion);
    }

}