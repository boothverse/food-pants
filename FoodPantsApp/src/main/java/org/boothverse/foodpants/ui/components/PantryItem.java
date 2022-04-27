package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardLabel;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;
import org.boothverse.foodpants.ui.forms.EditPantryForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;

public class PantryItem extends StandardPanel {

    protected JLabel itemName;
    protected JLabel quantity;

    protected int WIDTH = 250;
    protected int HEIGHT = 50;

    private JButton editButton, deleteButton;

    public PantryItem(String name, int amt) {
        super();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        initChildren(name, amt);
    }

    public void setModification(boolean status) {
        editButton.setVisible(status);
        deleteButton.setVisible(status);
    }

    private void initChildren(String name, int amt) {
        JPanel panel = new JPanel();

        itemName = new StandardLabel(name);
        quantity = new StandardLabel(amt + "");

        editButton = new StandardButton("Edit");
        editButton.addActionListener(e -> {
            StandardForm form = new EditPantryForm("Edit Item");
            form.setLocationRelativeTo(this);
            form.setVisible(true);
        });
        editButton.setVisible(false);

        deleteButton = new StandardButton("Delete");
        deleteButton.setVisible(false);

        panel.add(quantity);
        panel.add(editButton);
        panel.add(deleteButton);

        add(itemName, BorderLayout.WEST);
        add(panel, BorderLayout.EAST);
    }
}
