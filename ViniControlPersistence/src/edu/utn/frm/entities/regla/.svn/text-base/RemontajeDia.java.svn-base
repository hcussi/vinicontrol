/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.regla;

import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.utils.DateOperations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jdom.Element;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name = "REMONTAJE_DIA")
@Inheritance(strategy = InheritanceType.JOINED)
public class RemontajeDia extends EntityBase{

    private ReglaControlRemontaje reglaControlRemontaje;
    private List<MomentoRemontaje> momentosRemontajes;
    private String nombre;
    
    public RemontajeDia() {
        momentosRemontajes = new ArrayList<MomentoRemontaje>();
    }
    
    @NotNull(message="El nombre del Remontaje Dia no puede ser nulo")
    @Length(min=3,max=20,message="El nombre del Remontaje Dia debe estar entre {min} y {max} caracteres")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the momentosRemontajes
     */
    @OneToMany(mappedBy="remontajeDia",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=MomentoRemontaje.class)
    @OrderBy("hora")
    public List<MomentoRemontaje> getMomentosRemontajes() {
        return momentosRemontajes;
    }

    /**
     * @param momentosRemontajes the momentosRemontajes to set
     */
    public void setMomentosRemontajes(List<MomentoRemontaje> momentosRemontajes) {
        this.momentosRemontajes = momentosRemontajes;
    }

    @ManyToOne(optional=false,targetEntity=ReglaControlRemontaje.class)
    @JoinColumn(name="reglaControlRemontajeId")
    @PrimaryKeyJoinColumn
    public ReglaControlRemontaje getReglaControlRemontaje() {
        return reglaControlRemontaje;
    }

    public void setReglaControlRemontaje(ReglaControlRemontaje reglaControlRemontaje) {
        this.reglaControlRemontaje = reglaControlRemontaje;
    }

    public void addMomentoRemontaje( MomentoRemontaje momentoRemontajeNuevo ){
        if( this.momentosRemontajes.contains(momentoRemontajeNuevo) ){
            this.momentosRemontajes.remove( momentoRemontajeNuevo );
        }
        int index = 0;
        for( ; index < momentosRemontajes.size() ; index++ ){
            MomentoRemontaje momentoRemontajeItem = this.momentosRemontajes.get(index);
            Date tiempoInicioItem = momentoRemontajeItem.getHora();
            Date tiempoInicioNuevo = momentoRemontajeNuevo.getHora();

            if( DateOperations.compareTimeHour(tiempoInicioNuevo, tiempoInicioItem) < 0 ){
                break;
            }
        }
        this.momentosRemontajes.add( index, momentoRemontajeNuevo );
        momentoRemontajeNuevo.setRemontajeDia( this );
        
    }

    public void remove(MomentoRemontaje momentoRemontaje){
        this.momentosRemontajes.remove( momentoRemontaje );
        //subCuentaMayor.setCuentaMayor(null);
    }

    public boolean isValid(MomentoRemontaje momentoRemontajeNuevo) {
        boolean valid = true;
        for( MomentoRemontaje momentoRemontajeItem: this.momentosRemontajes){

            if( momentoRemontajeItem.equals(momentoRemontajeNuevo) ){
                continue;
            }

            Date tiempoInicioItem = momentoRemontajeItem.getHora();
            
            Date tiempoInicioNuevo = momentoRemontajeNuevo.getHora();
            Date tiempoFinNuevo = DateOperations.add(momentoRemontajeNuevo.getHora(),
                momentoRemontajeNuevo.getDuracion(), DateOperations.MINUTE);

            if( DateOperations.compareTimeHour(tiempoInicioItem, tiempoInicioNuevo) > 0 ){
                if( DateOperations.compareTimeHour(tiempoInicioItem, tiempoFinNuevo) > 0 ){
                    valid = true;
                }else if( DateOperations.compareTimeHour(tiempoInicioItem, tiempoFinNuevo) == 0 ){
                    valid = true;
                }else{
                    return false;
                }
            }else if(DateOperations.compareTimeHour(tiempoInicioItem, tiempoInicioNuevo) == 0){
                return false;
            }else{
                valid = true;
            }
        }
        return valid;
    }

    public RemontajeDia copy() {
        RemontajeDia copy = new RemontajeDia();

        copy.setNombre(this.getNombre()+"-copy");
        for (MomentoRemontaje item : momentosRemontajes) {
            copy.addMomentoRemontaje(item.copy());
        }

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, ha ocurrido un error inesperado";
    }

     public Element exportar(){
        Element elementRemontajeItem = new Element("remontajeDia");

        if(nombre != null)elementRemontajeItem.setAttribute("nombre", nombre);

        if(momentosRemontajes.size()!=0)
        for(MomentoRemontaje momentoRemonteje : momentosRemontajes){
            elementRemontajeItem.addContent(momentoRemonteje.exportar());
        }

        return elementRemontajeItem;
    }

}