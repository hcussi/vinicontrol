/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion;

import java.nio.ByteBuffer;

/**
 *
 * @author Proyecto
 */
public class PaqueteComunicacion {

    private byte[] datos;
    private ByteBuffer configDataBuffer;
    public PaqueteComunicacion(int size){
        datos = new byte[size];
        configDataBuffer = ByteBuffer.wrap(datos);
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }

    public void put(int dato){
        configDataBuffer.putInt(dato);
    }

    public void put(byte dato){
        configDataBuffer.put(dato);
    }

    public void put(byte[] dato){
        configDataBuffer.put(dato);
    }

    public byte[] getDatos(int i, int f){

        if( i < 0 || f < 0 ){
            throw new RuntimeException("Los indices no pueden ser negativos");
        }else if( i > f ){
            throw new RuntimeException("El primer indice debe ser menor o igual al segundo indice");
        }else if( i > datos.length || f > datos.length ){
            throw new RuntimeException("Indices fuera de rango del arreglo de bytes");
        }else if( i == f ){
            byte[] newDatos = new byte[1];
            newDatos[0] = datos[i];
            return newDatos;
        }
        byte[] newDatos = new byte[(f-i)];

        for (int k = 0, j = i ; j < f ; j++, k++) {
            newDatos[k] = datos[j];
        }

        return newDatos;
    }

    public void put(char[] idTanqueChar) {

        for (int i = 0; i < idTanqueChar.length; i++) {
            char c = idTanqueChar[i];
            put ( (byte)c );
        }
    }

    public Integer getDatosInt(byte[] numero){
    StringBuilder builder = new StringBuilder();
    builder.append("0x");
        for (int i = 0; i < numero.length; i++) {
            builder.append(String.format("%X", numero[i]));

        }
        return Integer.decode(builder.toString());
    }

    public String getDatosString(byte[] idTanque){
        StringBuilder builder = new StringBuilder();

            for (int i = 0; i < idTanque.length; i++) {
                byte b = idTanque[i];
                char c = (char) b;
                builder.append( String.valueOf(c) );
            }
        return builder.toString();
    }

    public Integer getDatosInt(String datos,int i, int f){
        Integer numero = null;

        try{
            numero = new Integer( datos.substring(i, f).trim() );
        }catch(NumberFormatException ex){
            ex.printStackTrace();
        }catch(StringIndexOutOfBoundsException ex){
            ex.printStackTrace();
        }

        return numero;
    }
}