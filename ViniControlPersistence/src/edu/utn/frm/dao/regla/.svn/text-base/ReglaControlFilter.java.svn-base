package edu.utn.frm.dao.regla;

import edu.utn.frm.dao.generic.GenericFilter;

public class ReglaControlFilter extends GenericFilter {

    private String codigo;
    private String descripcion;

    public ReglaControlFilter() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public void clear() {
        codigo = null;
        descripcion = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullDescripcion() && isNullCodigo();
    }

    public boolean isNullCodigo() {

        return isNull(codigo);
    }

    public boolean isNullDescripcion() {

        return isNull(descripcion);
    }

}