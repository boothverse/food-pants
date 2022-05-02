package org.boothverse.foodpants.ui.components.standard;

import lombok.Getter;
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
    protected Component parental;

    public ItemList(int numColumns, Component parent) {
        super();
        listDisplay = new JPanel(new GridLayout(0, numColumns));
        items = new ArrayList<>();
        this.parental = parent;
        setBackground(parent.getBackground());
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
        listDisplay.remove(item);
        item.removePropertyChangeListener(this);
        items.remove(item);

        if (Page.class.isAssignableFrom(parental.getClass())) {
            ((Page)parental).notifyChange("remove", item, null);
        }
        else {
            parental.revalidate();
            parental.repaint();
        }
        revalidate();
        repaint();
    }

    public void setModifiable(boolean status) {
        items.forEach(item -> item.setModification(status));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "deleteItem")) {
            System.out.println("delete " + evt.getOldValue());
            remove((StandardItem) evt.getOldValue());
        }
    }
}
