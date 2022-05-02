package org.boothverse.foodpants.ui.forms;

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
    protected PropertyChangeListener submitListener;

    public StandardForm(String header, Component parent) {
        super();
        this.parent = parent;
        setLocationRelativeTo(parent);

        parent.setEnabled(false);
        if (Page.class.isAssignableFrom(parent.getClass())) {
            System.out.println("main disabled");
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
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        setupListeners();
    }

    private void setupListeners() {
        submitListener = evt -> {
          if (Objects.equals(evt.getPropertyName(), "submit")) {
              System.out.println("submit");
              if (!parent.getClass().isAssignableFrom(StandardForm.class)) {
                  PageRunner.getFrame().setEnabled(true);
              }
          }
        };
        contentPanel.addPropertyChangeListener(submitListener);
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
            System.out.println("page form released");
            PageRunner.getFrame().setEnabled(true);
        }
        super.dispose();
    }
}
