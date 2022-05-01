package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.PageRunner;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardGridBagPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class StandardForm extends JFrame {
    // Form panel
    private final int DEFAULT_WIDTH = 500;
    private final int DEFAULT_HEIGHT = 400;
    private int numRows;

    protected StandardGridBagPanel contentPanel;
    protected JPanel wrapperPanel;
    protected JLabel title;

    public StandardForm(String header) {
        super();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(PageRunner.getPageViewer());
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

    void addSubmitButton(ActionListener e)  {
        contentPanel.addSubmitButton(e, ++numRows);
    }
}
