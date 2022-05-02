package org.boothverse.foodpants.ui.components.standard;

public interface Notifiable {
    public void notifyChange(String message, Object oldValue, Object newValue);
}
