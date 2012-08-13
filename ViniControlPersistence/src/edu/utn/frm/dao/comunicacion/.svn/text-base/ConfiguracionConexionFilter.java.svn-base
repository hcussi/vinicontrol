package edu.utn.frm.dao.comunicacion;

import edu.utn.frm.dao.generic.GenericFilter;

public class ConfiguracionConexionFilter extends GenericFilter {

    private String descripcion;
    private String codigo;

    public ConfiguracionConexionFilter() {
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

    public void clear() {
        descripcion = null;
        codigo = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullDescripcion() && isNullCodigo();
    }

    public boolean isNullDescripcion() {

        return isNull(descripcion);
    }

    public boolean isNullCodigo() {

        return isNull(codigo);
    }


}