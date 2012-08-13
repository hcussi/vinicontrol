package edu.utn.frm.ui.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

public class GenericComboBoxModel<E> extends DefaultComboBoxModel {

    private static final long serialVersionUID = 1L;
    private String property;

    public GenericComboBoxModel(String property) {
        this(new ArrayList<E>(), property, false);
    }

    public GenericComboBoxModel(List<E> items, String property) {
        this(items, property, false);
    }
    //Boolean firstNull: significa que el primero de la lista se muestra en blanco como para
    //que pueda seleccionar uno vacio

    public GenericComboBoxModel(List<E> items, String property, Boolean firstNull) {

        super();

        this.property = property;

        setEntities(items, firstNull);

    }

    public void setEntities(List<E> entitis) {

        setEntities(entitis, false);
    }

    public void setEntities(List<E> entitis, Boolean firstNull) {

        removeAllElements();

        if (firstNull) {
            addElement(null);
        }

        for (E entity : entitis) {
            addElement(new GenericEntityComboBox<E>(entity, property));
        }

        if (getSize() > 0) {
            setSelectedItem(getElementAt(0));
        }
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;

        int longitud = getSize() - 1;

        for (int i = 0; i < longitud; i++) {
            ((GenericEntityComboBox<E>) getElementAt(i)).setProperty(property);
        }

    }

    public E getSelectedEntity() {
        Object selectedItem = getSelectedItem();
        if (selectedItem != null) {
            return ((GenericEntityComboBox<E>) selectedItem).getEntity();
        } else {
            return null;
        }
    }

    public void setSelectedEntity(E entity) {
        if (entity == null) {
            setSelectedItem(null);
            return;
        }

        int longitud = getSize() - 1;

        for (int i = 0; i <= longitud; i++) {
            GenericEntityComboBox<E> object = (GenericEntityComboBox<E>) getElementAt(i);
            if (object != null && object.getEntity().equals(entity)) {
                setSelectedItem(getElementAt(i));
            }
        }
    }
}
