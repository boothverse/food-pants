package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddReportForm extends StandardForm {
    protected final int WIDTH = 384;
    protected final int HEIGHT = 256;
    protected final int TXT_FIELD_WIDTH = 25;

    protected List<JTextField> textFields;

    public AddReportForm(String header) {
        super(header);
        setSize(WIDTH, HEIGHT);
    }

    //TODO: This type of form doesn't make sense for needs
    @Override
    void initForm(final JPanel panel) {
        String[] textLabels = new String[]{"ChartType", "Fields"};
        textFields = new ArrayList<>();

        int i;
        for (i = 0; i < textLabels.length; i++) {
            JLabel lbl = new StandardLabel(textLabels[i]);
            JTextField txtField = new JTextField(TXT_FIELD_WIDTH);

            textFields.add(txtField);

            addLeftComponent(lbl, i + 1);
            addRightComponent(txtField, i + 1);
        }

        JButton submitBtn = new StandardButton("Submit");
        submitBtn.addActionListener(e -> this.dispose());

        addRightComponent(submitBtn, ++i);

        panel.add(boxPanel, BorderLayout.CENTER);
    }
}