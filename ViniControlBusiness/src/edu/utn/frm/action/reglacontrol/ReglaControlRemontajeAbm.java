package edu.utn.frm.action.reglacontrol;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.generic.ValidateException;
import edu.utn.frm.dao.generic.utils.HelperValidator;
import edu.utn.frm.dao.regla.ReglaControlRemontajeDao;
import edu.utn.frm.dao.regla.ReglaControlRemontajeFilter;
import edu.utn.frm.entities.regla.MomentoRemontaje;
import edu.utn.frm.entities.regla.ReglaControlRemontaje;
import edu.utn.frm.entities.regla.RemontajeDia;
import org.apache.log4j.Logger;


public class ReglaControlRemontajeAbm extends GenericAbm<ReglaControlRemontaje, ReglaControlRemontajeDao,
        ReglaControlRemontajeFilter> {

    private static Logger logger = Logger.getLogger(ReglaControlRemontajeAbm.class);

    private RemontajeDia remontajeDia;
    private MomentoRemontaje momentoRemontaje;

    public ReglaControlRemontajeAbm() {
        super(ReglaControlRemontaje.class,
                new ReglaControlRemontajeDao(LoginControlador.getInstance().getUsuario()),
                new ReglaControlRemontajeFilter());
    }

    public MomentoRemontaje getMomentoRemontaje() {
        return momentoRemontaje;
    }

    public void setMomentoRemontaje(MomentoRemontaje momentoRemontaje) {
        this.momentoRemontaje = momentoRemontaje;
    }

    public RemontajeDia getRemontajeDia() {
        return remontajeDia;
    }

    public void setRemontajeDia(RemontajeDia remontajeDia) {
        this.remontajeDia = remontajeDia;
    }
    
    public void validRemontajeDia() throws ValidateException {
        HelperValidator<RemontajeDia> validator = new HelperValidator<RemontajeDia>();
        validator.validate(remontajeDia, RemontajeDia.class );
    }

    public void validMomentoRemontaje() throws ValidateException {
        HelperValidator<MomentoRemontaje> validator = new HelperValidator<MomentoRemontaje>();
        validator.validate(momentoRemontaje, MomentoRemontaje.class );
    }

    public void nuevoRemontajeDia(){
        remontajeDia = new RemontajeDia();
    }

    public void nuevoMomentoRemontaje(){
        momentoRemontaje = new MomentoRemontaje();
    }

    public void eliminarRemontajeDia(){
        entity.remove(remontajeDia);
    }

    public void eliminarMomentoRemontaje(){
        remontajeDia.remove(momentoRemontaje);
    }

    public void guardarRemontajeDia() {
        entity.addRemontajeDia(remontajeDia);
    }

    public void guardarMomentoRemontaje() throws Exception {
        if( remontajeDia.isValid( momentoRemontaje ) ){
            remontajeDia.addMomentoRemontaje(momentoRemontaje);
        }else{
            logger.warn("El Momento Remontaje se suporpone con otros. Revise el inicio y la duración");
            throw new Exception("El Momento Remontaje se suporpone con otros. Revise el inicio y la duración");
        }
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportReglaControlRemontaje.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Regla de Control de Remontaje";
    }

}