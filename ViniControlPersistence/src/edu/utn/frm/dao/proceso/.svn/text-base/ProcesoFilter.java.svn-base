package edu.utn.frm.dao.proceso;

import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.entities.proceso.EstadoProceso;
import edu.utn.frm.entities.producto.MostoEnTanque;
import edu.utn.frm.entities.tanque.Tanque;
import edu.utn.frm.entities.utils.DateOperations;
import java.util.Date;

public class ProcesoFilter extends GenericFilter {

    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fecha;
    private EstadoProceso estadoProceso;
    private Tanque tanque;
    private MostoEnTanque producto;
    private Boolean isTanqueNotNull;

    public ProcesoFilter() {

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoProceso getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(EstadoProceso estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public Date getFecha() {
        return DateOperations.cleanHHmmss(fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaFin() {
        return DateOperations.add(DateOperations.cleanHHmmss(fechaFin), 1, DateOperations.DATE);
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return DateOperations.cleanHHmmss(fechaInicio);
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Tanque getTanque() {
        return tanque;
    }

    public void setTanque(Tanque tanque) {
        this.tanque = tanque;
    }

    public MostoEnTanque getProducto() {
        return producto;
    }

    public void setProducto(MostoEnTanque producto) {
        this.producto = producto;
    }

    public Boolean getIsTanqueNotNull() {
        return isTanqueNotNull;
    }

    public void setIsTanqueNotNull(Boolean isTanqueNull) {
        this.isTanqueNotNull = isTanqueNull;
    }

    @Override
    public void clear() {
        descripcion = null;
        fechaInicio = null;
        fechaFin = null;
        fecha = null;
        estadoProceso = null;
        tanque = null;
        producto = null;
        isTanqueNotNull = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullFechaFin() && isNullFecha() && isNullDescripcion() && isNullFechaInicio() &&
                isNullEstadoProceso() && isNullTanque() && isNullProducto() && isNullTanqueNotNull();
    }

    public boolean isNullFechaFin() {

        return isNull(fechaFin);
    }

    public boolean isNullFechaInicio() {

        return isNull(fechaInicio);
    }

    public boolean isNullFecha() {

        return isNull(fecha);
    }

    public boolean isNullDescripcion() {

        return isNull(descripcion);
    }


    public boolean isNullEstadoProceso() {

        return isNull(estadoProceso);
    }

    public boolean isNullTanque() {

        return isNull(tanque);
    }

    public boolean isNullProducto() {

        return isNull(producto);
    }

    public boolean isNullTanqueNotNull() {

        return isNull(isTanqueNotNull);
    }
}