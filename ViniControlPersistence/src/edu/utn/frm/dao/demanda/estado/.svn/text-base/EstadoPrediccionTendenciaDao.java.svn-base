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
import edu.utn.frm.entities.tanque.demanda.EstadoPrediccionTendencia;
import edu.utn.frm.entities.usuario.Usuario;

/**
 *
 * @author Proyecto
 */
public class EstadoPrediccionTendenciaDao extends GenericDao<EstadoPrediccionTendencia,Long>{
    
    /**
     * Creates a new instance of EstadoPrediccionDao
     */
    public EstadoPrediccionTendenciaDao(Usuario currentUser) {
        super(EstadoPrediccionTendencia.class,currentUser);
    }
    
    /**
     * Creates a new instance of EstadoPrediccionDao
     */
    public EstadoPrediccionTendenciaDao() {
        super(EstadoPrediccionTendencia.class);
    }

    protected void parseFilter(HqlBuilder builder, GenericFilter filter) {
        EstadoPrediccionTendenciaFilter filtro = (EstadoPrediccionTendenciaFilter) filter;

        builder.appendSchema(getEntityName(),"e") ;

        if (filter.isEmpty()){    
            return;
        }
        
    }

}
