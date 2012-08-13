package edu.utn.frm.action.producto;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.generic.PersistException;
import edu.utn.frm.dao.generic.ValidateException;
import edu.utn.frm.dao.generic.utils.HelperValidator;
import edu.utn.frm.dao.proceso.ProcesoDao;
import edu.utn.frm.dao.producto.CamionDao;
import edu.utn.frm.dao.producto.FincaDao;
import edu.utn.frm.dao.producto.MostoEnTanqueDao;
import edu.utn.frm.dao.producto.MostoEnTanqueFilter;
import edu.utn.frm.dao.producto.VariedadDao;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.producto.Camion;
import edu.utn.frm.entities.producto.Finca;
import edu.utn.frm.entities.producto.MostoEnTanque;
import edu.utn.frm.entities.producto.Pesada;
import edu.utn.frm.entities.producto.Variedad;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public class MostoEnTanqueAbm extends GenericAbm<MostoEnTanque, MostoEnTanqueDao, MostoEnTanqueFilter> {

    private static Logger logger = Logger.getLogger(MostoEnTanqueAbm.class);

    private CamionDao camionDao = new CamionDao();
    private VariedadDao variedadDao = new VariedadDao();
    private FincaDao fincaDao = new FincaDao();
    private ProcesoDao procesoDao = new ProcesoDao(LoginControlador.getInstance().getUsuario());
    private Pesada pesada;
    private boolean crearProceso;

    public MostoEnTanqueAbm() {
        super(MostoEnTanque.class,
                new MostoEnTanqueDao(LoginControlador.getInstance().getUsuario()),
                new MostoEnTanqueFilter());
    }

    public List<Camion> buscarTodosCamiones() {
        return camionDao.findAll();
    }

    public List<Variedad> buscarTodasVariedades() {
        return variedadDao.findAll();
    }

    public List<Finca> buscarTodasFincas() {
        return fincaDao.findAll();
    }

    public Pesada getPesada() {
        return pesada;
    }

    public void setPesada(Pesada pesada) {
        this.pesada = pesada;
    }

    public void setCrearProceso(boolean crearProceso) {
        this.crearProceso = crearProceso;
    }

    public void validPesada() throws ValidateException {
        HelperValidator<Pesada> validator = new HelperValidator<Pesada>();
        validator.validate(pesada, Pesada.class);
    }

    public void nuevoPesada() {
        pesada = new Pesada();
    }

    public void eliminarPesada() {
        entity.remove(pesada);
    }

    public void guardarPesada() {
        entity.addPesada(pesada);
    }

    public void crearProceso() throws PersistException {
        try {
            if (crearProceso) {
                Proceso proceso = new Proceso();
                proceso.setMostoEnTanque(entity);
                procesoDao.save(proceso);
            }
        } catch (PersistException ex) {
            logger.error("No se ha podido crear el proceso");
            throw ex;
        }
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportMostoEnTanque.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Mostos en Tanque";
    }
}
