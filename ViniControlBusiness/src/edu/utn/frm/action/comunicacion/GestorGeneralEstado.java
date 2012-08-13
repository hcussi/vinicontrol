/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.action.comunicacion;

import edu.utn.frm.action.GestorConfiguracionSistema;
import edu.utn.frm.action.comunicacion.eventos.AgregarProcesoEvent;
import edu.utn.frm.action.comunicacion.eventos.EventDispatcher;
import edu.utn.frm.action.comunicacion.eventos.NuevaAlarmaEvent;
import edu.utn.frm.action.comunicacion.eventos.NuevoComandoEvent;
import edu.utn.frm.action.comunicacion.eventos.NuevoEstadoTanqueEvent;
import edu.utn.frm.action.comunicacion.eventos.RemoverProcesoEvent;
import edu.utn.frm.action.comunicacion.eventos.ViniControlEvent;
import edu.utn.frm.action.comunicacion.eventos.ViniControlListener;
import edu.utn.frm.action.prediccion.GestorPrediccionTendencia;
import edu.utn.frm.action.exception.ComunicacionException;
import edu.utn.frm.action.exception.GestorGeneralEstadoException;
import edu.utn.frm.action.exception.PrediccionException;
import edu.utn.frm.action.log.LogManager;
import edu.utn.frm.action.operacion.FactoriaComandos;
import edu.utn.frm.action.operacion.IComandoTanque;
import edu.utn.frm.dao.demanda.estado.EstadoPrediccionTendenciaDao;
import edu.utn.frm.dao.generic.PersistException;
import edu.utn.frm.dao.proceso.ProcesoDao;
import edu.utn.frm.dao.proceso.ProcesoFilter;
import edu.utn.frm.entities.alarma.AlarmaNivelAzucar;
import edu.utn.frm.entities.alarma.AlarmaNivelCapacidad;
import edu.utn.frm.entities.alarma.AlarmaRemontaje;
import edu.utn.frm.entities.alarma.AlarmaTemperatura;
import edu.utn.frm.entities.comunicacion.ConexionIP;
import edu.utn.frm.entities.comunicacion.ConexionSerie;
import edu.utn.frm.entities.comunicacion.ConfiguracionConexion;
import edu.utn.frm.entities.proceso.EstadoProceso;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.producto.MostoEnTanque;
import edu.utn.frm.entities.regla.ReglaControl;
import edu.utn.frm.entities.regla.ReglaControlRemontaje;
import edu.utn.frm.entities.sistema.ConfiguracionSistema;
import edu.utn.frm.entities.tanque.EstadoTanque;
import edu.utn.frm.entities.tanque.Tanque;
import edu.utn.frm.entities.tanque.demanda.EstadoPrediccionTendencia;
import edu.utn.frm.entities.utils.DateOperations;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Timer;
import org.apache.log4j.Logger;

/**
 * Esta clase implementa el patrón singleton, se encarga de cargar en memoria todos los procesos
 * que están en el estado iniciado en la base de datos. Después de iniciar cada proceso y asignarle
 * los recursos se queda a la espera de los eventos que recibe de los gestores de comunicación.
 * Se encarga de verificar las alarmas y crear los eventos de nuevas alarmas y de verificar el remontaje
 * de cada tanque en particular asignado a cada proceso.
 * @author Proyecto
 */
public class GestorGeneralEstado extends EventDispatcher implements ViniControlListener {

    private static Logger logger = Logger.getLogger(GestorGeneralEstado.class);
    private ProcesoDao procesoDao = new ProcesoDao();
    private EstadoPrediccionTendenciaDao estadoPrediccionTendenciaDao = new EstadoPrediccionTendenciaDao();
    private static GestorGeneralEstado instance;
    private List<Proceso> procesos;
    private List<GestorComunicacionRecibir> receivers;
    private Timer timerRemontaje;
    private Timer timerProceso;
    private boolean isIniciado = false;
    private LogManager logManager = LogManager.getInstance();
    private GestorPrediccionTendencia gestorPrediccionTendencia;
    private static int MINUTOS_PREDECIR = 60;
    private static int VERIFICAR_DETENER = 60;
    private static int VERIFICAR_REMONTAJE = 30;
    private ConfiguracionSistema cs;

