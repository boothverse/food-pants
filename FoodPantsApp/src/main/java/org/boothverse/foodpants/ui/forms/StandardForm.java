package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.PageRunner;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class StandardForm extends JFrame {
    // Form panel
    private final int DEFAULT_WIDTH = 384;
    private final int DEFAULT_HEIGHT = 256;
    private int numRows;

    protected JPanel contentPanel;
    protected JPanel wrapperPanel;

    public StandardForm(String header) {
        super();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(PageRunner.getPageViewer());
        setResizable(false);

        // Content goes in this panel, order with grid bag layout
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Style.TRANSPARENT);

        // Flowlayout panel (wrap around grid bag panel)
        wrapperPanel = new JPanel(new FlowLayout());
        wrapperPanel.setBackground(Style.TRANSPARENT);

        // Form initialization
        initFormHeader(header);

        setTitle(header);
        add(wrapperPanel);
        wrapperPanel.add(contentPanel, BorderLayout.CENTER);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    protected void initFormHeader(String header) {
        JLabel title = new JLabel(header);
        title.setFont(Style.headerStyle);
        wrapperPanel.add(title);
    }

    abstract void initForm();

    protected void addLeftComponent(Component c, int row) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.3;
        gbc.weighty = 0.3;
        gbc.ipadx = 10;
        gbc.ipady = 10;

        contentPanel.add(c, gbc);

        if (row > numRows) {
            numRows = row;
        }
    }

    protected void addRightComponent(Component c, int row) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipady = 10;

        contentPanel.add(c, gbc);

        if (row > numRows) {
            numRows = row;
        }
    }

    void addSubmitButton(ActionListener e)  {
        JButton submitBtn = new StandardButton("Submit");
        submitBtn.addActionListener(e);
        addRightComponent(submitBtn, ++numRows);
    }
}
