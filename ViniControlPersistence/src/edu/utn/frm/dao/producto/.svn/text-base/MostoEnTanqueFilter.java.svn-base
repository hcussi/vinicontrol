package edu.utn.frm.dao.producto;

import edu.utn.frm.dao.generic.GenericFilter;
import java.util.Date;

public class MostoEnTanqueFilter extends GenericFilter {

    private String descripcion;
    private String codigo;
    private Date fechaIngreso;
    private Date fechaEgreso;

    public MostoEnTanqueFilter() {
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

    /**
     * @return the fechaIngreso
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the fechaEgreso
     */
    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    /**
     * @param fechaEgreso the fechaEgreso to set
     */
    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    @Override
    public void clear() {
        descripcion = null;
        codigo = null;
        fechaIngreso = null;
        fechaEgreso = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullDescripcion() && isNullCodigo() &&
                isNullFechaIngreso() && isNullFechaEgreso();
    }

    public boolean isNullDescripcion() {

        return isNull(descripcion);
    }

    public boolean isNullCodigo() {

        return isNull(codigo);
    }

    public boolean isNullFechaIngreso() {

        return isNull(fechaIngreso);
    }

    public boolean isNullFechaEgreso() {

        return isNull(fechaEgreso);
    }
}