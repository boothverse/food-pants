package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.PageRunner;
import org.boothverse.foodpants.ui.controllers.StartupController;
import org.jdesktop.swingx.JXDatePicker;
import systems.uom.unicode.CLDR;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewUserForm extends StandardForm {
    private int numRows = 0;
    private final String[] labels = {"Name", "Gender", "Height (ft/in)", "Weight (lb)", "DOB"};

    private JTextField name;
    private JComboBox<String> gender;
    private JPanel heightPanel;
    private JFormattedTextField weight;
    private JXDatePicker dobPicker;

    private JSpinner feet;
    private JSpinner in;

    private StartupController controller = new StartupController();
    public NewUserForm() {
        super("Register", PageRunner.getFrame());
        initForm();
    }

    @Override
    void initForm() {
        name = new JTextField();
        name.setPreferredSize(new Dimension(getWidth() - 140, 20));
        gender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        heightPanel = new JPanel(new GridLayout(1,0));

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        feet = new JSpinner(new SpinnerNumberModel(5, 0, 10, 1));
        in = new JSpinner(new SpinnerNumberModel(7, 0, 11, 1));

        heightPanel.add(feet);
        heightPanel.add(new JLabel("  feet"));
        heightPanel.add(in);
        heightPanel.add(new JLabel("  inches"));

        NumberFormat weightFormat = NumberFormat.getNumberInstance();
        weightFormat.setMaximumIntegerDigits(3);
        weightFormat.setMaximumFractionDigits(3);
        weightFormat.setGroupingUsed(false);

        weight = new JFormattedTextField(weightFormat);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -25);
        dobPicker = new JXDatePicker();
        dobPicker.setDate(cal.getTime());
        dobPicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

        JPanel spacer = new JPanel();
        spacer.setBackground(getBackground());

        addLeftComponent(new JLabel(labels[0]), numRows);
        addRightComponent(name, numRows);

        addLeftComponent(new JLabel(labels[1]), ++numRows);
        addRightComponent(gender, numRows);

        addMiddleComponent(spacer, ++numRows);

        addLeftComponent(new JLabel(labels[2]), ++numRows);
        addRightComponent(heightPanel, numRows);

        addLeftComponent(new JLabel(labels[3]), ++numRows);
        addRightComponent(weight, numRows);

        addLeftComponent(new JLabel(labels[4]), ++numRows);
        addRightComponent(dobPicker, numRows);

        addMiddleComponent(spacer, ++numRows);

        addSubmitButton(e -> {
            if (!(name.getText().isEmpty() || weight.getText().isEmpty())) {
                PageRunner.getFrame().setVisible(true);
                Double heightInFeet = (Integer)feet.getValue() + (((Integer)in.getValue())/12.0);

                String nameVal = name.getText();
                String genderVal = (String) gender.getSelectedItem();
                Quantity<Length> heightVal = Quantities.getQuantity(heightInFeet, CLDR.FOOT);
                Quantity<Mass> weightVal = Quantities.getQuantity(Double.valueOf(weight.getText()), CLDR.POUND);
                Date dobVal = dobPicker.getDate();

                controller.register(nameVal, genderVal, heightVal, weightVal, dobVal);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "All fields must be filled.", "Submit Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
