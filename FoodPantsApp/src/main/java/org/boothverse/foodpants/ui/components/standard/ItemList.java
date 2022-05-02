package org.boothverse.foodpants.ui.components.standard;

import lombok.Getter;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.PageViewer;
import org.boothverse.foodpants.ui.pages.Page;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemList extends JPanel {
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

        items.forEach(item -> listDisplay.add(item));
        revalidate();
    }

    public void add(StandardItem item) {
        items.add(item);
        listDisplay.add(item);
        revalidate();
    }

    public void removeAll() {
        items.clear();
        listDisplay.removeAll();
        revalidate();
    }

    public void remove(StandardItem item) {
        listDisplay.remove(item);
        items.remove(item);
    }
}
