package edu.utn.frm.dao.sistema;

import edu.utn.frm.dao.generic.GenericFilter;

public class ConfiguracionSistemaFilter extends GenericFilter {

    private String empresa;
    private String direccion;
    private String cuit;

    public ConfiguracionSistemaFilter() {
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public void clear() {
        empresa = null;
        cuit = null;
        direccion = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullCuit() && isNullEmpresa() && isNullDireccion();
    }

    public boolean isNullEmpresa() {

        return isNull(empresa);
    }

    public boolean isNullCuit() {

        return isNull(cuit);
    }

    public boolean isNullDireccion() {

        return isNull(direccion);
    }
}
