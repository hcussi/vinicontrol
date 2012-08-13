/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.operacion;

import edu.utn.frm.action.exception.ComunicacionException;
import edu.utn.frm.entities.tanque.EstadoTanque;
import edu.utn.frm.entities.tanque.Tanque;

/**
 *
 * @author Proyecto
 */
public interface IComandoTanque {

    void execute() throws ComunicacionException;
    void setTanque(Tanque tanque, EstadoTanque estadoTanque);
}
