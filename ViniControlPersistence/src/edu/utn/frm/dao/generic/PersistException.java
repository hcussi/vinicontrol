/**
 * 
 */
package edu.utn.frm.dao.generic;

/**
 * @author Proyecto
 *
 */
public class PersistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param msg
	 */
	public PersistException(String msg) {
        super(msg);
    }
    
	/**
	 * 
	 * @param ex
	 */
	public PersistException(Exception ex) {
		super(ex);
	}
	
	/**
	 * 
	 * @param msg
	 * @param ex
	 */
    public PersistException(String msg,Exception ex) {
        super(msg,ex);
    }

}
