package se.uog.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserType {

    private final PropertyChangeSupport propertyChangeSupport;
    private UserEnum userEnum;
    private static UserType instance;

    private UserType(UserEnum userEnum){
        this.userEnum = userEnum;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public UserEnum getUserEnum() {
        return userEnum;
    }

    @Override
    public String toString() {
        return "UserType{" +
            "userEnum=" + userEnum +
            '}';
    }

    public void setUserEnum(UserEnum userEnum) {
        System.out.println("ffiring");
        UserEnum oldNEnum = UserEnum.UNASSIGNED;
        this.userEnum = userEnum;
        propertyChangeSupport.firePropertyChange("userEnum", oldNEnum, userEnum);
    }

    public static UserType getInstance() {
        if (instance == null) {
            instance = new UserType(UserEnum.UNASSIGNED);
        }
        return instance;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }



    // Can make singleton?
}
