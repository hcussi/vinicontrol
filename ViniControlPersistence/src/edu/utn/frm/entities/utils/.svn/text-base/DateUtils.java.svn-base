package edu.utn.frm.entities.utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Proyecto
 */
public class DateUtils {
    
    public DateUtils() {
        
    }
    
    /**
     *
     * @return Fecha Actual
     */
    public static Date getFechaActual(){
        return new Date();
    }
    
    /**
     *
     * @return Dia Actual
     */
    public static int getDiaActual(){
        Calendar fecha = Calendar.getInstance();
        return fecha.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     *
     * @return Dia de una fecha
     */
    public static int getDia(Date fecha){
        Calendar calendario = Calendar.getInstance();
        
        calendario.setTime(fecha);
        
        return calendario.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     *
     * @return Mes Actual
     */
    public static long getMesActual(){
        Calendar fecha = Calendar.getInstance();
        return fecha.get(Calendar.MONTH);
    }
    
    /**
     *
     * @return Mes de una fecha
     */
    public static int getMes(Date fecha){
        Calendar calendario = Calendar.getInstance();
        
        calendario.setTime(fecha);
        
        return calendario.get(Calendar.MONTH);
    }
    
    /**
     *
     * @return A�o Actual
     */
    public static int getAnioActual(){
        Calendar fecha = Calendar.getInstance();
        return fecha.get(Calendar.YEAR);
    }
    
    /**
     *
     * @return Anio de una fecha
     */
    public static int getAnio(Date fecha){
        Calendar calendario = Calendar.getInstance();
        
        calendario.setTime(fecha);
        
        return calendario.get(Calendar.YEAR);
    }
    
    /**
     *
     * setea el field(anio,mes,dia,hora,min,seg) a esta fecha
     */
    public static Date setField(Date fecha,int field,int value){
        Calendar calendario = Calendar.getInstance();
        
        calendario.setTime(fecha);
        calendario.set(field,value);
        
        return calendario.getTime();
    }
    
    /**
     *
     * devuelve el field(anio,mes,dia,hora,min,seg) de esta fecha
     */
    public static int getField(Date fecha,int field){
        Calendar calendario = Calendar.getInstance();
        
        calendario.setTime(fecha);
        
        return calendario.get(field);
    }
    
    /**
     *
     * @param c Calendario
     * @return Cantidad de dias desde q empezo el a�o
     */
    public static long getCantDias(Date d){
        Calendar fecha = Calendar.getInstance();
        
        fecha.setTime(d);
        
        return fecha.get(Calendar.DAY_OF_YEAR);
    }
    
    /**
     *
     * @param Fecha inicio
     * @param Fecha fin
     * @return la Diferencia de Fechas
     */
    public static long getDifFechas(Date i,Date f){
        Calendar inicio,fin;
        
        inicio=Calendar.getInstance();
        fin=Calendar.getInstance();
        
        long diasInicio= getCantDias(i);
        long diasFin= getCantDias(f);
        
        return (fin.get(Calendar.YEAR)-inicio.get(Calendar.YEAR))*365 + diasFin - diasInicio;
    }
    
    public static boolean isEntre(Date entrada, Date salida, Date fecha){
        return getCantDias(entrada)<= getCantDias(fecha)&&getCantDias(salida)>= getCantDias(fecha);
        
    }
    
    public static boolean isMenor(Date d1,Date d2) {
        return getDifFechas(d1,d2)< 0;
    }
    
    /**
     * @return Descuenta un dia al calendario
     *
     */
    public Date quitDia(Date fecha){
        
        Calendar calendario=Calendar.getInstance();
        
        calendario.setTime(fecha);
        
        calendario.add(Calendar.DAY_OF_MONTH,-1);
        
        return calendario.getTime();
        
    } // end quitDia()
    
    /**
     * @return Le suma un dia al calendario
     *
     */
    public Date addDia(Date fecha) {
        
        Calendar calendario=Calendar.getInstance();
        
        calendario.setTime(fecha);
        
        calendario.add(Calendar.DAY_OF_MONTH,1);
        
        return  calendario.getTime();
        
    } // end addDia()
    
    /**
     * @return Le suma n dias al calendario
     *
     */
    public Date addDias(Date fecha,int n) {
        
        Calendar calendario=Calendar.getInstance();
        
        calendario.setTime(fecha);
        
        calendario.add(Calendar.DAY_OF_MONTH,n);
        
        return calendario.getTime();
        
    } // end addDias()

    public static Date getFecha(int anio, int mes) {
        
        Calendar calendario=Calendar.getInstance();
        
        calendario.set(Calendar.YEAR,anio);
        calendario.set(Calendar.MONTH,mes);
        calendario.set(Calendar.DATE,0);
        calendario.set(Calendar.DAY_OF_MONTH,0);
        calendario.set(Calendar.DAY_OF_WEEK,0);
        calendario.set(Calendar.DAY_OF_WEEK_IN_MONTH,0);
        calendario.set(Calendar.HOUR_OF_DAY,0);
        calendario.set(Calendar.MINUTE,0);
        calendario.set(Calendar.SECOND,0);
        
        return calendario.getTime();
    }
    
}