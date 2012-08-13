package edu.utn.frm.entities.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Hernan Cussi
 *
 */
public class DateOperations {

    private static final double SECONDS_MILLIS = 1000;
    private static final double MINUTS_MILLIS = 1000 * 60;
    private static final double HOUR_MILLIS = 1000 * 60 * 60;
    private static final double DAY_MILLIS = 1000 * 60 * 60 * 24.0015;
    private static final double WEEK_MILLIS = DAY_MILLIS * 7;
    private static final double MONTH_MILLIS = DAY_MILLIS * 30.43675;
    private static final double YEAR_MILLIS = WEEK_MILLIS * 52.2;
    public static final Time DATE_END = DateOperations.convertTime("24:00");
    public static final Time DATE_INIT = DateOperations.convertTime("00:00");

    private Date d1;
    private Date d2;

    public DateOperations(Date date1, Date date2) {
        super();
        this.d1 = date1;
        this.d2 = date2;
    }

    private int getDateDiff(int calUnit) {

        boolean neg = false;
        if (d1.after(d2)) {
            Date temp = d1;
            d1 = d2;
            d2 = temp;
            neg = true;
        }
        int estimate = (int) getEstDiff(calUnit, d1, d2);

        GregorianCalendar c1 = new GregorianCalendar();
        c1.setTime(d1);
        GregorianCalendar c2 = new GregorianCalendar();
        c2.setTime(d2);

        c1.add(calUnit, (int) estimate - 2);
        for (int i = estimate - 1;; i++) {
            c1.add(calUnit, 1);
            if (c1.after(c2)) {
                return neg ? 1 - i : i - 1;
            }
        }
    }

    private int getEstDiff(int calUnit, Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        switch (calUnit) {
            case Calendar.DAY_OF_WEEK_IN_MONTH:
            case Calendar.DAY_OF_MONTH:
                return (int) (diff / DAY_MILLIS + .5);
            case Calendar.WEEK_OF_YEAR:
                return (int) (diff / WEEK_MILLIS + .5);
            case Calendar.MONTH:
                return (int) (diff / MONTH_MILLIS + .5);
            case Calendar.YEAR:
                return (int) (diff / YEAR_MILLIS + .5);
            case Calendar.HOUR_OF_DAY:
                return (int) (diff / HOUR_MILLIS + .5);
            case Calendar.MINUTE:
                return (int) (diff / MINUTS_MILLIS + .5);
            case Calendar.SECOND:
                return (int) (diff / SECONDS_MILLIS + .5);
            default:
                return 0;
        }
    }

    public int getYearDiff() {
        return getDateDiff(Calendar.YEAR);
    }

    public int getMonthDiff() {
        return getDateDiff(Calendar.MONTH);
    }

    public int getDayDiff() {
        return getDateDiff(Calendar.DATE);
    }

    public int getWeekDiff() {
        return getDateDiff(Calendar.WEEK_OF_YEAR);
    }

    public int getHourDiff() {
        return getDateDiff(Calendar.HOUR_OF_DAY);
    }

    public int getMinutesDiff() {
        return getDateDiff(Calendar.MINUTE);
    }

    public int getSecondsDiff() {
        return getDateDiff(Calendar.SECOND);
    }

    public double getHoursDiffWithDecimal() {
        return getMinutesDiff() / 60d;
    }
    public final static int YEAR = 1;
    public final static int MONTH = 2;
    public final static int DATE = 5;
    public final static int HOUR = 10;
    public final static int MINUTE = 12;
    public final static int SECOND = 13;
    public final static int MILLISECOND = 14;

