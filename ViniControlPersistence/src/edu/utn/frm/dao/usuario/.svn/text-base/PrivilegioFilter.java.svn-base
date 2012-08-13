package edu.utn.frm.dao.usuario;

import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.entities.usuario.TipoPrivilegio;

public class PrivilegioFilter extends GenericFilter {

    private TipoPrivilegio tipoPrivilegio;
    private String descripcion;

    public PrivilegioFilter() {
    }

    public TipoPrivilegio getTipoPrivilegio() {
        return tipoPrivilegio;
    }

    public void setTipoPrivilegio(TipoPrivilegio tipoPrivilegio) {
        this.tipoPrivilegio = tipoPrivilegio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public void clear() {
        tipoPrivilegio = null;
        descripcion = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullDescripcion() && isNullTipoPrivilegio();
    }

    public boolean isNullTipoPrivilegio() {

        return isNull(tipoPrivilegio);
    }

    public boolean isNullDescripcion() {

        return isNull(descripcion);
    }

}