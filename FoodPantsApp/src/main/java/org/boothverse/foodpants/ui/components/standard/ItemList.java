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
    protected Page parent;

    public ItemList(int numColumns, Page parent) {
        super();
        listDisplay = new JPanel(new GridLayout(0, numColumns));
        items = new ArrayList<>();
        this.parent = parent;
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
    }

    public void add(StandardItem item) {
        items.add(item);
        listDisplay.add(item);
        item.addPropertyChangeListener(this);
        revalidate();
    }

    public void removeAll() {
        items.forEach(item -> removePropertyChangeListener(this));
        items.clear();
        listDisplay.removeAll();
        revalidate();
    }

    public void remove(StandardItem item) {
        listDisplay.remove(item);
        item.removePropertyChangeListener(this);
        items.remove(item);
        parent.notifyPage("remove", item, null);
        revalidate();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "deleteItem")) {
            System.out.println("delete " + evt.getOldValue());
            remove((StandardItem) evt.getOldValue());
        }
    }
}
