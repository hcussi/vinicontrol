package edu.utn.frm.dao.generic.hql;

import edu.utn.frm.entities.base.EntityBase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 * Objeto que construye consultas parametrizadas de EJBQL/Hibernate (HQL) de manera dinamica.
 * Tambien permite hacer consultas parametrizadas para el paginado por demanda ("on demand")
 * @author Proyecto
 *
 */
public class HqlBuilder {
	
    /**
     * Mapa de paremetros de la consulta
     */
	private HashMap<String,Object> parameters;
    /**
     * Consulta para traer los items
     */
	private StringBuilder statement;
    /**
     * Consulta para saber cuantos items conciden con la consutla, independientemente
     * de los que se traigan cada vez. Sirve para hacer paginacion por demanda
     */
	private StringBuilder countstatement;
    /**
     * EntityManager a usar
     */
	private EntityManager em;
    /**
     * Indica si ya se uso <code>appendWhereClause()</code>
     */
	private boolean whereAppended;
    /**
     * Indica si el lo ultimo agregado fue una clausula de campo
     */
	private boolean fieldAppended;
    /**
     * Indica si ya se agrego ORDER BY 
     */
	private boolean orderbyAppended; 
    /**
     * Nombre de la entidad de la clase se van a recuperar objetos
     */
    private String alias; 
	
    /**
     * Constructor 
     * @param em EntityManager a usar
     */
	public HqlBuilder(EntityManager em) {
		super();
		this.parameters = new HashMap<String,Object>();
		this.statement = new StringBuilder();
		this.countstatement = new StringBuilder(); 
		this.em = em;
	}
	
    /**
     * Agrega el operador <code>AND</code> si lo ultimo agregado fue una clausula de campo
     * @return esta instancia del builder
     */
	public HqlBuilder addAndClause() {
		if (fieldAppended == true){
			statement.append(" and ");
			countstatement.append(" and ");
            fieldAppended = false;
		}
		return this;
	}
	
    /**
     * Agrega el operador <code>OR</code> si lo ultimo agregado fue una clausula de campo
     * @return esta instancia del builder
     */
	public HqlBuilder addOrClause() {
		if (fieldAppended == true){
			statement.append(" or ");
			countstatement.append(" or ");
            fieldAppended = false;
		}
		return this;
	}
	
    /**
     * Agrega una clausula de campo
     * @param alias: establecido en <code>appendSchema(clase, alias)</code> para la clase a buscar 
     * @param property: nombre del campo a usar de la clase
     * @param operator: operador a usar
     * @param paramaterName: nombre de parametro
     * @param parameterValue: valor del parametro
     * @return esta instancia del builder
     */
	public HqlBuilder appendFieldClause(String alias, String property, String operator, 
			String paramaterName, Object parameterValue) {
		//para buscar
		statement.append(alias).append(".").append(property).append(" ").append(operator).
			append(" :").append(paramaterName).append(" ");
		//para contar
		countstatement.append(alias).append(".").append(property).append(" ").append(operator).
		append(" :").append(paramaterName).append(" ");
		parameters.put(paramaterName, parameterValue);
		fieldAppended = true;
		return this;
	}
	
    /**
     * Agrega una segunda clase con la cual se hace JOIN.
     * <br><b>Debe ser usado de forma excluyente con, y en lugar de 
     *         <pre>
     *          appendShecma(clase, alias)
     *         </pre> 
     * </b></br>
     * @param mainSchema: clase principal de busqueda
     * @param mainAlias: alias de la clase principal
     * @param joins: clases para hacer join
     * @param joinType: tipo de join (INNER,OUTER,LEFT)
     * @return esta instancia del builder
     */
	public HqlBuilder appendJoinClause(String mainSchema, String mainAlias, JoinedEntity [] joins, JoinType joinType) {
		statement.append("select distinct "+ mainAlias + " from ").append(mainSchema).append(" " + mainAlias +" ");
		countstatement.append("select count ( distinct "+ mainAlias + ")  from ").append(mainSchema).append(" " + mainAlias +" ");
		for (JoinedEntity join : joins) {
            String s = join.toString();
			statement.append(joinType +" join " + s);	
			countstatement.append(joinType +" join " + s);
		}
		return this;
	}
	
