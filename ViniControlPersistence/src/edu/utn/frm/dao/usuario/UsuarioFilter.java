package edu.utn.frm.dao.usuario;

import edu.utn.frm.dao.generic.GenericFilter;

public class UsuarioFilter extends GenericFilter {

    private String usuario;
    private String nombre;
    private String apellido;
    private String dni;

    public UsuarioFilter() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public void clear() {
        usuario = null;
        nombre = null;
        apellido = null;
        dni = null;
    }

    @Override
    public boolean isEmpty() {

        return isNullNombre() && isNullUsuario() && isNullApellido() && isNullDni();
    }

    public boolean isNullUsuario() {

        return isNull(usuario);
    }

    public boolean isNullNombre() {

        return isNull(nombre);
    }

    public boolean isNullApellido() {

        return isNull(apellido);
    }

    public boolean isNullDni() {

        return isNull(dni);
    }
}
