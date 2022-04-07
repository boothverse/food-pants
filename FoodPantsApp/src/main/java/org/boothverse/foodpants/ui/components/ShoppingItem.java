package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardCheckbox;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;
import org.boothverse.foodpants.ui.forms.EditShoppingForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ShoppingItem extends StandardPanel implements ActionListener {
    protected JLabel itemName;
    protected JTextField quantity;
    protected StandardCheckbox checkBox;
    protected ImageIcon icon;
    protected JButton deleteButton;
    protected JButton editButton;


    public ShoppingItem(String name, int quantity) {
        super();
        setLayout(new BorderLayout());
        initChildren(name, quantity);
        setPreferredSize(new Dimension(400, 60));
    }

    private void initChildren(String name, int amt) {
        //Add right hand side formatter
        JPanel rightFormat = new JPanel();
        rightFormat.setBackground(Style.TRANSPARENT);
        add(rightFormat, BorderLayout.EAST);

        // Add left hand side formatter
//        JPanel leftFormat = new JPanel();
//        rightFormat.setBackground(Style.TRANSPARENT);
//        add(leftFormat, BorderLayout.WEST);

        // Setup Checkbox
        checkBox = new StandardCheckbox();
        add(checkBox, BorderLayout.WEST);

        // Setup Name of the item
        itemName = new JLabel(name);
        itemName.setFont(Style.bodyStyle);
        add(itemName);

        // Set up quantity text field
        quantity = new JFormattedTextField(NumberFormat.getIntegerInstance());
        quantity.setText(amt + "");
        quantity.setFont(Style.bodyStyle);
        quantity.setHorizontalAlignment(JLabel.RIGHT);
        quantity.setBackground(Style.PLATINUM);
        quantity.setBorder(Style.BORDER_1);
        quantity.setPreferredSize(new Dimension(40, 40));
        rightFormat.add(quantity);

        // Add edit button
        editButton = new StandardButton("Edit");
        rightFormat.add(editButton);
        editButton.setVisible(false);
        editButton.addActionListener(this);

        // Add delete button
        deleteButton = new StandardButton("Delete");
        rightFormat.add(deleteButton);
        deleteButton.setVisible(false);
        deleteButton.setActionCommand(name);
        deleteButton.addActionListener(this);
    }

    public void setModification(boolean status) {
        editButton.setVisible(status);
        deleteButton.setVisible(status);
    }

    public StandardCheckbox getCheckBox() {
        return checkBox;
    }

    public String getName() {
        return itemName.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            StandardForm form = new EditShoppingForm("Edit Item");
            form.setLocationRelativeTo(this);
            form.setVisible(true);
        } else if (e.getSource() == deleteButton) {
            System.out.println("asdadada");
            firePropertyChange("deleteItem", e.getActionCommand(), null);
        }
    }
}
