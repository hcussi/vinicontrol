package edu.utn.frm.dao.generic;

import java.util.ArrayList;
import java.util.List;

import edu.utn.frm.dao.generic.hql.OrderClause;
import edu.utn.frm.dao.generic.utils.Paginator;


public abstract class  GenericFilter {
	
	protected Paginator paginator;
	protected Boolean anulada;
	protected List<OrderClause> order ; 
	
	public abstract boolean  isEmpty();	
	public abstract void clear ();

	
    public GenericFilter() {
        super();
        paginator=new Paginator();
        order = new ArrayList<OrderClause>(); 
    }
    
    protected boolean isNull(Paginator p)
    {
    	return isNull(p);
    }
    
    protected boolean isNull(String s)
    {
    	return (null == s || s.trim().length() == 0);
    }
    protected boolean isNull(Integer value)
	{
		return (null == value || value.intValue() == -1);
	}
    protected boolean isNull(int value)
	{
		return ( value== -1);
	}
    protected boolean isNull(Long value)
	{
		return (null == value || value.intValue() == -1);
	}
    protected boolean isNull(long value)
	{
		return ( value== -1);
	}
    protected boolean isNull(Double value)
	{
		return (null == value || value.intValue() == -1);
	}
    protected boolean isNull(double value)
	{
		return ( value== -1);
	}
    protected boolean isNull(Float value)
	{
		return (null == value || value.intValue() == -1);
	}
    protected boolean isNull(float value)
	{
		return ( value== -1);
	}
    protected boolean isNull(Object value)
	{
		return (null == value);
	}


	public boolean isAnulada() {
		return anulada;
	}


	public void setAnulada(boolean anulada) {
		this.anulada = anulada;
	}
    
	public boolean isNullAnulada(){
		return isNull(anulada);
	}
	
	public List<OrderClause> getOrder() {
		return order;
	}


	public void setOrder(List<OrderClause> order) {
		this.order = order;
	}
    
	public boolean isNullOrder(){
		if(order!=null && !order.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	public Paginator getPaginator() {
		return paginator;
	}
	
	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}
		
}