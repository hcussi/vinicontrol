package edu.utn.frm.dao.producto;

import edu.utn.frm.dao.generic.GenericFilter;

public class FincaFilter extends GenericFilter {

    private String descripcion;
    private String codigo;
    private String direccion;
    private String razonSocial;
    private Integer cantidadHectareas;

    public FincaFilter() {
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
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the cantidadHectareas
     */
    public Integer getCantidadHectareas() {
        return cantidadHectareas;
    }

    /**
     * @param cantidadHectareas the cantidadHectareas to set
     */
    public void setCantidadHectareas(Integer cantidadHectareas) {
        this.cantidadHectareas = cantidadHectareas;
    }

    @Override
    public void clear() {
        cantidadHectareas = null;
        descripcion = null;
        codigo = null;
        direccion = null;
        razonSocial = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullCodigo() && isNullDescripcion() && isNullDireccion() && isNullRazonSocial() &&
                isNullCantidadHectareas();
    }

    public boolean isNullCodigo() {

        return isNull(codigo);
    }

    public boolean isNullDireccion() {

        return isNull(direccion);
    }

    public boolean isNullRazonSocial() {

        return isNull(razonSocial);
    }

    public boolean isNullCantidadHectareas() {

        return isNull(cantidadHectareas);
    }

    public boolean isNullDescripcion() {

        return isNull(descripcion);
    }
}
