/*
 * EstadoPrediccionDao.java
 *
 * Created on 27 de noviembre de 2006, 22:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.utn.frm.dao.demanda.estado;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.tanque.demanda.EstadoPrediccion;
import edu.utn.frm.entities.usuario.Usuario;
import java.util.List;

/**
 *
 * @author Proyecto
 */
public class EstadoPrediccionDao extends GenericDao<EstadoPrediccion,Long> {
    
    /**
     * Creates a new instance of EstadoPrediccionDao
     */
    public EstadoPrediccionDao(Usuario currentUser) {
        super(EstadoPrediccion.class,currentUser);
    }
    
    /**
     * Creates a new instance of EstadoPrediccionDao
     */
    public EstadoPrediccionDao() {
        super(EstadoPrediccion.class);
    }

    public EstadoPrediccion findEstado() {
        HqlBuilder builder = new HqlBuilder(getVinicEM());
        builder.appendSchema(getEntityName(),"a") ;
        List<EstadoPrediccion> list = builder.executeList();
        return list.size()>0?list.get(0):null;
    }
    
    protected void parseFilter(HqlBuilder builder, GenericFilter filter) {
        EstadoPrediccionFilter filtro = (EstadoPrediccionFilter) filter;

        builder.appendSchema(getEntityName(),"a") ;

        if (filter.isEmpty()){    
            return;
        }
    }

}
