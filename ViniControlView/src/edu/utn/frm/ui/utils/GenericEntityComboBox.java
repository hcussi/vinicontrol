package edu.utn.frm.ui.utils;

class GenericEntityComboBox<E> {

    private E entity;
    private String property;

    public GenericEntityComboBox(E entity, String property) {
        this.entity = entity;
        this.property = property;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        Object result = Utiles.invokeMethodGet(entity, property);
        if (result == null || result.toString() == null || result.toString().equals("")) {
            return "null";
        } else {
            return result.toString();
        }
    }
}

