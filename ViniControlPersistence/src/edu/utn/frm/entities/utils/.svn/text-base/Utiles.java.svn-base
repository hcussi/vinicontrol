package edu.utn.frm.entities.utils;

/*
 * Utiles.java
 *
 * Created on 11 de octubre de 2006, 15:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utiles {

    /*
     * Se puede colocar propiedades anidadas en propertis 
     * eje: si quiero el nombre de un pais de una provincia
     * invocaria este  metodo asi invokeMethodGet(provincia, "pais.nombre")
     */
    public static Object invokeMethodGet(Object entity, String propertis) {

        List<String> array = new ArrayList<String>();

        if (propertis.contains(".")) {
            array = Arrays.asList(propertis.replace(".", ",").split(","));
        } else {
            array.add(propertis);
        }

        Object result = null;

        for (String p : array) {

            String nameMethod = "get" + (p.replaceFirst(p.substring(0, 1), p.substring(0, 1).toUpperCase()));

            result = invokeMethod(entity, nameMethod.toString());

            entity = result;

        }

        return result;
    }

    public static Object invokeMethod(Object entity, String nameMethod) {

        if (entity == null || nameMethod == null) {
            return null;
        }

        try {
            Class[] parametertypes = new Class[]{};
            Object[] params = {};
            return entity.getClass().getMethod(nameMethod, parametertypes).invoke(entity, params);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
