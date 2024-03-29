package edu.utn.frm.ui.utils;

/*
 * Utiles.java
 *
 * Created on 11 de octubre de 2006, 15:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class Utiles {

    public static void centrarFormulario(Component frm) {
        Dimension screeenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frm.getSize();

        if (frameSize.height > screeenSize.height) {
            frameSize.height = screeenSize.height;
        }

        if (frameSize.width > screeenSize.width) {
            frameSize.width = screeenSize.width;
        }

        frm.setLocation((screeenSize.width - frameSize.width) / 2, (screeenSize.height - frameSize.height) / 2);

    }

    public static boolean validarHora(String hora) {
        boolean res = false;
        Date dt2 = new Date();
        SimpleDateFormat sdh = new SimpleDateFormat("HH:mm");
        sdh.setLenient(false);
        //if(!fecha.equals("")){
        try {


            dt2 = sdh.parse(hora);
            res = true;
        } catch (ParseException e) {
            res = false;
        } catch (IllegalArgumentException e) {
            res = false;
        }

        return res;

    }

    /*
     * el argumento hora puede ser de esta manera ej: "10:30"
     * y devuelve el Date con esa hora.
     */
    public static Date parseHora(String hora) {

        SimpleDateFormat sdh = new SimpleDateFormat("HH:mm");
        sdh.setLenient(false);

        try {
            return sdh.parse(hora);
        } catch (ParseException e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }

    }

    public static Date getDDMMYYYY() {
        return getDDMMYYYY(new Date());
    }

    public static Date getDDMMYYYY(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();
        return date;
    }

    public static Date sigiente_N_Dia(Date dia, int cantDias) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dia);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + cantDias);
        return cal.getTime();

    }

    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public static Date unificarFechaYHora(Date fecha, Date hora) {

        Calendar calendarHora = Calendar.getInstance();
        calendarHora.setTime(hora);

        Calendar calendarFecha = Calendar.getInstance();
        calendarFecha.setTime(fecha);

        calendarFecha.set(Calendar.HOUR_OF_DAY, calendarHora.get(Calendar.HOUR_OF_DAY));
        calendarFecha.set(Calendar.MINUTE, calendarHora.get(Calendar.MINUTE));
        calendarFecha.set(Calendar.SECOND, 0);
        calendarFecha.set(Calendar.MILLISECOND, 0);

        return calendarFecha.getTime();

    }

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
    public static String error = "";
    private static int dniStc;
    private static int xyStc;
    private static int digitoStc;

    public static boolean verificarCuit(String cuit) {
        error = "";
        String xyStr, dniStr, digitoStr;
        int digitoTmp;
        int n = cuit.lastIndexOf("-");
        xyStr = cuit.substring(0, 2);
        dniStr = cuit.substring(cuit.indexOf("-") + 1, n);
        digitoStr = cuit.substring(n + 1, n + 2);
        if (xyStr.length() != 2 || dniStr.length() > 8 || digitoStr.length() != 1) {
            return false;
        }
        try {
            xyStc = Integer.parseInt(xyStr);
            dniStc = Integer.parseInt(dniStr);
            digitoTmp = Integer.parseInt(digitoStr);
        } catch (NumberFormatException e) {
            return false;
        }
        if (xyStc != 20 && xyStc != 23 && xyStc != 24 && xyStc != 27 && xyStc != 30 && xyStc != 33 && xyStc != 34) {
            error = "Los primeros dos digitos '" + xyStc + "' del CUIT son invalidos";
            return false;
        }
        calcular();
        if (digitoStc == digitoTmp && xyStc == Integer.parseInt(xyStr)) {
            return true;
        } else {


            return false;
        }

    }

    private static void calcular() {
        long tmp1, tmp2;
        long acum = 0;
        int n = 2;
        tmp1 = xyStc * 100000000L + dniStc;
        for (int i = 0; i < 10; i++) {
            tmp2 = tmp1 / 10;
            acum += (tmp1 - tmp2 * 10L) * n;
            tmp1 = tmp2;
            if (n < 7) {
                n++;
            } else {
                n = 2;
            }
        }
        n = (int) (11 - acum % 11);
        if (n == 10) {
            if (xyStc == 20 || xyStc == 27 || xyStc == 24) {
                xyStc = 23;
            } else {
                xyStc = 33; 	    /*No es necesario hacer la llamada recursiva a calcular(), 	  	 *se puede poner el digito en 9 si el prefijo original era 	  	 *23 o 33 o poner el dijito en 4 si el prefijo era 27*/
            }
            calcular();
        } else {
            if (n == 11) {
                digitoStc = 0;
            } else {
                digitoStc = n;
            }
        }
    }

    /**
     * Este metodo devuelve true si la direccion ip pasada como parametro
     * es valida o false en caso contrario
     * @param ip La dirección ip a validar
     * @return booleanreturn true si es una dirección válida, false en otro caso.
     */
    public static boolean verificarIp(String ip) {

        //En java un StringTokenizer es una version mejorada del String
        //  q permite darle formato a las cadenas. Al llamar el constructor
        //  y pasar por ej. la direccion="255.192.168.1" y el String punto "." lo q
        //  hago es separar la ip y dejar a st={"255","192","168","1"}
        StringTokenizer st = new StringTokenizer(ip, ".");

        //ahora verifico q si el tamaño de st no es 4, osea, los 4 numeros
        //  q debe tener toda direcicon ip, digo q esta mal la direccion ip.
        if (st.countTokens() != 4) {
            return false;
        }

        //ahora sigo verificando, y le digo a st con el metodo nextTokens()
        //  q me de uno por uno sus elementos, es decir, los 4 numeros de las
        //  de la direcicon ip. Luego verifo q el numero tenga solo 1 o 3 digitos y
        //  q este entre 0 y 255. Y listo, eso es todo.
        while (st.hasMoreTokens()) {
            String nro = st.nextToken();
            if ((nro.length() > 3) || (nro.length() < 1)) {
                return false;
            }
            int nroInt = 0;
            try {
                nroInt = Integer.parseInt(nro);
            } catch (NumberFormatException s) {
                return false;
            }
            if ((nroInt < 0) || (nroInt > 255)) {
                return false;
            }
        }
        return true;
    }
}
