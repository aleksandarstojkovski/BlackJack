package ch.supsi.blackjack.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

abstract public class AbstractModel{

    protected final PropertyChangeSupport pcs;

    public AbstractModel() {
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }

    public void firePropertyChange(PropertyChangeEvent event){
        pcs.firePropertyChange(event);
    }
}
