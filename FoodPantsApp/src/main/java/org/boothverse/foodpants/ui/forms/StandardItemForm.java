package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StandardItemForm extends StandardForm {
    protected final int TXT_FIELD_WIDTH = 25;

    protected List<JTextField> textFields;
    protected String[] textLabels = {"Name", "Quantity", "Calories"};

    public StandardItemForm(String header, Component parent) {
        super(header, parent);
        initForm();
    }

    public StandardItemForm(String header, Component parent, String[] labels) {
        super(header, parent);
        setSize(WIDTH, HEIGHT);
        textLabels = labels;
        initForm();
    }

    @Override
    void initForm() {
        textFields = new ArrayList<>();

        int i = 0;
        for (; i < textLabels.length; i++) {
            JLabel lbl = new StandardLabel(textLabels[i]);
            JTextField txtField = new JTextField(TXT_FIELD_WIDTH);

            textFields.add(txtField);

            addLeftComponent(lbl, i);
            addRightComponent(txtField, i);
        }

        JButton submitBtn = new StandardButton("Submit");
        submitBtn.addActionListener(e -> this.dispose());
        addRightComponent(submitBtn, ++i);
    }
}
