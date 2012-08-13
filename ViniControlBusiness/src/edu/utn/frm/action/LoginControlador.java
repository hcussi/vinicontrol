/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action;

import edu.utn.frm.entities.usuario.Privilegio;
import edu.utn.frm.entities.usuario.Rol;
import edu.utn.frm.entities.usuario.Usuario;

/**
 *
 * @author Proyecto
 */
public class LoginControlador {

    private static LoginControlador instance;
    private static final String ROOT = "superusuario";
    private Usuario usuario;

    private boolean accesoConfiguracionSistema = false;
    private boolean accesoTanque = false;
    private boolean accesoReglaControl = false;
    private boolean accesoProducto = false;
    private boolean accesoConexion = false;
    private boolean accesoUsuario = false;

    private LoginControlador(){
        setDefaultValues();
    }

    public static LoginControlador getInstance(){
        if( instance == null ){
            instance = new LoginControlador();
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isSuperUsuario(){

        return usuario.getUsuario().equals(ROOT);
    }

    public boolean isAccesoReglaControl(){
        return accesoReglaControl;
    }

    public boolean isAccesoConexion(){
        return accesoConexion;
    }

    public boolean isAccesoProducto(){
        return accesoProducto;
    }

    public boolean isAccesoTanque(){
        return accesoTanque;
    }

    public boolean isAccesoUsuario(){
        return accesoUsuario;
    }

    public boolean isAccesoConfiguracionSistema(){
        return accesoConfiguracionSistema;
    }

    public void validarAcceso(){
        setDefaultValues();
        if( !isSuperUsuario() ){
            for( Rol rol : usuario.getRoles() ){
                for( Privilegio privilegio : rol.getPrivilegios() ){
                    switch( privilegio.getTipoPrivilegio() ){
                        case CONEXION:
                            accesoConexion = true;
                            break;
                        case PRODUCTO:
                            accesoProducto = true;
                            break;
                        case REGLA_CONTROL:
                            accesoReglaControl = true;
                            break;
                        case TANQUE:
                            accesoTanque = true;
                            break;
                        case USUARIO:
                            accesoUsuario = true;
                            break;
                        case SISTEMA:
                            accesoConfiguracionSistema = true;
                            break;
                    }
                }
            }
        }else{
            accesoConexion = accesoProducto = accesoReglaControl = accesoTanque = 
                    accesoUsuario = accesoConfiguracionSistema =true;
        }
    }

    private void setDefaultValues() {
        accesoConfiguracionSistema = false;
        accesoTanque = false;
        accesoReglaControl = false;
        accesoProducto = false;
        accesoConexion = false;
        accesoUsuario = false;
    }
}