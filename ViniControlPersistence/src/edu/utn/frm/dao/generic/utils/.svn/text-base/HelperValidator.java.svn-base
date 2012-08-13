/*
 * HelperValidator.java
 *
 * Created on 16 de abril de 2008, 10:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.utn.frm.dao.generic.utils;

import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidStateException;
import org.hibernate.validator.InvalidValue;
import edu.utn.frm.dao.generic.ValidateException;
import edu.utn.frm.entities.base.EntityBase;

/**
 *
 * @author Proyecto
 */
public class HelperValidator<E extends EntityBase>{
    
    /** Creates a new instance of HelperValidator */
    public HelperValidator() {
    
    }
    
    /**
     * 
     * @param entity
     * @param c
     * @throws ValidateException
     */
    public void validate(E entity,Class c) throws ValidateException{
       
        ClassValidator<E> validator = new ClassValidator<E>(c);
        
        StringBuilder msg = new StringBuilder();
        try{
            validator.assertValid(entity);
        }catch(InvalidStateException ex){
            InvalidValue[] values = ex.getInvalidValues();
            
            for(int i = 0;i < values.length;i++){
                InvalidValue value = values[i];
                msg.append(value.getMessage()).append("\n");
            }
        }
        
        if(msg.length()>0){
            throw new ValidateException(msg.toString());
        }
         
    }
}
