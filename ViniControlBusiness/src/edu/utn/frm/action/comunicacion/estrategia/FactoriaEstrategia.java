/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion.estrategia;

import edu.utn.frm.entities.proceso.Proceso;

/**
 *
 * @author Proyecto
 */
public class FactoriaEstrategia {

    public static final int ESTRATEGIA_TCP = 0;
    public static final int ESTRATEGIA_RS232 = 1;
    public static final int ESTRATEGIA_UDP = 2;
    private static FactoriaEstrategia instance;

    private FactoriaEstrategia(){

    }

    public static FactoriaEstrategia getInstance(){
        if( instance == null ){
            instance = new FactoriaEstrategia();
        }
        return instance;
    }

    public IEstrategiaComunicacion getEstrategia(int estrategia, Proceso proceso){

        switch(estrategia){
            case ESTRATEGIA_TCP: return new EstrategiaComunicacionTCP(proceso);
            case ESTRATEGIA_RS232: return new EstrategiaComunicacionSerie(proceso);
            case ESTRATEGIA_UDP: return new EstrategiaComunicacionUDP(proceso);
        }
        return null;
    }

}