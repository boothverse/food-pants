package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.StandardLabel;

import javax.swing.*;
import java.awt.*;

public class RecipeForm extends JFrame {
  private static final int WIDTH = 640;
  private static final int HEIGHT = 480;

  private static final int TXT_FIELD_WIDTH = 25;

  private String[] fields = new String[]{"Name", "Servings"};

  public RecipeForm() {
    super();
    this.setSize(WIDTH, HEIGHT);
    JPanel panel = new JPanel(new FlowLayout());
    initForm(panel);

    this.add(panel);
    this.setTitle("Create Recipe");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void initForm(final JPanel panel) {
    JPanel boxPanel = new JPanel(new GridBagLayout());

    JLabel createHeader = new JLabel("Create Recipe");
    createHeader.setFont(Style.headerStyle);
    panel.add(createHeader);

    int i;
    for (i = 0; i < fields.length; i++) {
      JLabel lbl = new StandardLabel(fields[i]);
      JTextField txtField = new JTextField(TXT_FIELD_WIDTH);

      addLabel(boxPanel, lbl, i + 1);
      addInputField(boxPanel, txtField, i + 1);
    }

    JLabel ingredients = new StandardLabel("Ingredients");
    TextArea ingredArea = new TextArea();

    addLabel(boxPanel, ingredients, i + 1);
    i++;
    addInputField(boxPanel, ingredArea, i + 1);
    i++;

    JButton submitBtn = new JButton("Submit");
    submitBtn.addActionListener(e -> this.dispose());

    addInputField(boxPanel, submitBtn, i + 1);

    panel.add(boxPanel, BorderLayout.CENTER);
  }

  void addLabel(final JPanel panel, JLabel label, int row) {
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    panel.add(label, gbc);
  }

  void addInputField(final JPanel panel, Component field, int row) {
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.gridx = 1;
    gbc.gridy = row;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    panel.add(field, gbc);
  }
}