    /**
     * Crea una instancia de la clase GestorGeneralEstado
     */
    private GestorGeneralEstado() {
        try {
            gestorPrediccionTendencia = new GestorPrediccionTendencia();
            cs = GestorConfiguracionSistema.getInstance().getConfiguracionSistema();
            if (cs != null) {
                if (cs.getMinutosPredecir() != null) {
                    MINUTOS_PREDECIR = cs.getMinutosPredecir();
                }
                if (cs.getVerificarProcesosDetener() != null) {
                    VERIFICAR_DETENER = cs.getVerificarProcesosDetener();
                }
                if (cs.getVerificarRemontaje() != null) {
                    VERIFICAR_REMONTAJE = cs.getVerificarRemontaje();
                }
            }
            iniciar();
        } catch (GestorGeneralEstadoException ex) {
            logger.error("Ha fallado al tratar de iniciar los procesos");
            logManager.addLog("Ha fallado al tratar de iniciar los procesos", null);
            logger.error(ex.getMessage());
            logManager.addLog(ex.getMessage(), null);
        }
    }

    /**
     * 
     * @return La instancia de la clase GestorGeneralEstado
     */
    public static GestorGeneralEstado getInstance() {
        if (instance == null) {
            instance = new GestorGeneralEstado();
        }
        return instance;
    }

