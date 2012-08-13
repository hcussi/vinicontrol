package edu.utn.frm.dao.producto;

import edu.utn.frm.dao.generic.GenericFilter;

public class VariedadFilter extends GenericFilter {

    private String codigo;
    private String descripcion;
    private String tinte;

    public VariedadFilter() {
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
     * @return the tinte
     */
    public String getTinte() {
        return tinte;
    }

    /**
     * @param tinte the tinte to set
     */
    public void setTinte(String tinte) {
        this.tinte = tinte;
    }

    @Override
    public void clear() {
        descripcion = null;
        codigo = null;
        tinte = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullCodigo() && isNullDescripcion() && isNullTinte();
    }

    public boolean isNullCodigo() {

        return isNull(codigo);
    }

    public boolean isNullDescripcion() {

        return isNull(descripcion);
    }

    public boolean isNullTinte() {

        return isNull(tinte);
    }
}
