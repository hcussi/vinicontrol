package edu.utn.frm.action;

import edu.utn.frm.action.exception.ReporteException;
import edu.utn.frm.action.file.FileExtensionFilter;
import edu.utn.frm.action.file.JFileChooser;
import edu.utn.frm.action.log.LogManager;
import edu.utn.frm.action.reporte.GestorReporte;
import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.PersistException;
import edu.utn.frm.dao.generic.ValidateException;
import edu.utn.frm.dao.generic.utils.HelperValidator;
import edu.utn.frm.entities.base.EntityBase;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.exception.ConstraintViolationException;


/*
 * Generic action provee metodos para realizar ABM de Entidades de Negocio.
 * @author Proyecto
 */
@SuppressWarnings("unchecked")
public class GenericAbm<E extends EntityBase, D extends GenericDao, F extends GenericFilter> {

    protected D dao;
    protected F filter;
    protected E entity;
    protected Class<E> classEntity;
    private static Logger logger = Logger.getLogger(GenericAbm.class);
    private static LogManager logManager = LogManager.getInstance();
    public GenericAbm(Class<E> classEntity, D dao, F filter) {
        super();
        this.dao = dao;
        this.filter = filter;
        this.classEntity = classEntity;
    }

    /*
     * Para que en un principio ya este creado el EntityManagerFactory
     */
    public static void inicializar() throws PersistException {
        //Configura el log4j
        try{
            PropertyConfigurator.configure("log4j.properties");
        }catch(Exception ex){
            ex.printStackTrace();
            logManager.addLog("La configuración de log4j ha fallado", null);
        }
        //PropertyConfigurator.configure("log4j.properties");
        //DOMConfigurator.configure("log4j.xml");
        logger.info("Se esta inicializando la configuración de hibernate");
        logManager.addLog("Se esta inicializando la configuración de hibernate", null);
        try{
            GenericDao.getVinicEM();
        }catch(Throwable e){
            logger.error("No se ha podido conectar a la base de datos. Verifique los servicios");
            logger.error(e.getMessage());
            throw new PersistException("No se ha podido conectar a la base de datos. Verifique los servicios");
        }

        logger.info("Fin de la configuración de hibernate");
        logManager.addLog("Fin de la configuración de hibernate", null);
    }

    /*
     * Busqueda de enticades.
     * Para buscar las entidades se debe tener seteado antes el Filter
     */
    public List<E> buscar() {
        return dao.findByFilter(filter);
    }

    public List<E> buscarTodos() {
        return dao.findAll();
    }

    /*
     * Getters and Sets
     */
    public F getFilter() {
        return filter;
    }

    public void setFilter(F filter) {
        this.filter = filter;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    /*
     *
     */
    public void nuevo() {
        try {
            this.entity = classEntity.newInstance();
        } catch (InstantiationException e) {
            logger.error("Ha ocurrido un error al crear una nueva instancia");
            logger.error(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error("Ha ocurrido un error al crear una nueva instancia");
            logger.error(e.getMessage());
        }
    }

    public void eliminar() throws PersistException {
        try {
            dao.delete(entity);
        } catch (PersistException e) {
            if (e.getCause() != null && e.getCause().getCause() != null &&
                    e.getCause().getCause() instanceof ConstraintViolationException) {
                logger.error("No se puede eliminar, el objeto está siendo usado. " + entity);
                throw new PersistException("No se puede eliminar, el objeto está siendo usado");
            }
            logger.error("Ha ocurrido un error inesperado al eliminar. " + e.getMessage());
            throw new PersistException("Ha ocurrido un error inesperado al eliminar. \n" + e.getMessage());
        }
    }

    public void copiar() throws PersistException, ValidateException {

        entity = (E) entity.copy();
        valid();
        guardar();
    }

    public void guardar() throws PersistException {

        try {
            if (entity.getId() == null) {
                dao.save(entity);
            } else {
                dao.update(entity);
            }
        } catch (PersistException e) {
            if (e.getCause() != null && e.getCause().getCause() != null &&
                    e.getCause().getCause() instanceof ConstraintViolationException) {
                throw new PersistException(entity.getConstraintErrorMessage());
            }
            logger.error("Ha ocurrido un error inesperado al guardar. " + e.getMessage());
            throw new PersistException("Ha ocurrido un error inesperado al guardar. \n" + e.getMessage());
        }
    }

    protected boolean isValid() {
        return true;
    }

    public void valid() throws ValidateException {
        HelperValidator<E> validator = new HelperValidator<E>();
        validator.validate(entity, classEntity);
    }
    protected JFileChooser jFileChooser;

    public JFileChooser getJFileChooserInforme(FileExtensionFilter filter) {
        if (jFileChooser == null) {
            jFileChooser = new JFileChooser();
            jFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
            jFileChooser.setWarnOnWriteOver(true);
            jFileChooser.setAcceptAllFileFilterUsed(false);
            jFileChooser.setMultiSelectionEnabled(false);
            jFileChooser.setName("informe");
            FileExtensionFilter fef = filter;

            jFileChooser.setFileFilter(fef);
        }
        return jFileChooser;
    }

    protected String getNombreArchivo() {
        return "archivo.jasper";
    }

    protected String getTituloInforme() {
        return "Titulo Informe";
    }

    public void ejecutarReporte() throws ReporteException {
        logger.info("Inicio de la generación del reporte...");

        try {
            JasperReport masterReport = null;
            try {
                URL url = getClass().getResource(getNombreArchivo());

                masterReport = (JasperReport) JRLoader.loadObject(url);

            } catch (JRException e) {
                logger.error("Error cargando el reporte maestro: " + e.getMessage());
                throw new ReporteException("Error cargando el reporte maestro: " + e.getMessage());
            }
            //este es el parámetro, se pueden agregar más parámetros
            //basta con poner mas parametro.put

            //Reporte diseñado y compilado con iReport
            Connection conn = GestorReporte.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, null, conn);
            //Se lanza el Viewerde Jasper, no termina aplicación al salir
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle(getTituloInforme());
            jviewer.setVisible(true);
        } catch (Exception e) {
            logger.error("Error al generar el reporte. " + e.getMessage());
            throw new ReporteException("Error al generar el reporte. " + e.getMessage());
        }
    }

    /*
     * Impresión
     */
    public void showPrintPreview(String title, String fileName, HashMap hm, List list) {
        try {
            URL url = getClass().getResource(fileName);

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

            JRBeanCollectionDataSource unDataSource = new JRBeanCollectionDataSource(list);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, unDataSource);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle(title);
            jviewer.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