    /**
     * Agrega una clausula de campo con varios valores
     * 
     * @param alias: establecido en <code>appendSchema(clase, alias)</code> para la clase a buscar 
     * @param property: nombre del campo a usar de la clase
     * @param operator: operador a usar
     * @param paramaterName: nombre de parametro
     * @param parameterValues: arreglo con valores del parametro
     * @param withAnd: todas las clasulas separadas con <code>AND</code> (<code>true</code>) o con <code>OR</code> (<code>false</code>) 
     * @return esta instancia del builder
     */
	public HqlBuilder appendFieldArray(String alias, String property, String operator, 
				String paramaterName, Object[] parameterValues, boolean withAnd) {
		openParanthesis();
		for (int i = 0; i < parameterValues.length; i++) {
			appendFieldClause(alias,property,operator,paramaterName+i,parameterValues[i]);
			if (i<parameterValues.length-1) {
				if (withAnd)
					addAndClause();
				else
					addOrClause();
			}
		}
		closeParenthesis();
		return this;
	}
    
    /**
     * Agrega una clausula de campo con varios ids de Entidades
     * 
     * @param alias: establecido en <code>appendSchema(clase, alias)</code> para la clase a buscar 
     * @param property: nombre del campo a usar de la clase
     * @param operator: operador a usar
     * @param paramaterName: nombre de parametro
     * @param entityValues: coleccion de entidades
     * @param withAnd: todas las clasulas separadas con <code>AND</code> (<code>true</code>) o con <code>OR</code> (<code>false</code>) 
     * @return esta instancia del builder
     */
    public HqlBuilder appendFieldCollection(String alias, String property, String operator, 
			String paramaterName, Collection<? extends EntityBase> entityValues, boolean withAnd) {
    	openParanthesis();
    	int i = 0;
    	for (EntityBase entity : entityValues) {
    		appendFieldClause(alias,property,operator,paramaterName+i,entity.getId());
    		if (i<entityValues.size()-1)
    		{
    			if (withAnd)
    				addAndClause();
    			else
    				addOrClause();
    		}
    		i++;
    	}
    	closeParenthesis();
    	return this;
    }
	
    /**
     * Abre un parentesis
     * @return esta instancia del builder
     */
	public HqlBuilder openParanthesis() {
		statement.append(" (");
		countstatement.append(" (");
		return this;
	}
	
    /**
     * Cierra un parentesis
     * @return esta instancia del builder
     */
	public HqlBuilder closeParenthesis() {
		statement.append(") ");
		countstatement.append(") ");
		return this;
	}
	
    /**
     * Agrega clausula FROM
     * @param schema: nombre de la clase
     * @param alias: alias a usar en las consultas
     * @return esta instancia del builder
     */
	public HqlBuilder appendSchema(String schema, String alias) {
        this.alias = alias; 
		statement.append("from ").append(schema).append(" " + alias +" ");
		countstatement.append("select count( ").append(alias).append(" ) ").append(" from ").append(schema).append(" " + alias +" ");
		return this;
	}
	
    /**
     * Agrega la clausula WHERE
     * @return esta instancia del builder
     */
	public HqlBuilder appendWhereClause() {
        if (!isWhereAppended()) {
    		statement.append(" where ");
    		countstatement.append(" where ");
    		whereAppended = true;
        }
		return this;
	}

    /**
     * Agrega una cadena escrita manualmente
     * @return esta instancia del builder
     */
	public HqlBuilder appendFreeClause(String clause) {
		statement.append(clause);
		countstatement.append(clause);
		fieldAppended = true;
		return this;
	}
	
    /**
     * Ejecuta la consulta y trae todos los datos
     * @return esta instancia del builder
     */
	public List executeList() {
		Query query = em.createQuery(statement.toString());
		setParamneters(query);
		return query.getResultList();
	}

	public Object executeField() {
		Query query = em.createQuery(statement.toString());
		setParamneters(query);
		Object object = null;
		try{
			object = query.getSingleResult();
			return object;
		}catch (NoResultException e) {
			return object;
		}
	}
	