    /**
     *  YEAR = 1;
     *	MONTH = 2;
     *	DATE = 5;
     *	HOUR = 10;
     *	MINUTE = 12;
     *	SECOND = 13;
     *	MILLISECOND = 14;
     *
     * @param time
     * @param added
     * @return
     */
    public static Time add(Time time, int added, int unit) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(unit, added);
        return new Time(calendar.getTimeInMillis());
    }

    /**
     *  YEAR = 1;
     *	MONTH = 2;
     *	DATE = 5;
     *	HOUR = 10;
     *	MINUTE = 12;
     *	SECOND = 13;
     *	MILLISECOND = 14;
     *
     * @param time
     * @param added la cantidad
     * @param unit alguna de las constantes de Calendar
     * @return un Date con el nuevo tiempo
     */
    public static Date add(Date time, int added, int unit) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(unit, added);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     *  YEAR = 1;
     *	MONTH = 2;
     *	DATE = 5;
     *	HOUR = 10;
     *	MINUTE = 12;
     *	SECOND = 13;
     *	MILLISECOND = 14;
     *
     * @param time
     * @param added
     * @param unit alguna de las constantes de Calendar
     * @return un Date con el nuevo tiempo
     */
    public static java.sql.Date add(java.sql.Date time, int added, int unit) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(unit, added);
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    /**
     *  YEAR = 1;
     *	MONTH = 2;
     *	DATE = 5;
     *	HOUR = 10;
     *	MINUTE = 12;
     *	SECOND = 13;
     *	MILLISECOND = 14;
     *
     * @param time
     * @param added
     * @return
     */
    public static Time substract(Time time, int subs, int unit) {
        return add(time, (-1) * subs, unit);
    }

    /**
     *  YEAR = 1;
     *	MONTH = 2;
     *	DATE = 5;
     *	HOUR = 10;
     *	MINUTE = 12;
     *	SECOND = 13;
     *	MILLISECOND = 14;
     *
     * @param time
     * @param added
     * @return
     */
    public static Date substract(Date time, int subs, int unit) {
        return add(time, (-1) * subs, unit);
    }

    /**
     *  YEAR = 1;
     *	MONTH = 2;
     *	DATE = 5;
     *	HOUR = 10;
     *	MINUTE = 12;
     *	SECOND = 13;
     *	MILLISECOND = 14;
     *
     * @param time
     * @param added
     * @return
     */
    public static java.sql.Date substract(java.sql.Date time, int subs, int unit) {
        return add(time, (-1) * subs, unit);
    }

    /**
     * @param hora
     * @return antes = -1;
     * entre = 0;
     * despues = 1
     *
     */
    public static int entreLimites(Time inicio, Time fin, Time hora) {
        int result = 0;
        if (inicio.compareTo(fin) <= 0) { // inicio < fin
            boolean menorigual = false, mayorigual = false;
            menorigual = (hora.compareTo(fin) <= 0);
            mayorigual = (hora.compareTo(inicio) >= 0);
            if (mayorigual && menorigual) {
                result = 0;
            } else if (mayorigual) {
                result = 1;
            } else //menorigual
            {
                result = -1;
            }
        } else { // entre inicio y fin estan las 0 hs, se toman las 12 hs como limites
            boolean menorque = false, mayorque = false;
            menorque = hora.compareTo(inicio) < 0;
            mayorque = hora.compareTo(fin) > 0;
            if (!(menorque && mayorque)) {
                result = 0;
            } else if (hora.compareTo(DateOperations.convertTime("12:00")) <= 0) // entre el fin y las 12 hs, est� despues
            {
                result = 1;
            } else //entre as 12 hs y el inicio, est� antes
            {
                result = -1;
            }
        }
        return result;
    }

    public static Time convertTime(String strTime) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        try {
            return new Time(timeFormat.parse(strTime).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date convertDate(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            return new Date(dateFormat.parse(strDate).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param date
     * @param time
     * @return Un nuevo Date con el a�o,mes,dia de 'date' y la hora,minutos,segundos de 'time'
     */
    public static Date setTimeInDate(Date date, Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar calTime = Calendar.getInstance();
        if (time != null) {
            calTime.setTime(time);
        } else {
            calTime.setTime(DATE_INIT);
        }

        cal.set(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
        cal.set(Calendar.SECOND, calTime.get(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     *
     * @param date
     * @param day
     * @return un Date con el a�o, el mes y el dia de 'day'
     */
    public static Date setDayInDate(Date date, Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar calDay = Calendar.getInstance();
        if (day != null) {
            calDay.setTime(day);
        } else {
            calDay.setTime(DATE_INIT);
        }

        cal.set(Calendar.YEAR, calDay.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, calDay.get(Calendar.MONTH));
        cal.set(Calendar.DATE, calDay.get(Calendar.DATE));

        return cal.getTime();
    }

    /**
     *
     * @return el dia actual del sitema DD/MM/YYYY 00:00:00
     */
    public static Date getDayDDMMYYYYActual() {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    public static Date getDayDDMMYYYY(Date dia) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dia);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    /**
     *
     * @return el dia actual del sitema DD/MM/YYYY hh:mm:ss
     */
    public static Date getDayActual() {
        Calendar cal = Calendar.getInstance();

        return cal.getTime();
    }

    public static Date getDayActualWhitOutSeconds() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Time getTimeOfDate(Date dateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return new Time(calendar.getTimeInMillis());
    }

    public static String toHHmmString(int minutes) {
        StringBuilder sb = new StringBuilder();
        int horas = minutes / 60;
        int restoMinutos = minutes % 60;
        if (horas < 10) {
            sb.append('0');
        }
        sb.append(horas).append(':');
        if (restoMinutos < 10) {
            sb.append('0');
        }
        sb.append(restoMinutos);

        return sb.toString();
    }

    public static String toYYYYMMDDString(Date date, String separator) {
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dias = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int anio = cal.get(Calendar.YEAR);
        sb.append(anio).append(separator);
        if (mes < 10) {
            sb.append('0');
        }
        sb.append(mes).append(separator);
        if (dias < 10) {
            sb.append('0');
        }
        sb.append(dias);
        return sb.toString();
    }

    public static String toDDMMYYYYString(Date date, String separator) {
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();

        if (date != null) {
            cal.setTime(date);
            int dias = cal.get(Calendar.DAY_OF_MONTH);
            int mes = cal.get(Calendar.MONTH) + 1;
            int anio = cal.get(Calendar.YEAR);
            sb.append(dias).append(separator);
            if (mes < 10) {
                sb.append('0');
            }
            sb.append(mes).append(separator);
            if (anio < 10) {
                sb.append('0');
            }
            sb.append(anio);
            return sb.toString();
        } else {
            return "";
        }
    }

    public static String toTimeString(Date hora) {
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        cal.setTime(hora);
        int horas = cal.get(Calendar.HOUR_OF_DAY);
        int minutos = cal.get(Calendar.MINUTE);

        if (horas < 10) {
            sb.append('0');
        }
        sb.append(horas % 24).append(':');
        if (minutos < 10) {
            sb.append('0');
        }
        sb.append(minutos);

        return sb.toString();
    }

    public static String toHHmmssString(Date hora, String separator) {
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        cal.setTime(hora);
        int horas = cal.get(Calendar.HOUR_OF_DAY);
        int minutos = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);

        if (horas < 10) {
            sb.append('0');
        }
        sb.append(horas % 24).append(separator);
        if (minutos < 10) {
            sb.append('0');
        }
        sb.append(minutos).append(separator);
        if (sec < 10) {
            sb.append('0');
        }
        sb.append(sec);

        return sb.toString();
    }

    /**
     *
     * @param hora
     * @param separator
     * @return
     * retorna la hora y los minutos pero no los segundos
     */
    public static String toHHmmString(Date hora, String separator) {
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        cal.setTime(hora);
        int horas = cal.get(Calendar.HOUR_OF_DAY);
        int minutos = cal.get(Calendar.MINUTE);

        if (horas < 10) {
            sb.append('0');
        }
        sb.append(horas % 24).append(separator);
        if (minutos < 10) {
            sb.append('0');
        }
        sb.append(minutos);

        return sb.toString();
    }

    /**
     *
     * @param date Dia
     * @param separatorD Separador Dia
     * @param separatorH Separador Hora
     * @return
     */
    public static String toDateString(Date date, String separatorD, String separatorH) {
        StringBuilder sb = new StringBuilder();

        sb.append(toDDMMYYYYString(date, separatorD));
        sb.append(" ");
        sb.append(toHHmmssString(date, separatorH));

        return sb.toString();
    }

    /**
     *
     * @param date Dia
     * @param separatorD Separador Dia
     * @param separatorH Separador Hora
     * @return
     * retorna el String DD/MM/YY HH/MM  pero no los segundos
     */
    public static String toDateSinSegundosString(Date date, String separatorD, String separatorH) {
        StringBuilder sb = new StringBuilder();

        sb.append(toDDMMYYYYString(date, separatorD));
        sb.append(" ");
        sb.append(toHHmmString(date, separatorH));

        return sb.toString();
    }

    /**
     *
     * @param operador1
     * @param operador2
     * @return true si el operador1 > operador2
     */
    public static boolean compareMayorTime(Date operador1, Date operador2) {
        return operador1.getTime() > operador2.getTime();
    }

    /**
     *
     * @param operador1 Date
     * @param operador2 Date
     * @return Devuelve '1' si el operador1 > operador2
     *         Devuelve '0' si el operador1 = operador2
     *         Devuelve '-1' si el operador1 < operador2
     */
    public static int compareTimeHour(Date operador1, Date operador2) {

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(operador1);
        cal2.setTime(operador2);

        if (cal1.get(Calendar.HOUR_OF_DAY) > cal2.get(Calendar.HOUR_OF_DAY)) {
            return 1;
        } else if (cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY)) {
            if (cal1.get(Calendar.MINUTE) > cal2.get(Calendar.MINUTE)) {
                return 1;
            } else if (cal1.get(Calendar.MINUTE) == cal2.get(Calendar.MINUTE)) {
                if (cal1.get(Calendar.SECOND) > cal2.get(Calendar.SECOND)) {
                    return 1;
                }else if(cal1.get(Calendar.SECOND) == cal2.get(Calendar.SECOND)){
                    return 0;
                }else{
                    return -1;
                }
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }

    /**
     *
     * @param operador1 Date
     * @param operador2 Date
     * @return true si operador1 es igual a operador 2
     */
    public static boolean compareTimeHourEquals(Date operador1, Date operador2) {

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(operador1);
        cal2.setTime(operador2);

        if (cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY)) {
            if (cal1.get(Calendar.MINUTE) == cal2.get(Calendar.MINUTE)) {
                if (cal1.get(Calendar.SECOND) == cal2.get(Calendar.SECOND)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     *
     * @param operador1
     * @param operador2
     * @return true si el operador1 < operador2
     */
    public static boolean compareMinorTime(Date operador1, Date operador2) {
        return operador1.getTime() < operador2.getTime();
    }

    /**
     *
     * @param operador1
     * @param operador2
     * @return true si el operador1 = operador2
     */
    public static boolean compareEqualsTime(Date operador1, Date operador2) {
        return operador1.getTime() == operador2.getTime();
    }
    public final static int MAYOR = 1;
    public final static int MENOR = 2;
    public final static int IGUAL = 3;
    public final static int MAYOROIGUAL = 4;
    public final static int MENOROIGUAL = 5;
    public final static int IGNORAR_DESDE_MONTH = 1;
    public final static int IGNORAR_DESDE_HORA = 2;
    public final static int IGNORAR_DESDE_MINUTE = 3;
    public final static int IGNORAR_DESDE_SECOND = 4;
    public final static int IGNORAR_DESDE_MILLISECOND = 5;
    public final static int IGNORAR_NADA = 6;

    /**
     *
     * @param estaFecha
     * @param conEstaFecha
     * @param operador
     * @param ignorar_desde
     * @return
     * Compara Time el operador puede ser mayor, mayoroigual, etc.
     * el ignorar_desde coloca en cero desde la opcion que se le pase,
     * por ejemplo si coloco IGNORAR_DESDE_HORA soloca en cero las horas, los minutos
     * y los segundos.
     */
    public static boolean compareTime(Date estaFecha, Date conEstaFecha, int operador, int ignorar_desde) {

        Calendar estaFechaC = Calendar.getInstance();
        estaFechaC.setTime(estaFecha);

        Calendar conEstaFechaC = Calendar.getInstance();
        conEstaFechaC.setTime(conEstaFecha);

        switch (ignorar_desde) {
            case IGNORAR_DESDE_MONTH:
                estaFechaC.set(Calendar.MONTH, 0);
                conEstaFechaC.set(Calendar.MONTH, 0);
                estaFechaC.set(Calendar.HOUR_OF_DAY, 0);
                conEstaFechaC.set(Calendar.HOUR_OF_DAY, 0);
                estaFechaC.set(Calendar.MINUTE, 0);
                conEstaFechaC.set(Calendar.MINUTE, 0);
                estaFechaC.set(Calendar.SECOND, 0);
                conEstaFechaC.set(Calendar.SECOND, 0);
                estaFechaC.set(Calendar.MILLISECOND, 0);
                conEstaFechaC.set(Calendar.MILLISECOND, 0);
                break;
            case IGNORAR_DESDE_HORA:
                estaFechaC.set(Calendar.HOUR_OF_DAY, 0);
                conEstaFechaC.set(Calendar.HOUR_OF_DAY, 0);
                estaFechaC.set(Calendar.MINUTE, 0);
                conEstaFechaC.set(Calendar.MINUTE, 0);
                estaFechaC.set(Calendar.SECOND, 0);
                conEstaFechaC.set(Calendar.SECOND, 0);
                estaFechaC.set(Calendar.MILLISECOND, 0);
                conEstaFechaC.set(Calendar.MILLISECOND, 0);
                break;
            case IGNORAR_DESDE_MINUTE:
                estaFechaC.set(Calendar.MINUTE, 0);
                conEstaFechaC.set(Calendar.MINUTE, 0);
                estaFechaC.set(Calendar.SECOND, 0);
                conEstaFechaC.set(Calendar.SECOND, 0);
                estaFechaC.set(Calendar.MILLISECOND, 0);
                conEstaFechaC.set(Calendar.MILLISECOND, 0);
                break;
            case IGNORAR_DESDE_SECOND:
                estaFechaC.set(Calendar.SECOND, 0);
                conEstaFechaC.set(Calendar.SECOND, 0);
                estaFechaC.set(Calendar.MILLISECOND, 0);
                conEstaFechaC.set(Calendar.MILLISECOND, 0);
                break;
            case IGNORAR_DESDE_MILLISECOND:
                estaFechaC.set(Calendar.MILLISECOND, 0);
                conEstaFechaC.set(Calendar.MILLISECOND, 0);
                break;
            case IGNORAR_NADA:

        }

        switch (operador) {
            case MAYOR:
                return estaFechaC.getTimeInMillis() > conEstaFechaC.getTimeInMillis();
            case MENOR:
                return estaFechaC.getTimeInMillis() < conEstaFechaC.getTimeInMillis();
            case IGUAL:
                return estaFechaC.getTimeInMillis() == conEstaFechaC.getTimeInMillis();
            case MAYOROIGUAL:
                return estaFechaC.getTimeInMillis() >= conEstaFechaC.getTimeInMillis();
            case MENOROIGUAL:
                return estaFechaC.getTimeInMillis() <= conEstaFechaC.getTimeInMillis();
            default:
                return false;
        }
    }

    /*
     * devuelve la cadena que corresponde al dia de la semana
     */
    public static String getDayOfWeekAsString(Date dia) {
        String diaSemana = new String();
        if (dia != null) {
            int diaSem;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dia);
            diaSem = calendar.get(Calendar.DAY_OF_WEEK);
            switch (diaSem) {
                case 1:
                    diaSemana = new String("Dom");
                    break;
                case 2:
                    diaSemana = new String("Lun");
                    break;
                case 3:
                    diaSemana = new String("Mar");
                    break;
                case 4:
                    diaSemana = new String("Mie");
                    break;
                case 5:
                    diaSemana = new String("Jue");
                    break;
                case 6:
                    diaSemana = new String("Vie");
                    break;
                default:
                    diaSemana = new String("Sab");
                    break;
            }
        }
        return diaSemana;
    }

    public static String getFullDayOfWeekAsString(Date dia) {
        String diaSemana = new String();
        if (dia != null) {
            int diaSem;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dia);
            diaSem = calendar.get(Calendar.DAY_OF_WEEK);
            switch (diaSem) {
                case 1:
                    diaSemana = new String("Domingo");
                    break;
                case 2:
                    diaSemana = new String("Lunes");
                    break;
                case 3:
                    diaSemana = new String("Martes");
                    break;
                case 4:
                    diaSemana = new String("Miercoles");
                    break;
                case 5:
                    diaSemana = new String("Jueves");
                    break;
                case 6:
                    diaSemana = new String("Viernes");
                    break;
                default:
                    diaSemana = new String("Sabado");
                    break;
            }
        }
        return diaSemana;
    }

    //devuelve fecha pero con DDMMYYYY 00:00:00
    public static Date cleanHHmmss(Date fecha) {
        if( fecha == null ) return null;
        Calendar dia = Calendar.getInstance();
        dia.setTime(fecha);
        dia.set(Calendar.HOUR_OF_DAY, 0);
        dia.set(Calendar.MINUTE, 0);
        dia.set(Calendar.SECOND, 0);
        dia.set(Calendar.MILLISECOND, 0);
        fecha = dia.getTime();
        return fecha;
    }

    public static Date getMaxTimeOfDate(Date fecha) {
        Calendar dia = Calendar.getInstance();
        dia.setTime(fecha);
        dia.set(Calendar.HOUR_OF_DAY, 23);
        dia.set(Calendar.MINUTE, 59);
        dia.set(Calendar.SECOND, 59);
        dia.set(Calendar.MILLISECOND, 0);
        fecha = dia.getTime();
        return fecha;
    }

    public static Date stringToDate(String dateString) throws ParseException {

        DateFormat df = new SimpleDateFormat("HH:mm:ss");

        return df.parse(dateString);
    }
}
