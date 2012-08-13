package edu.utn.frm.dao.producto;

import edu.utn.frm.dao.generic.GenericFilter;

public class CamionFilter extends GenericFilter {

    private Double capacidadKilos;
    private String descripcion;
    private String marca;
    private String patente;
    private Integer modelo;

    public CamionFilter() {
    }

    /**
     * @return the capacidadKilos
     */
    public Double getCapacidadKilos() {
        return capacidadKilos;
    }

    /**
     * @param capacidadKilos the capacidadKilos to set
     */
    public void setCapacidadKilos(Double capacidadKilos) {
        this.capacidadKilos = capacidadKilos;
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
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the patente
     */
    public String getPatente() {
        return patente;
    }

    /**
     * @param patente the patente to set
     */
    public void setPatente(String patente) {
        this.patente = patente;
    }

    /**
     * @return the modelo
     */
    public Integer getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    @Override
    public void clear() {
        capacidadKilos = null;
        descripcion = null;
        marca = null;
        patente = null;
        modelo = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullCapacidadKilos() && isNullDescripcion() && isNullMarca() &&
                isNullPatente() && isNullModelo();
    }

    public boolean isNullCapacidadKilos() {

        return isNull(capacidadKilos);
    }

    public boolean isNullDescripcion() {

        return isNull(descripcion);
    }

    public boolean isNullMarca() {

        return isNull(marca);
    }

    public boolean isNullPatente() {

        return isNull(patente);
    }

    public boolean isNullModelo() {

        return isNull(modelo);
    }
}