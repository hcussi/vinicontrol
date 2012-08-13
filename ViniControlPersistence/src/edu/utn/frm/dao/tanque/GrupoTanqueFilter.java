package edu.utn.frm.dao.tanque;

import edu.utn.frm.dao.generic.GenericFilter;

public class GrupoTanqueFilter extends GenericFilter {

    private String codigo;
    private String nombre;

    public GrupoTanqueFilter() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void clear() {
        codigo = null;
        nombre = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullNombre() && isNullCodigo();
    }

    public boolean isNullCodigo() {

        return isNull(codigo);
    }

    public boolean isNullNombre() {

        return isNull(nombre);
    }
}