	private void setParamneters(Query query) {
		Set<Entry<String,Object>> entrySet = parameters.entrySet();
		for (Entry<String,Object> entry : entrySet) {
			query.setParameter(entry.getKey(),entry.getValue());
		}
	}

    /**
     * Obtiene la bandera <code>fieldAppended</code>
     * @return fieldAppended
     */
	public boolean isFieldAppended() {
		return fieldAppended;
	}
	/**
     * Establece la bandera <code>fieldAppended</code>
     * @param fieldAppended
	 */
	public void setFieldAppended(boolean fieldAppended) {
		this.fieldAppended = fieldAppended;
	}

    /**
     * Obtiene la bandera <code>whereAppended</code>
     * @return whereAppended
     */
	public boolean isWhereAppended() {
		return whereAppended;
	}
    /**
     * Establece la bandera <code>whereAppended</code>
     * @param whereAppended
     */
	public void setWhereAppended(boolean whereAppended) {
		this.whereAppended = whereAppended;
	}

    /**
     * Devuelve la cantidad de objectos devueltos por el builder actual
     * @return cantidad de objectos
     */
	public int getRowCount() {
		Query query = em.createQuery(countstatement.toString());
		setParamneters(query);
		return Integer.decode(query.getSingleResult().toString()); 
	}
    
    /**
     * Ejecuta la consulta
     * @param pageNumber: numero de la pagina a buscar (empieza en 0)
     * @param pageSize: tama�o de la pagina
     * @return resultado de la busqueda
     */
	public List executeList(int pageNumber, int pageSize) {
		Query query = em.createQuery(statement.toString());
		setParamneters(query);
		//Trae la cantidad de objetos que indica pageSize
		if (pageSize >= 0)
			query.setMaxResults(pageSize);
		//Trae a partir de este resultado
		if (pageSize >= 0 && pageNumber >= 0)
			query.setFirstResult(pageNumber*pageSize);
		return query.getResultList();
	}	
	
	/**
     * Agrega una clausula <code>ORDER BY</code> para un campo
     * @param alias: alias de la clase
     * @param property: campo por el cual se ordena
     * @param dir: direccion de orden
     * @return esta instancia del builder
	 */
	public HqlBuilder appendOrderClause(String alias,String property,OrderType dir){
		addOrderClause(new OrderClause(alias,property,dir));
		return this;
	}
	
	/**
     * Agrega varias clausulas <code>ORDER BY</code>
     * @param aliases: alias de las clases devueltas
     * @param properties: campos de busqueda para cada alias
     * @param dirs: direcciones de orden para cada alias
     * @return esta instancia del builder
	 */
	public HqlBuilder appendOrderClauseArray(String[] aliases,String[] properties,OrderType[] dirs) {
		List<OrderClause> clauses = new ArrayList<OrderClause>(properties.length);
		for (int i = 0, j = 0, k = 0;
			i < properties.length; i++,
			j=(j<dirs.length-1)?j+1:j,
			k=(k<aliases.length-1)?k+1:k) {
			    clauses.add(new OrderClause(aliases[k],properties[i],dirs[j]));
		}
		addOrderClause(clauses);
		return this;
	}
	
    /**
     * Agrega varias clausulas <code>ORDER BY</code>
     * @param orders: lista de objectos de clausula
     * @return esta instancia del builder
     */
	public HqlBuilder addOrderClause(List<OrderClause> orders ){
		if(orders != null) {
			Iterator iterator=orders.iterator();
			while(iterator.hasNext()) {
				 addOrderClause((OrderClause)iterator.next());
				 if(iterator.hasNext()){
					 statement.append(" , ");
				 }
			}
		}
		return this;
	}

    /**
     * Agrega una clausula <code>ORDER BY</code>
     * @param clause: objecto de clausula
     * @return esta instancia del builder
     */
	public HqlBuilder addOrderClause(OrderClause clause) {
		if(!orderbyAppended) {
			statement.append(" order by ");
			orderbyAppended = true ; 
		}
		statement.append(clause.getAlias()).append(".").append(clause.getField()).append(" ").append(clause.getOrder());
		return this;
	}

    /**
     * Obtiene el nombre de la clase a buscar
     * @return nombre
     */
    public String getAlias() {
        return alias;
    }
}
