package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.PageRunner;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardGridBagPanel;
import org.boothverse.foodpants.ui.pages.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public abstract class StandardForm extends JFrame {
    // Form panel
    private final int DEFAULT_WIDTH = 500;
    private final int DEFAULT_HEIGHT = 400;
    private int numRows;

    protected StandardGridBagPanel contentPanel;
    protected JPanel wrapperPanel;
    protected JLabel title;
    protected Component parent;

    public StandardForm(String header, Component parent) {
        super();
        this.parent = parent;
        setLocationRelativeTo(parent);

        // Disable prior forms/ pages while this one is open
        parent.setEnabled(false);
        if (Page.class.isAssignableFrom(parent.getClass())) {
            PageRunner.getFrame().setEnabled(false);
        }

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Content goes in this panel
        contentPanel = new StandardGridBagPanel();

        // Flowlayout panel (wrap around grid bag panel)
        wrapperPanel = new JPanel();
        wrapperPanel.setBackground(Style.TRANSPARENT);

        // Form initialization
        initFormHeader(header);

        setTitle(header);
        add(wrapperPanel);
        wrapperPanel.add(contentPanel);
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    protected void initFormHeader(String header) {
        if (title == null) {
            title = new JLabel(header);
            title.setFont(Style.headerStyle);
            wrapperPanel.add(title);
        }
        else {
            title.setText(header);
        }

    }

    abstract void initForm();

    protected void addLeftComponent(Component c, int row) {
        contentPanel.addLeftComponent(c, row);

        if (row > numRows) {
            numRows = row;
        }
    }

    protected void addRightComponent(Component c, int row) {
        contentPanel.addRightComponent(c, row);

        if (row > numRows) {
            numRows = row;
        }
    }

    protected void addMiddleComponent(Component c, int row) {
        contentPanel.addMiddleComponent(c, row);
        if (row > numRows) {
            numRows = row;
        }
    }

    void addSubmitButton(ActionListener e)  {
        contentPanel.addSubmitButton(e, ++numRows);
    }

    @Override
    public void dispose() {
        parent.setEnabled(true);
        if (Page.class.isAssignableFrom(parent.getClass())) {
            PageRunner.getFrame().setEnabled(true);
        }
        super.dispose();
    }

    protected void updateFoodSearchBar(Food newFood) {}
}
