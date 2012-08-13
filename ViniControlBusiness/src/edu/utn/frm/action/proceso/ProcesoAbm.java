package edu.utn.frm.action.proceso;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.proceso.ProcesoDao;
import edu.utn.frm.dao.proceso.ProcesoFilter;
import edu.utn.frm.dao.producto.MostoEnTanqueDao;
import edu.utn.frm.dao.tanque.TanqueDao;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.producto.MostoEnTanque;
import edu.utn.frm.entities.tanque.Tanque;
import java.util.List;


public class ProcesoAbm extends GenericAbm<Proceso, ProcesoDao, ProcesoFilter> {

    private MostoEnTanqueDao mostoEnTanqueDao = new MostoEnTanqueDao();
    private TanqueDao tanqueDao = new TanqueDao();

    public ProcesoAbm() {
        super(Proceso.class,
                new ProcesoDao(LoginControlador.getInstance().getUsuario()),
                new ProcesoFilter());
    }

    public List<Tanque> buscarTodosTanques(){
        return tanqueDao.findAll();
    }

    public List<MostoEnTanque> buscarTodosProductos(){
        return mostoEnTanqueDao.findAll();
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportProceso.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Procesos";
    }
    
}