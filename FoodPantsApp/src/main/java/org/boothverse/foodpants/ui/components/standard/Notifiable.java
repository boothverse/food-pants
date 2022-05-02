package org.boothverse.foodpants.ui.components.standard;

public interface Notifiable {
    void notifyChange(String message, Object oldValue, Object newValue);
}