    /**
     * Recupera los procesos que deberían estar iniciados y los inicia
     * @throws GestorGeneralEstadoException ha fallado al tratan de iniciar los procesos
     */
    public void iniciar() throws GestorGeneralEstadoException {
        logger.info("[GestorGeneralEstado.iniciar]");
        logManager.addLog("[GestorGeneralEstado.iniciar]", null);

        if (isIniciado) {
            logger.info("Ya esta iniciado, se va a intentar detener los procesos");
            logManager.addLog("Ya esta iniciado, se va a intentar detener los procesos", null);
            detener();
        }
        receivers = new ArrayList<GestorComunicacionRecibir>();
        procesos = cargarProcesos();
        for (Proceso proceso : procesos) {
            try {
                //Deberia iniciar en otro hilo
                if (proceso.getTanque() != null) {
                    iniciarProceso(proceso);
                }
            } catch (GestorGeneralEstadoException ex) {
                logger.error(ex.getMessage());
            }
        }
        logger.info("Iniciando el timer para controlar los remontajes");
        logManager.addLog("Iniciando el timer para controlar los remontajes", null);
        //Cada 30 segundos verificar el remontaje
        timerRemontaje = new Timer(VERIFICAR_REMONTAJE * 1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                verificarRemontaje();
            }
        });
        timerRemontaje.start();
        isIniciado = true;
        logger.info("Se han iniciado los procesos");
        logManager.addLog("Se han iniciado los procesos", null);

        //Cada 60 segundos verifica los procesos a detener
        timerProceso = new Timer(VERIFICAR_DETENER * 1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                verificarProcesosADetener();
            }
        });
        timerProceso.start();
    }

    /**
     * Cada 60 segundos se verifica si un proceso deberia detenerse de acuerdo a la
     * fecha actual con la fecha y hora de finalización del proceso
     */
    private void verificarProcesosADetener() {
        Date fechaHoy = new Date();

        for (Proceso proceso : this.procesos) {
            Date fechaFinProceso = DateOperations.setTimeInDate(proceso.getFechaFin(), proceso.getHoraFin());
            if (DateOperations.compareMayorTime(fechaHoy, fechaFinProceso)) {
                try {
                    liberarRecursos(proceso, EstadoProceso.TERMINADO);
                    try {
                        procesoDao.update(proceso);
                        proceso.getTanque().getEstadoPrediccionTendencia().setLastTendencia(0);
                        estadoPrediccionTendenciaDao.update( proceso.getTanque().getEstadoPrediccionTendencia() );
                    } catch (PersistException ex) {
                        logger.error("Fallo al actualizar el proceso");
                        logManager.addLog("Fallo al actualizar el proceso",proceso);
                        logger.error("No se pudo actualizar el estado del proceso a terminado");
                        logManager.addLog("No se pudo actualizar el estado del proceso a terminado",proceso);
                        logger.error(ex.getMessage());
                        logManager.addLog(ex.getMessage(),proceso);
                    }
                } catch (GestorGeneralEstadoException ex) {
                    logger.error("Ha fallado al intentar detener el proceso");
                    logger.error(ex.getMessage());
                    logManager.addLog("Ha fallado al intentar detener el proceso",proceso);
                    logManager.addLog(ex.getMessage(),proceso);
                }
            }
        }
    }

    public void detener() throws GestorGeneralEstadoException {
        logger.info("[GestorGeneralEstado.detener]");
        logManager.addLog("[GestorGeneralEstado.detener]", null);
        for (GestorComunicacionRecibir gcr : receivers) {
            gcr.detenerConexion();
            gcr.removeEventListener(this);
        }
        for (Proceso proceso : procesos) {
            liberarRecursos(proceso, proceso.getEstadoProceso());
        }
        procesos.clear();
        logger.info("Deteniendo el timer");
        logManager.addLog("Deteniendo el timer", null);
        timerRemontaje.stop();
        isIniciado = false;
    }

    /**
     *
     * @return Los procesos activos en el sistema
     */
    public List<Proceso> getProcesos() {
        return procesos;
    }

    /**
     * Busca los procesos con estado iniciado y que deban ser arrancados
     * @return Los procesos con estado iniciado
     */
    private List<Proceso> cargarProcesos() {
        logger.info("[GestorGeneralEstado.cargarProcesos]");
        logManager.addLog("[GestorGeneralEstado.cargarProcesos]", null);
        ProcesoFilter filter = new ProcesoFilter();
        filter.setEstadoProceso(EstadoProceso.INICIADO);
        filter.setFecha(new Date());
        logger.info("Buscando los procesos iniciados en la Base de Datos");
        logManager.addLog("Buscando los procesos iniciados en la Base de Datos", null);
        List<Proceso> procesosEncontrados = procesoDao.findByFilter(filter);

        return procesosEncontrados;
    }

    /**
     * Inicia un proceso para controlar
     * @param proceso El proceso para ser iniciado
     * @throws GestorGeneralEstadoException
     */
    private void iniciarProceso(Proceso proceso) throws GestorGeneralEstadoException {
        logger.info("[GestorGeneralEstado.iniciarProceso]");
        logManager.addLog("[GestorGeneralEstado.iniciarProceso]", proceso);
        GestorComunicacionRecibir gcr = new GestorComunicacionRecibir(proceso);
        gcr.addEventListener(this);
        receivers.add(gcr);
    }

    /**
     * Procesa los eventos del sistema de acuerdo al tipo del evento
     * @param e Un evento del sistema
     */
    public synchronized void actionPerformed(ViniControlEvent e) {

        logger.info("[GestorGeneralEstado.actionPerformed]");

        if (e instanceof NuevoEstadoTanqueEvent) {
            NuevoEstadoTanqueEvent event = (NuevoEstadoTanqueEvent) e;
            EstadoTanque estadoTanque = event.getEstadoTanque();
            Proceso proceso = event.getProceso();
            //Verifico si el estado tanque es igual al ultimo que agregue
            if (proceso.addEstadoTanque(estadoTanque)) {
                proceso.setEstadoTanque(estadoTanque);
                try {
                    gestorPrediccionTendencia.actualizar(estadoTanque.getTemperatura(), proceso);
                } catch (PrediccionException ex) {
                    logger.error("Fallo al actualizar la predicción");
                    logManager.addLog("Fallo al actualizar el predicción", proceso);
                    logger.error("No se pudo agregar la predicción");
                    logManager.addLog("No se pudo agregar la predicción", proceso);
                    logger.error(ex.getMessage());
                    logManager.addLog(ex.getMessage(), proceso);
                }
            } else {
                //si es igual al ultimo no lo agrego
                estadoTanque = proceso.getEstadoTanque();
            }
            verificarAlarma(estadoTanque);
            try {
                procesoDao.update(proceso);
            } catch (PersistException ex) {
                logger.error("Fallo al actualizar el proceso");
                logManager.addLog("Fallo al actualizar el proceso", proceso);
                logger.error("No se pudo agregar el estado del tanque");
                logManager.addLog("No se pudo agregar el estado del tanque", proceso);
                logger.error(ex.getMessage());
                logManager.addLog(ex.getMessage(), proceso);
            }

            dispatchEvent(event);
        }
    }

    /**
     * Se valida si un proceso se puede iniciar
     * @param proceso El proceso a validar
     * @throws GestorGeneralEstadoException
     */
    public void validarInicio(Proceso proceso) throws GestorGeneralEstadoException {
        logger.info("[GestorGeneralEstado.validarInicio]");
        logManager.addLog("[GestorGeneralEstado.validarInicio]", proceso);
        ProcesoFilter filter = new ProcesoFilter();
        filter.setEstadoProceso(EstadoProceso.INICIADO);
        filter.setTanque(proceso.getTanque());
        List<Proceso> procesosIniciados = procesoDao.findByFilter(filter);
        if (!procesosIniciados.isEmpty()) {
            logger.info("Ya hay un tanque asignado a un proceso que esta iniciado");
            logManager.addLog("Ya hay un tanque asignado a un proceso que esta iniciado", proceso);
            throw new GestorGeneralEstadoException("Ya hay un tanque asignado a un proceso que esta iniciado");
        } else {
            iniciarProceso(proceso);
            procesos.add(proceso);
            dispatchEvent(new AgregarProcesoEvent(this, proceso));
        }
    }

    /**
     * Cuando un proceso se detiene se liberan los recursos que estaba ocupando
     * @param proceso El proceso a detener
     * @param estadoProceso Se cambia el estado del proceso
     * @throws GestorGeneralEstadoException
     */
    public void liberarRecursos(Proceso proceso, EstadoProceso estadoProceso) throws GestorGeneralEstadoException {
        logger.info("[GestorGeneralEstado.liberarRecursos]");
        logManager.addLog("[GestorGeneralEstado.liberarRecursos]", proceso);
        for (GestorComunicacionRecibir gcr : receivers) {
            if (gcr.getProceso().equals(proceso)) {
                receivers.remove(gcr);
                gcr.removeEventListener(this);
                proceso.setEstadoProceso(estadoProceso);
                break;
            }
        }
        dispatchEvent(new RemoverProcesoEvent(this, proceso));
    }

    /**
     * Se ejecuta el comando de bajar temperatura para el proceso
     * @param proceso El proceso que se esta controlando
     * @throws GestorGeneralEstadoException
     * @throws ComunicacionException
     */
    public void enviarComandoBajarTemperatura(Proceso proceso) throws GestorGeneralEstadoException, ComunicacionException {
        logger.info("[GestorGeneralEstado.enviarComandoBajarTemperatura]");
        logManager.addLog("[GestorGeneralEstado.enviarComandoBajarTemperatura]", proceso);
        ConfiguracionConexion cip = proceso.getConfiguracionConexion();
        if (cip == null) {
            logger.error("No hay ninguna conexión activa para el tanque del proceso");
            logManager.addLog("No hay ninguna conexión activa para el tanque del proceso", proceso);
            logger.error("Tanque: " + proceso.getTanque());
            logManager.addLog("Tanque: " + proceso.getTanque(), proceso);
            logger.error("Proceso: " + proceso);
            logManager.addLog("Proceso: " + proceso, proceso);
            throw new GestorGeneralEstadoException("No hay ninguna conexión activa para el tanque del proceso");
        }
        IComandoTanque comando = FactoriaComandos.getInstance().getComando(FactoriaComandos.COMANDO_BAJAR_TEMPERATURA, cip, proceso);
        comando.setTanque(proceso.getTanque(), proceso.getEstadoTanque());
        comando.execute();
    }

    /**
     * Se ejecuta el comando de remontaje para el proceso
     * @param proceso El proceso que se esta controlando
     * @throws GestorGeneralEstadoException
     * @throws ComunicacionException
     */
    public void enviarComandoRemontaje(Proceso proceso) throws GestorGeneralEstadoException, ComunicacionException {
        logger.info("[GestorGeneralEstado.enviarComandoRemontaje]");
        logManager.addLog("[GestorGeneralEstado.enviarComandoRemontaje]", proceso);
        ConfiguracionConexion cip = proceso.getConfiguracionConexion();
        if (cip == null) {
            logger.error("No hay ninguna conexión activa para el tanque del proceso");
            logManager.addLog("No hay ninguna conexión activa para el tanque del proceso", proceso);
            logger.error("Tanque: " + proceso.getTanque());
            logManager.addLog("Tanque: " + proceso.getTanque(), proceso);
            logger.error("Proceso: " + proceso);
            logManager.addLog("Proceso: " + proceso, proceso);
            throw new GestorGeneralEstadoException("No hay ninguna conexión activa para el tanque del proceso");
        }
        IComandoTanque comando = FactoriaComandos.getInstance().getComando(FactoriaComandos.COMANDO_REMONTAJE, cip, proceso);
        comando.setTanque(proceso.getTanque(), proceso.getEstadoTanque());
        comando.execute();
        dispatchEvent(new NuevoComandoEvent(this, "Ejecutando remontaje", proceso));
    }

    /**
     * Se ejecuta el comando de fin de remontaje para el proceso
     * @param proceso El proceso que se esta controlando
     * @throws GestorGeneralEstadoException
     * @throws ComunicacionException
     */
    public void enviarComandoFinRemontaje(Proceso proceso) throws GestorGeneralEstadoException, ComunicacionException {
        logger.info("[GestorGeneralEstado.enviarComandoFinRemontaje]");
        logManager.addLog("[GestorGeneralEstado.enviarComandoFinRemontaje]", proceso);
        ConfiguracionConexion cip = proceso.getConfiguracionConexion();
        if (cip == null) {
            logger.error("No hay ninguna conexión activa para el tanque del proceso");
            logManager.addLog("No hay ninguna conexión activa para el tanque del proceso", proceso);
            logger.error("Tanque: " + proceso.getTanque());
            logManager.addLog("Tanque: " + proceso.getTanque(), proceso);
            logger.error("Proceso: " + proceso);
            logManager.addLog("Proceso: " + proceso, proceso);
            throw new GestorGeneralEstadoException("No hay ninguna conexión activa para el tanque del proceso");
        }
        IComandoTanque comando = FactoriaComandos.getInstance().getComando(FactoriaComandos.COMANDO_FIN_REMONTAJE, cip, proceso);
        comando.setTanque(proceso.getTanque(), proceso.getEstadoTanque());
        comando.execute();
        dispatchEvent(new NuevoComandoEvent(this, "Deteniendo el remontaje", proceso));
    }

    /**
     * Se verifica si el estado del tanque de un proceso se encuentra con alarmas
     * @param estadoTanque El estado del tanque que se esta controlando
     */
    private void verificarAlarma(EstadoTanque estadoTanque) {
        logger.info("[GestorGeneralEstado.verificarAlarma]");
        logManager.addLog("[GestorGeneralEstado.verificarAlarma]", estadoTanque.getProceso());
        logger.info("Estado del Tanque: " + estadoTanque);
        logManager.addLog("Estado del Tanque: " + estadoTanque, estadoTanque.getProceso());
        Proceso proceso = estadoTanque.getProceso();

        if (proceso == null) {
            logger.fatal("No se pueden verificar las alarmas el proceso es nulo");
            logManager.addLog("No se pueden verificar las alarmas el proceso es nulo", null);
            return;
        }

        boolean isAlarmaNivelAzucar = proceso.verificarAlarmaNivelAzucar();
        boolean isAlarmaNivel = proceso.verificarAlarmaNivel();
        boolean isAlarmaTemperaturaMaxima = proceso.verificarAlarmaTemperaturaMaxima();
        boolean isAlarmaTemperaturaMinima = proceso.verificarAlarmaTemperaturaMinima();
        boolean isAlarmaTemperaturaTolerancia = proceso.verificarAlarmaTemperaturaTolerancia();

        try {
            double temperaturaPredecida = gestorPrediccionTendencia.predecir(proceso, MINUTOS_PREDECIR);
            boolean isAlarmaTemperaturaPrediccion = proceso.verificarAlarmaTemperaturaPrediccion(temperaturaPredecida);

            if (!isAlarmaTemperaturaMaxima && isAlarmaTemperaturaPrediccion) {
                try {
                    enviarComandoBajarTemperatura(proceso);
                    dispatchEvent(new NuevoComandoEvent(this, "Ejecutando bajar temperatura por predicción", proceso));
                    logger.info("Se ha predecido que la temperatura va a subir hasta pasar el límite establecido como alarma");
                    logManager.addLog("Se ha predecido que la temperatura va a subir hasta pasar el límite establecido como alarma", proceso);
                    try {
                        procesoDao.update(proceso);
                    } catch (PersistException ex) {
                        ex.printStackTrace();
                        logger.error("Fallo al actualizar el proceso");
                        logManager.addLog("Fallo al actualizar el proceso", proceso);
                        logger.error("No se pudo agregar la alarma de temperatura");
                        logManager.addLog("No se pudo agregar la alarma de temperatura", proceso);
                        logger.error(ex.getMessage());
                        logManager.addLog(ex.getMessage(), proceso);
                    }
                } catch (GestorGeneralEstadoException ex) {
                    logger.error("Error al bajar la temperatura. ");
                    logManager.addLog("Error al bajar la temperatura. ", proceso);
                    logger.error(ex.getMessage());
                    logManager.addLog(ex.getMessage(), proceso);
                } catch (ComunicacionException ex) {
                    logger.error("Error al bajar la temperatura. ");
                    logManager.addLog("Error al bajar la temperatura. ", proceso);
                    logger.error(ex.getMessage());
                    logManager.addLog(ex.getMessage(), proceso);
                }
            }
            if (isAlarmaTemperaturaMinima) {
                AlarmaTemperatura alarmaTemperatura = new AlarmaTemperatura();
                alarmaTemperatura.setMaxima(false);
                alarmaTemperatura.setTemperatura(estadoTanque.getTemperatura());
                NuevaAlarmaEvent alarmaTemperaturaEvent = new NuevaAlarmaEvent(this, alarmaTemperatura);

                logger.warn("Alarma Temperatura: " + alarmaTemperatura);
                logManager.addLog("Alarma Temperatura: " + alarmaTemperatura, proceso);
                proceso.addAlarmaTemperatura(alarmaTemperatura);
                dispatchEvent(alarmaTemperaturaEvent);
                try {
                    procesoDao.update(proceso);
                } catch (PersistException ex) {
                    ex.printStackTrace();
                    logger.error("Fallo al actualizar el proceso");
                    logManager.addLog("Fallo al actualizar el proceso", proceso);
                    logger.error("No se pudo agregar la alarma de temperatura");
                    logManager.addLog("No se pudo agregar la alarma de temperatura", proceso);
                    logger.error(ex.getMessage());
                    logManager.addLog(ex.getMessage(), proceso);
                }
            }
        } catch (PrediccionException ex) {
            logger.error("Ha ocurrido un error al intentar verificar la predicción de la alarma");
            logManager.addLog("Ha ocurrido un error al intentar verificar la predicción de la alarma", proceso);
            logger.error(ex.getMessage());
            logManager.addLog(ex.getMessage(), proceso);
        }

        if (isAlarmaNivelAzucar) {
            AlarmaNivelAzucar alarmaNivelAzucar = new AlarmaNivelAzucar();
            alarmaNivelAzucar.setNivelAzucar(estadoTanque.getNivelAzucar());
            NuevaAlarmaEvent alarmaNivelAzucarEvent = new NuevaAlarmaEvent(this, alarmaNivelAzucar);
            logger.warn("Alarma Nivel Azucar: " + alarmaNivelAzucar);
            logManager.addLog("Alarma Nivel Azucar: " + alarmaNivelAzucar, proceso);
            proceso.addAlarmaNivelAzucar(alarmaNivelAzucar);
            dispatchEvent(alarmaNivelAzucarEvent);
            try {
                procesoDao.update(proceso);
            } catch (PersistException ex) {
                logger.error("Fallo al actualizar el proceso");
                logManager.addLog("Fallo al actualizar el proceso", proceso);
                logger.error("No se pudo agregar la alarma del nivel de azucar");
                logManager.addLog("No se pudo agregar la alarma del nivel de azucar", proceso);
                logger.error(ex.getMessage());
                logManager.addLog(ex.getMessage(), proceso);
            }
        }
        if (isAlarmaNivel) {
            AlarmaNivelCapacidad alarmaNivelCapacidad = new AlarmaNivelCapacidad();
            alarmaNivelCapacidad.setNivelCapacidad(estadoTanque.getNivelContenido());
            NuevaAlarmaEvent alarmaNivelCapacidadEvent = new NuevaAlarmaEvent(this, alarmaNivelCapacidad);
            logger.warn("Alarma Nivel Capacidad: " + alarmaNivelCapacidad);
            logManager.addLog("Alarma Nivel Capacidad: " + alarmaNivelCapacidad, proceso);
            proceso.addAlarmaNivelCapacidad(alarmaNivelCapacidad);
            dispatchEvent(alarmaNivelCapacidadEvent);
            try {
                procesoDao.update(proceso);
            } catch (PersistException ex) {
                logger.error("Fallo al actualizar el proceso");
                logManager.addLog("Fallo al actualizar el proceso", proceso);
                logger.error("No se pudo agregar la alarma del nivel de capacidad");
                logManager.addLog("No se pudo agregar la alarma del nivel de capacidad", proceso);
                logger.error(ex.getMessage());
                logManager.addLog(ex.getMessage(), proceso);
            }
        }
        if (isAlarmaTemperaturaMaxima) {
            AlarmaTemperatura alarmaTemperatura = new AlarmaTemperatura();
            alarmaTemperatura.setMaxima(true);
            alarmaTemperatura.setTemperatura(estadoTanque.getTemperatura());
            NuevaAlarmaEvent alarmaTemperaturaEvent = new NuevaAlarmaEvent(this, alarmaTemperatura);
            try {
                enviarComandoBajarTemperatura(proceso);
                dispatchEvent(new NuevoComandoEvent(this, "Ejecutando bajar temperatura", proceso));
                logger.warn("Alarma Temperatura: " + alarmaTemperatura);
                logManager.addLog("Alarma Temperatura: " + alarmaTemperatura, proceso);
                proceso.addAlarmaTemperatura(alarmaTemperatura);
                dispatchEvent(alarmaTemperaturaEvent);
                try {
                    procesoDao.update(proceso);
                } catch (PersistException ex) {
                    ex.printStackTrace();
                    logger.error("Fallo al actualizar el proceso");
                    logManager.addLog("Fallo al actualizar el proceso", proceso);
                    logger.error("No se pudo agregar la alarma de temperatura");
                    logManager.addLog("No se pudo agregar la alarma de temperatura", proceso);
                    logger.error(ex.getMessage());
                    logManager.addLog(ex.getMessage(), proceso);
                }
            } catch (GestorGeneralEstadoException ex) {
                logger.error("Error al bajar la temperatura. ");
                logManager.addLog("Error al bajar la temperatura. ", proceso);
                logger.error(ex.getMessage());
                logManager.addLog(ex.getMessage(), proceso);
            } catch (ComunicacionException ex) {
                logger.error("Error al bajar la temperatura. ");
                logManager.addLog("Error al bajar la temperatura. ", proceso);
                logger.error(ex.getMessage());
                logManager.addLog(ex.getMessage(), proceso);
            }
        }
        if (!isAlarmaTemperaturaMaxima && isAlarmaTemperaturaTolerancia) {
            try {
                enviarComandoBajarTemperatura(proceso);
                dispatchEvent(new NuevoComandoEvent(this, "Ejecutando bajar temperatura por tolerancia", proceso));
            } catch (GestorGeneralEstadoException ex) {
                logger.error("Error al bajar la temperatura. ");
                logManager.addLog("Error al bajar la temperatura. ", proceso);
                logger.error(ex.getMessage());
                logManager.addLog(ex.getMessage(), proceso);
            } catch (ComunicacionException ex) {
                logger.error("Error al bajar la temperatura. ");
                logManager.addLog("Error al bajar la temperatura. ", proceso);
                logger.error(ex.getMessage());
                logManager.addLog(ex.getMessage(), proceso);
            }
        }
    }

    /**
     * Se verifica el remontaje para todos los procesos
     */
    private void verificarRemontaje() {
        logger.info("[GestorGeneralEstado.verificarRemontaje]");
        logManager.addLog("[GestorGeneralEstado.verificarRemontaje]", null);

        for (Proceso proceso : procesos) {
            verificarRemontaje(proceso);
        }
    }

    /**
     * Se verifica si el proceso tiene que ser remontado
     * @param proceso El proceso a controlar
     */
    private void verificarRemontaje(Proceso proceso) {
        logger.info("[GestorGeneralEstado.verificarRemontaje]");
        logManager.addLog("[GestorGeneralEstado.verificarRemontaje]", proceso);

        if (!proceso.getEstadoProceso().equals(EstadoProceso.INICIADO)) {
            return;
        }

        ReglaControl reglaControl = proceso.getReglaControl();
        if (reglaControl == null) {
            return;
        }
        ReglaControlRemontaje rcr = reglaControl.getReglaControlRemontaje();
        try {
            if (rcr != null && rcr.isNecesarioRemontar(proceso.getFechaInicio())) {
                if (!rcr.isRemontando()) {
                    logger.info("No se está remontando, se debe remontar");
                    logManager.addLog("No se está remontando, se debe remontar", proceso);
                    enviarComandoRemontaje(proceso);
                    AlarmaRemontaje alarmaRemontaje = new AlarmaRemontaje();
                    alarmaRemontaje.setInicio(true);
                    NuevaAlarmaEvent alarmaRemontajeEvent = new NuevaAlarmaEvent(this, alarmaRemontaje);
                    logger.info("Alarma Remontaje: " + alarmaRemontaje);
                    logManager.addLog("Alarma Remontaje: " + alarmaRemontaje, proceso);
                    dispatchEvent(alarmaRemontajeEvent);
                    proceso.addAlarmaRemontaje(alarmaRemontaje);
                    try {
                        procesoDao.update(proceso);
                    } catch (PersistException ex) {
                        logger.error("Fallo al actualizar el proceso");
                        logManager.addLog("Fallo al actualizar el proceso", proceso);
                        logger.error("No se pudo agregar la alarma del remontaje");
                        logManager.addLog("No se pudo agregar la alarma del remontaje", proceso);
                        logger.error(ex.getMessage());
                        logManager.addLog(ex.getMessage(), proceso);
                    }
                }
            } else {
                if (rcr.isRemontando()) {
                    logger.info("Se está remontando, no se debe remontar");
                    logManager.addLog("Se está remontando, no se debe remontar", proceso);
                    enviarComandoFinRemontaje(proceso);
                    AlarmaRemontaje alarmaRemontaje = new AlarmaRemontaje();
                    alarmaRemontaje.setInicio(false);
                    NuevaAlarmaEvent alarmaRemontajeEvent = new NuevaAlarmaEvent(this, alarmaRemontaje);
                    dispatchEvent(alarmaRemontajeEvent);
                    logger.info("Alarma Remontaje: " + alarmaRemontaje);
                    logManager.addLog("Alarma Remontaje: " + alarmaRemontaje, proceso);
                    proceso.addAlarmaRemontaje(alarmaRemontaje);
                    try {
                        procesoDao.update(proceso);
                    } catch (PersistException ex) {
                        logger.error("Fallo al actualizar el proceso");
                        logManager.addLog("Fallo al actualizar el proceso", proceso);
                        logger.error("No se pudo agregar la alarma del nivel del remontaje");
                        logManager.addLog("No se pudo agregar la alarma del nivel del remontaje", proceso);
                        logger.error(ex.getMessage());
                        logManager.addLog(ex.getMessage(), proceso);
                    }
                }
            }
        } catch (GestorGeneralEstadoException ex) {
            logger.error("Ha fallado la verificación del remontaje");
            logManager.addLog("Ha fallado la verificación del remontaje", proceso);
            logger.error(ex.getMessage());
            logManager.addLog(ex.getMessage(), proceso);
        } catch (ComunicacionException ex) {
            logger.error("Ha fallado la verificación del remontaje");
            logManager.addLog("Ha fallado la verificación del remontaje", proceso);
            logger.error(ex.getMessage());
            logManager.addLog(ex.getMessage(), proceso);
        }
    }

    /**
     *
     * @param cc La configuración de conexión de un tanque
     * @return true si la configuración de conexión esta siendo usada
     */
    public synchronized boolean isConfiguracionConexionUsada(ConfiguracionConexion cc){

        boolean usado = false;

        for (Proceso proceso : procesos) {
            if( cc instanceof ConexionIP ){
                if( proceso.getTanque().getConexionIP()!=null &&
                    proceso.getTanque().getConexionIP().equals((ConexionIP)cc) ){
                    return true;
                }
            }else if( cc instanceof ConexionSerie ){
                if( proceso.getTanque().getConexionSerie() !=null &&
                    proceso.getTanque().getConexionSerie().equals((ConexionSerie)cc) ){
                    return true;
                }
            }
        }

        return usado;
    }

    /**
     *
     * @param tanque El tanque ha verififcar
     * @return true si el tanque está siendo utilizado
     */
    public synchronized boolean isTanqueUsado(Tanque tanque){
        boolean usado = false;

        for (Proceso proceso : procesos) {
            if( proceso.getTanque().equals(tanque) ){
                return true;
            }else if( proceso.getTanque().equals(tanque) ){
                return true;
            }
        }

        return usado;
    }

    /**
     *
     * @param ept El estado de predicción tendencia a verificar
     * @return true si el estado de predicción de tendencia esta siendo utilizado
     */
    public synchronized boolean isEstadoPrediccionTendenciaUsado(EstadoPrediccionTendencia ept){
        boolean usado = false;

        for (Proceso proceso : procesos) {
            if( proceso.getTanque().getEstadoPrediccionTendencia().equals(ept) ){
                return true;
            }else if( proceso.getTanque().getEstadoPrediccionTendencia().equals(ept) ){
                return true;
            }
        }

        return usado;
    }

    /**
     *
     * @param met El mosto en tanque a verificar
     * @return true si el mosto en tanque está siendo utilizado
     */
    public boolean isProductoUsado(MostoEnTanque met){
        boolean usado = false;

        for (Proceso proceso : procesos) {
            if( proceso.getMostoEnTanque().equals(met) ){
                return true;
            }else if( proceso.getMostoEnTanque().equals(met) ){
                return true;
            }
        }

        return usado;
    }
}
