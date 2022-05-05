package org.boothverse.foodpants.ui.components;

import lombok.Getter;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.components.standard.Notifiable;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import org.boothverse.foodpants.ui.forms.EditFoodInstanceForm;
import org.boothverse.foodpants.ui.forms.StandardForm;
import org.boothverse.foodpants.ui.pages.Page;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ItemList extends JPanel implements PropertyChangeListener {
    protected List<StandardItem> items;
    protected JPanel listDisplay;
    protected Notifiable parental;
    protected boolean editing = false;

    public ItemList(int numColumns, Notifiable parent) {
        super();
        listDisplay = new JPanel(new GridLayout(0, numColumns));
        items = new ArrayList<>();
        this.parental = parent;
        setBackground(((Component)parent).getBackground());
        add(listDisplay);
    }

    ItemList(int numColumns, Page parent, List<StandardItem> itemList) {
        this(numColumns, parent);
        populate(itemList);
    }

    public void populate(List<StandardItem> itemList) {
        items.clear();
        items.addAll(itemList);
        listDisplay.removeAll();

        items.forEach(item -> {
            item.addPropertyChangeListener(this);
            listDisplay.add(item);
        });
        revalidate();
        repaint();
    }

    public void add(StandardItem item) {
        items.add(item);
        listDisplay.add(item);
        item.addPropertyChangeListener(this);
        item.setModification(editing);
        revalidate();
        repaint();
    }

    public void removeAll() {
        items.forEach(item -> removePropertyChangeListener(this));
        items.clear();
        listDisplay.removeAll();
        revalidate();
        repaint();
    }

    public void remove(StandardItem item) {
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?",
            "Are you sure?", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.NO_OPTION) {
            return;
        }

        listDisplay.remove(item);
        item.removePropertyChangeListener(this);
        items.remove(item);

        parental.notifyChange("remove", item, null);

        revalidate();
        repaint();
    }

    public void edit(StandardItem item) {
        remove(item);
        parental.notifyChange("edit", item, null);
    }

    public void setModifiable(boolean status) {
        editing = status;
        items.forEach(item -> item.setModification(status));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "deleteItem")) {
            System.out.println("delete " + evt.getOldValue());
            remove((StandardItem) evt.getOldValue());
        }
        else if (Objects.equals(evt.getPropertyName(), "editItem")) {
            System.out.println("delete " + evt.getOldValue());
            edit((StandardItem) evt.getOldValue());
        }
    }
}
