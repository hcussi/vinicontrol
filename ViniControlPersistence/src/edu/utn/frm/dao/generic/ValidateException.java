/**
 * 
 */
package edu.utn.frm.dao.generic;

/**
 * @author Hernán Cussi, Cristian Llanos
 *
 */
public class ValidateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param msg
	 */
	public ValidateException(String msg) {
        super(msg);
    }
    
	/**
	 * 
	 * @param ex
	 */
	public ValidateException(Exception ex) {
		super(ex);
	}
	
	/**
	 * 
	 * @param msg
	 * @param ex
	 */
    public ValidateException(String msg,Exception ex) {
        super(msg,ex);
    }

}
