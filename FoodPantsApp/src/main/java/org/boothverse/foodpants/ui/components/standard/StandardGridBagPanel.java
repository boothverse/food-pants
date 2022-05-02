package org.boothverse.foodpants.ui.components.standard;

import lombok.Getter;
import lombok.Setter;
import org.boothverse.foodpants.ui.PageRunner;
import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StandardGridBagPanel extends JPanel {
    public StandardGridBagPanel() {
        setLayout(new GridBagLayout());
        setBackground(Style.TRANSPARENT);
    }

    public void addSeperator(int row) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
    }

    public void addMiddleComponent(Component c, int row) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(c, gbc);
    }

    public void addLeftComponent(Component c, int row) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.3;
        gbc.weighty = 0.3;
        gbc.ipadx = 10;
        gbc.ipady = 5;

        add(c, gbc);
    }

    public void addRightComponent(Component c, int row) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipady = 5;

        add(c, gbc);
    }

    public void addSubmitButton(ActionListener e, int row)  {
        JButton submitBtn = new StandardButton("Submit");
        submitBtn.addActionListener(e);
        addRightComponent(submitBtn, row);
    }
}
